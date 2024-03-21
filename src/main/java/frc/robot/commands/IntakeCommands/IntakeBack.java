package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeBack extends WaitCommand {
  private final Intake intake;
  public boolean canRun=true;

  public IntakeBack(Intake intake) {
    super(Constants.intake.backupTime);
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
      SmartDashboard.putString("ended", "false");
      this.intake.intake();
  }

  @Override
  public void end(boolean interupted){
    //super.end(interupted);
    SmartDashboard.putString("ended", "true");
    intake.stop();
  }

  @Override
  public boolean isFinished(){
    return (!canRun||super.isFinished());

  }

}
