package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeMain extends Command {
  Intake intake;
  int counter;
  boolean canRun;

  boolean noteInIntake;
  
  public IntakeMain(Intake intake) {
    this.intake = intake;
    addRequirements(intake);

  }

  @Override
  public void initialize() {
    canRun=intake.beamBreak.isOk()||!intake.beamBreak.getVal();


  }

  @Override
  public void execute() {
    intake.intake();

  }

  @Override
  public void end(boolean wasInterupted){
    intake.stop();
  }

  @Override
  public boolean isFinished() { 
    return !intake.beamBreak.getVal()||!canRun;  
  }
}
