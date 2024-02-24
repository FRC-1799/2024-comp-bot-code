package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.WristIntake;

public class manualMoveWrist extends RunCommand{
    public manualMoveWrist(WristIntake wrist, DoubleSupplier speed){
        super(
            () -> {
                wrist.move(MathUtil.applyDeadband(speed.getAsDouble(), 0.1));
            },
            wrist);
        addRequirements(wrist);
    }
}
