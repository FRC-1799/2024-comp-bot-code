package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class RepetitiveIntake extends Command {
  Intake intake;
  int counter;

  boolean noteInIntake;
  
  public RepetitiveIntake(Intake intake) {
    this.intake = intake;
    addRequirements(intake);

  }

  @Override
  public void initialize() {

    counter = 0;

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
    if (intake.beamBreak.getVal()) {
      counter++;
    }

    else {
      counter=0;
    }
    return counter > Constants.intake.counterCap;
  } 

  public void checkRequirements(){
    boolean status = true;

    if (!intake.beamBreak.isOk()) {
      status = false;
    }

    if (intake.beamBreak.getVal()){
      status = false;
    }

    if (!status){
      CommandScheduler.getInstance().cancel(this);
    }
  }
}
