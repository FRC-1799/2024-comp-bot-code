package frc.robot.commands;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.LimitManager;

public class GetLimitPort extends Command{
    SerialPort port;
    byte[] initPack;
    SerialPort.Port portToTry;

    /** creates a command that will check if the arduino is on the inputed serial port */
    public GetLimitPort(SerialPort.Port portToTry, byte[] pack){
        this.portToTry=portToTry;
        initPack=pack;
        

    }
    @Override
    public void execute(){
        if (port==null){
            try{
                port = new SerialPort(9600, portToTry);
            }
            catch(Exception e){
                
            }
        }

        byte[] checkPack;
        port.write(initPack, initPack.length);
        if (port.getBytesReceived()>0){
            checkPack=port.read(initPack.length);
            
            for (int i=0; i<initPack.length; i++){
                if(checkPack[i]!=initPack[i]){
                    return;
                }
            }
            LimitManager.setPort(port);
            CommandScheduler.getInstance().cancel(this);
        }
    }
}
