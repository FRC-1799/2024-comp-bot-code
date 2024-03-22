package frc.robot.commands.SpeakerShooterCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SpeakerShooter;

public class ShootSpeakerMain extends Command {
    final SpeakerShooter shooter;
    boolean canRun;
    int count=0;
    
    public ShootSpeakerMain(SpeakerShooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize(){
        count=0;
        canRun=shooter.beamBreak.isOk()||shooter.beamBreak.getVal();
        
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("count", count);
        SmartDashboard.putString("string", "test");
        shooter.revving();
        //shooter.runIndex();
        if (shooter.canShoot()){
            count++;
            if (count>100){
                shooter.runIndex();
            }
        }
    }

    // @Override
    // public boolean isFinished() { 
    //     return false;
    // }  

    @Override
    public void end(boolean wasInterupted){
        shooter.stop();
    }
    
}
