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

    public static void StartUp(){
    
        for (switchInfoBox i: Constants.switchInfo){
            switches.put(i.ID, new limitSwitch(i));
     
        }
        unsignedByte[] pack = new unsignedByte[Constants.switchInfo.length*3+7];
        //LOAD BYTE INTO BYTE PACK
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
    



        for (int i=4, n=0; n<pack.length-6;i+=3, n++){
            pack[i]=new unsignedByte(Constants.switchInfo[n].ID);
            pack[i+1]=new unsignedByte(Constants.switchInfo[n].statusPort);
            pack[i+2]=new unsignedByte(Constants.switchInfo[n].readPort);
        }


        for (int i=0; i<pack.length;i++){
            bytepack[i]=pack[i].getAsByte();
        }
        getPort(bytepack);
    }

    public static void getPort(byte[] pack){
        new ParallelRaceGroup(
            new GetLimitPort(SerialPort.Port.kUSB, pack),
            new GetLimitPort(SerialPort.Port.kUSB1, pack),
            new GetLimitPort(SerialPort.Port.kUSB2, pack)
            );
    }

    public static void periodic(){
        distributePack(getPackage());
    }


    public static unsignedByte[] getPackage(){
        while (port.getBytesReceived()>0){
            int startByteCount=0;
            int endByteCount=0;
            boolean packIsGood=true;

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
            pack[0]=lenghtByte;
            for (int i=1; i<pack[0].get();i++){
                pack[i]=read();
            }
            
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
                return pack;
            }
            
        }
        return null;
    }

    public static void distributePack(unsignedByte[] pack){
        for (int i=1;i<pack[0].get(); i+=3){
            getSwitch(pack[i].get()).update(byteToBool(pack[i+1]), byteToBool(pack[i+2]));
        }
    }

    public static limitSwitch getSwitch(int switchID){
        return switches.get(switchID);
    }

    public static boolean byteToBool(unsignedByte toCheck){
        if (toCheck==Constants.switchManager.trueVal){
            return true;
        }
        else if (toCheck==Constants.switchManager.falseVal){
            return false;
        }
        else{
            throw(new Error("a byte that did not contain the true or false value was converted to a bool"));
        }
    }

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
    public static unsignedByte read(){
        return new unsignedByte(port.read(1)[0]);
    }

    public static void setPort(SerialPort portToBeSet){
        if (port==null){
            port=portToBeSet;
        }
    }

}
