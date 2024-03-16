package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class OuttakeMain extends Command {
  Intake intake;
  double speed;
  boolean canRun;

  public OuttakeMain(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize(){
    canRun=intake.beamBreak.isOk()||!intake.beamBreak.getVal();

  }

  @Override
  public void execute() {
    intake.outake();
  }

  @Override
  public boolean isFinished() { 
    return intake.beamBreak.getVal()||!canRun;
  } 

  @Override
  public void end(boolean wasInterupted){
    intake.stop();
  }
}
