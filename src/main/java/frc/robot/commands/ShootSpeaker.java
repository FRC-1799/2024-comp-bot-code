package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SpeakerShooter;

public class ShootSpeaker extends Command {
    final SpeakerShooter shooter;
    
    public ShootSpeaker(SpeakerShooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }


    @Override
    public void execute() {
        shooter.revving();
        if (shooter.canShoot()){
            shooter.runIndex();
        }
    }

    @Override
    public boolean isFinished() { 
        return (!shooter.beamBreak.getVal());
    }  
    
    
    
}
