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

    public void execute(){
        shooter.intake();
    }

    public boolean isFinished(){
        return shooter.beamBreak.getVal()||!canRun;
    }

    public void end(){
        shooter.stop();
    }
}
