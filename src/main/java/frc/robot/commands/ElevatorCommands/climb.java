package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.limitSwitch;

public class climb extends Command {
  Elevator elevator;
  public climb(Elevator elevator) {
    this.elevator = elevator;
    addRequirements(elevator);
	}

  @Override
  public void execute() {
    elevator.moveElevator(Constants.elevator.elevatorClimbSpeed);
  }



  @Override
  public void end(boolean wasInterupted) {
    elevator.stop();

    if (!wasInterupted){
      elevator.isUp=!elevator.isUp;
      new stayClimbing(elevator).schedule();
    }
  }

  @Override
  public boolean isFinished() { 
    return elevator.bottomSwitch.getVal();
  }
}