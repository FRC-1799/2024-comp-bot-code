package frc.robot.SemiAutoRoutines;

import com.fasterxml.jackson.databind.ser.std.NumberSerializers.ShortSerializer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.semiAutoManager;
import frc.robot.Constants.elevator;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;

import frc.robot.subsystems.WristIntake;

public class scoreAmpPositRunner extends InstantCommand{
    public Elevator elevator;
    public WristIntake wrist;
    public scoreAmpPositRunner (Elevator elevator, WristIntake wrist){
        this.elevator = elevator;
        this.wrist=wrist;
    }

    @Override
    public void initialize(){
        Command command = new scoreAmpPosit(elevator, wrist);
        if (semiAutoManager.getCurrent()!=null){
            CommandScheduler.getInstance().cancel(semiAutoManager.getCurrent());
        }
        semiAutoManager.setCurrent(command);
        command.schedule();
    
    }
}
