package frc.robot;

import java.util.Dictionary;
import java.util.Hashtable;

//import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.GetLimitPort;
import frc.robot.subsystems.limitSwitch;

public class LimitManager {
    public static SerialPort port=null;
    public static Dictionary<Integer, limitSwitch> switches = new Hashtable<>();
    public static boolean hasStartedUp=false;
    public static int ticksWithoutNewPackage=0;

    /*
    * expeted file format for init
    * 0x00, 0x00, 0x00 Pattern to help the reader identify the start of a package
    * 1 byte to tell the compiler how long to expect the package to be, does not include headers or footers but does include the length value itself
    * 4 byte packages for the deffinition of switches ordered ID, CheckPort, ValuePort
    * 0x255, 0x255, 0x255 Pattern to conferm the ending of a package
    * 
    */

    /* expected periodic format
    * 3 0x00 bytes as a package init
    * one bit to define the lenght of the package
    * 3 byte groups to send data formated ID, status, Value
    * 3 0xff bytes to finish the package
    */




    /** starts up the limit manager, will only run once per power cycle */
    public static void StartUp(){

        //makes sure the startup doesn't run multiple times

        if (hasStartedUp){
            return;
        }
        

        //loads the switches
        for (switchInfoBox i: Constants.switchInfo){
            switches.put(i.ID, new limitSwitch(i));
     
        }

        //loads the structs to be used to create the init pack

        


        unsignedByte[] pack = new unsignedByte[Constants.switchInfo.length*3+7];
        byte[] bytepack = new byte[Constants.switchInfo.length*3+7];

        //start padding
        pack[0]=new unsignedByte(0x00);
        pack[1]=new unsignedByte(0x00);
        pack[2]=new unsignedByte(0x00);

        //byte count 
        pack[3]=new unsignedByte(pack.length-6);

        //final padding 
        pack[-1]=new unsignedByte(0xFF);
        pack[-2]=new unsignedByte(0xFF);
        pack[-3]=new unsignedByte(0xFF);
    


        //actual meat of the package
        for (int i=4, n=0; n<Constants.switchInfo.length;i+=3, n++){
            pack[i]=new unsignedByte(Constants.switchInfo[n].ID);
            pack[i+1]=new unsignedByte(Constants.switchInfo[n].statusPort);
            pack[i+2]=new unsignedByte(Constants.switchInfo[n].readPort);
        }

        //converts the pack from unsignedBytes to bytes that are ready to be sent
        for (int i=0; i<pack.length;i++){
            bytepack[i]=pack[i].getAsByte();
        }


        //finds the port and passes in the config pack
        getPort(bytepack);
    }
    /** finds the port with the arduino, the port will be asigned via the setPort function instead of a return value */
    public static void getPort(byte[] pack){
        //Race group that will find the usb port with an arduino if any
        new ParallelRaceGroup(
            new GetLimitPort(SerialPort.Port.kUSB, pack),
            new GetLimitPort(SerialPort.Port.kUSB1, pack),
            new GetLimitPort(SerialPort.Port.kUSB2, pack)
            );
    }

    /** runs every period to keep the limits updated */
    public static void periodic(){
        //runs every period


        if (port!=null){
            //tries to naturaly disribute data
            distributePack(getPackage());

            //sets all switches to false if code hasn't reseved new data in too long
            if (ticksWithoutNewPackage>Constants.switchManager.ticksWithoutNewPackageBeforErrorState){
                for(switchInfoBox limitSwitch: Constants.switchInfo){
                    getSwitch(limitSwitch.ID).update(true, false);
                }
            }
        }

        
    }

    /**reads the current package from the serial port */
    public static unsignedByte[] getPackage(){
        

        while (port.getBytesReceived()>0){
            int startByteCount=0;
            int endByteCount=0;
            boolean packIsGood=true;

            //looks for the start pattern
            while(startByteCount<3){
                if (read().get()==0){
                    startByteCount++;
                }
                else{
                    startByteCount=0;
                }
            }


            unsignedByte lenghtByte = read();
            unsignedByte[] pack= new unsignedByte[lenghtByte.get()];

            //reads the pack into a array to be distributed later
            pack[0]=lenghtByte;
            for (int i=1; i<pack[0].get();i++){
                pack[i]=read();
            }


            //looks for the end symbol and flushes and returns if it is present
            while(endByteCount<3){
                if (read().get()==255){
                    endByteCount++;
                }
                else{
                    packIsGood=false;
                    break;
                }
            }
            if (packIsGood){
                port.flush();
                return pack;
            }
            
        }
        return null;
    }

    /**distribues a data pack to all of the limit switches */
    public static void distributePack(unsignedByte[] pack){

        //aborts if there is no pack and keeps track of how long it has been since there has been a pack
        if (pack==null){
            ticksWithoutNewPackage++;
            return;
        }
        else{
            ticksWithoutNewPackage=0;
        }

        //distribues the pack
        for (int i=1;i<pack[0].get(); i+=3){
            getSwitch(pack[i].get()).update(byteToBool(pack[i+1]), byteToBool(pack[i+2]));
        }
    }


    /**returns a switch based on the switchID */
    public static limitSwitch getSwitch(int switchID){
        return switches.get(switchID);
    }

    /** transforms a byte into a bool */
    public static boolean byteToBool(unsignedByte toCheck){
        if (toCheck.get()==Constants.switchManager.trueVal.get()){
            return true;
        }
        else if (toCheck.get()==Constants.switchManager.falseVal.get()){
            return false;
        }
        else{
            throw(new Error("a byte that did not contain the true or false value was converted to a bool"));
        }
    }

    /** reads an amount of bytes equal to bytesToRead into a unsignedByte list */
    public static unsignedByte[] read(int bytesToRead){
        byte[] pack = port.read(bytesToRead);
        unsignedByte[] returnPack = new unsignedByte[bytesToRead];
        int count=0;
        for (byte i: pack){
            returnPack[count]=new unsignedByte(i);
            count++;
        }
        return returnPack;
    }

    /**reads and returns a single unsigned byte */
    public static unsignedByte read(){
        return new unsignedByte(port.read(1)[0]);
    }

    /*used to set the serial port */
    public static void setPort(SerialPort portToBeSet){
        if (port==null){
            port=portToBeSet;
        }
    }

}
