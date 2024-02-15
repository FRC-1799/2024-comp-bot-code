package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.SpeakerShooter;

public class SpeakerSafety extends RunCommand {
  public SpeakerSafety(SpeakerShooter importedShooter, DoubleSupplier flySpeed, DoubleSupplier indexSpeed) {
    super(
        ()->{
            importedShooter.SafetyFunction(
                MathUtil.applyDeadband(indexSpeed.getAsDouble(),0.1),
                MathUtil.applyDeadband(flySpeed.getAsDouble(), 0.1));
        },
        importedShooter
    );
    addRequirements(importedShooter);
  }      
}