package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Pneumatics;

public class ToggleCompressor extends InstantCommand {
  public ToggleCompressor(Pneumatics pneumatics) {
    super(
      ()->{
        pneumatics.toggleCompressor();
      }
    );
  }
}