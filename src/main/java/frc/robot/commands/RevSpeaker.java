package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.subsystems.SpeakerShooter;

public class RevSpeaker extends Command {
    // setActive setActive;
    final SpeakerShooter shooter;

    public RevSpeaker(SpeakerShooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.revving();
    }
    
    public void checkRequirements(){
        boolean status = true;

        if (!shooter.beamBreak.getVal()){
            status = false;
        }
        
        if (!status){
            CommandScheduler.getInstance().cancel(this);
        }
    }
}
