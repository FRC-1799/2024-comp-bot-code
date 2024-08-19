package frc.robot.commands.WristComands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.WristIntake;

public class WristHold extends Command{
    WristIntake wrist;
    private final PIDController pid = new PIDController(
        Constants.wrist.kp,
        Constants.wrist.ki,
        Constants.wrist.kd
    );

    public  WristHold(WristIntake wrist){
        this.wrist=wrist;
        pid.enableContinuousInput(-Math.PI, Math.PI);
        pid.setSetpoint(wrist.holdPoint);
        addRequirements(wrist);
    }

    @Override
    public void execute(){
        if (wrist.shouldBeHeld) {
            if (pid.atSetpoint()){
                wrist.move(0);
                pid.calculate(Math.toRadians(wrist.getEncoder()));
            }
            else{
                wrist.move(MathUtil.clamp(pid.calculate(Math.toRadians(wrist.getEncoder())), -Constants.wrist.motorSpeeds.maxSpeed, Constants.wrist.motorSpeeds.maxSpeed));
            }
        }
    }
}
