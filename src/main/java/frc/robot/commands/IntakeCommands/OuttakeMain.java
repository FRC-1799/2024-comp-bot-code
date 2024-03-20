package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class OuttakeMain extends Command {
  Intake intake;
  double speed;
  boolean canRun;
  int count=0;

  public OuttakeMain(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize(){
    canRun=intake.beamBreak.isOk()||!intake.getBeamBreak();
    count=0;

  }

  @Override
  public void execute() {
    intake.outake();
  }

  @Override
  public boolean isFinished() { 
    if (!intake.getBeamBreak()){
      count++;
    }

    return count>50||!canRun;
  } 

  @Override
  public void end(boolean wasInterupted){
    intake.stop();
  }
}
