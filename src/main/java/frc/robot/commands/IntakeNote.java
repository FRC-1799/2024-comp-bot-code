package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Intake;

public class IntakeNote extends Command {
  Intake intake;

  public IntakeNote(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }
  
  @Override
  public void initialize(){
  }

  @Override
  public void execute() {
    intake.intake();
  }
  
  @Override
  public void end(boolean interrupted) {
    intake.stop();
  }


}