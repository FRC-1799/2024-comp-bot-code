package frc.robot.commands.SpeakerShooterCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SpeakerShooter;

public class ShootSpeakerMain extends Command {
    final SpeakerShooter shooter;
    boolean canRun;
    
    public ShootSpeakerMain(SpeakerShooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize(){
        canRun=shooter.beamBreak.isOk()||shooter.beamBreak.getVal();
        
    }

    @Override
    public void execute() {
        shooter.revving();
        shooter.runIndex();
        // if (shooter.canShoot()){
        //     shooter.runIndex();
        // }
    }

    @Override
    public boolean isFinished() { 
        return false;
    }  

    @Override
    public void end(boolean wasInterupted){
        shooter.stop();
    }
    
}
