package frc.robot.commands.SpeakerShooterCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.SpeakerShooter;

public class shooterIntakeBack extends WaitCommand {
    final SpeakerShooter shooter;
    boolean canRun;
    
    public shooterIntakeBack(SpeakerShooter shooter) {
        super(Constants.speakerShooter.intakeTime);
        this.shooter = shooter;
        addRequirements(shooter);
        
    }


    @Override
    public void initialize(){
        canRun=!shooter.beamBreak.isOk();
    }


    @Override
    public void execute() {
        shooter.intake();
    }


    @Override
    public void end(boolean wasInterupted){
        shooter.stop();
        
    }
    
    @Override
    public boolean isFinished(){
        return (!canRun||super.isFinished());
    }
}
