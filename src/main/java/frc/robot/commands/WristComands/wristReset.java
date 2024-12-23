package frc.robot.commands.WristComands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants;
import frc.robot.Constants.wrist;
import frc.robot.subsystems.WristIntake;
import frc.robot.subsystems.limitSwitch;

public class wristReset extends Command{
    WristIntake wrist;
    public wristReset(WristIntake wrist){
        this.wrist=wrist;
        addRequirements(wrist);
    }

    @Override
    public void initialize(){
        // if (limitSwitch.isOk()){
        //     cancel();
        // }
    }

    @Override
    public void execute(){
        wrist.move(Constants.wrist.resetSpeed);
    }

    @Override
    public boolean isFinished(){
        return wrist.getLimit();
    }

    @Override
    public void end(boolean isFinished){
        wrist.move(0);
    }
}
