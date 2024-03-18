package frc.robot.commands.SpeakerShooterCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SpeakerShooter;

public class shooterIntakeMain extends Command{
    SpeakerShooter shooter;
    boolean canRun;

    public shooterIntakeMain(SpeakerShooter shooter){
        this.shooter=shooter;
        addRequirements(shooter);

    }

    @Override
    public void initialize(){
        
        canRun = shooter.beamBreak.isOk()||!shooter.beamBreak.getVal();

    }
    @Override
    public void execute(){
        shooter.intake();
    }

    @Override
    public boolean isFinished(){
        return shooter.beamBreak.getVal()||!canRun;
    }
    @Override
    public void end(boolean wasInteruped){
        shooter.stop();
    }
}
