package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.limitSwitch;

public class OutakeNote extends Command {
  // setActive setActive;
  limitSwitch topSwitch;
  limitSwitch bottomSwitch;
  Intake intake;

  public OutakeNote(Intake intake) {
    this.intake = intake;
  }

  @Override
  public void execute() {
    intake.outake();

  }
}