package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;

public class ElevatorToggleBack extends WaitCommand {
  private final Elevator elevator;
  private double speed;
  boolean canRun=true;

  public ElevatorToggleBack(Elevator elevator, double time) {
    super(time);
    this.elevator = elevator;
    addRequirements(elevator);
  }

  @Override
  public void initialize() {
    if (elevator.isUp) {
      this.speed = Constants.elevator.elevatorBackupDownSpeed;
    }
    else {
      this.speed = Constants.elevator.elevatorBackupUpSpeed;
    }

    if (elevator.isUp){
      canRun=!elevator.topSwitch.isOk();
    }
    else{
      canRun=!elevator.bottomSwitch.isOk();
    }
  }

  @Override
  public void execute() {
    super.execute();
    elevator.moveElevator(speed);
  }



  @Override
  public void end(boolean interupted) {
    elevator.moveElevator(0);

    if( !interupted){
      elevator.isUp=!elevator.isUp;
    }
  }

  public boolean isFinished(){
    return (!canRun||super.isFinished());
  }
}