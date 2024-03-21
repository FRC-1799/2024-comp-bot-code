package frc.robot.SemiAutoRoutines;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.semiAutoManager;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WristIntake;

public class grabNoteFromGroundRunner extends InstantCommand{
    public DriveBase drive;
    public Elevator elevator;
    public Intake intake;
    public WristIntake wrist;
    public grabNoteFromGroundRunner (DriveBase drive, Elevator elevator, Intake intake, WristIntake wrist){
        this.drive=drive;    
        this.elevator = elevator;
        this.intake = intake;
        this.wrist = wrist;
    }

    @Override
    public void initialize(){
        Command command = new grabNoteGround(drive, wrist, intake, elevator);
        if (semiAutoManager.getCurrent()!=null){
            CommandScheduler.getInstance().cancel(semiAutoManager.getCurrent());
        }
        semiAutoManager.setCurrent(command);
        command.schedule();
    
    }
}
