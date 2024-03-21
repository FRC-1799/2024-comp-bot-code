package frc.robot.SemiAutoRoutines;

import com.fasterxml.jackson.databind.ser.std.NumberSerializers.ShortSerializer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.semiAutoManager;
import frc.robot.subsystems.DriveBase;



public class testRoutineRunner extends InstantCommand{
    public DriveBase drive;

    public testRoutineRunner(DriveBase drive){
        this.drive=drive;    



    }

    @Override
    public void initialize(){

        Command command = new testRoutine(drive);

        if (semiAutoManager.getCurrent()!=null){
            CommandScheduler.getInstance().cancel(semiAutoManager.getCurrent());
        }
        semiAutoManager.setCurrent(command);
        command.schedule();
    
    }
}
