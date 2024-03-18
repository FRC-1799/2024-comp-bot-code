package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class OuttakeBack extends WaitCommand {
  private final Intake intake;
  boolean canRun=true;

  public OuttakeBack(Intake intake) {
    super(Constants.intake.outtakeBackupTime);
    this.intake = intake;
    addRequirements(intake);


  }


  @Override
  public void initialize(){
    canRun=!intake.beamBreak.isOk();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
      super.execute();
      intake.outake();
  }

  @Override
  public void end(boolean interupted){
    intake.stop();
    
  }

  @Override
  public boolean isFinished(){
    return (!canRun||super.isFinished());
  }
}
