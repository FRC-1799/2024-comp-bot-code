package frc.robot.commands;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.LimitManager;

public class GetLimitPort extends Command{
    SerialPort port;
    byte[] initPack;
    public GetLimitPort(SerialPort.Port portToTry, byte[] pack){
        try{
            port = new SerialPort(9600, portToTry);
        }
        catch(Exception e){
            port=null;
        }
        initPack=pack;
        

    }
    @Override
    public void execute(){
        if (port==null){
            return;
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
        }
    }
}
