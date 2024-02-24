package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;

public class ElevatorTimed extends WaitCommand {
  private final Elevator elevator;
  private double speed;

  public ElevatorTimed(Elevator elevator, double time) {
    super(time);
    this.elevator = elevator;
  }

  @Override
  public void initialize() {
    if (elevator.isUp) {
      this.speed = Constants.elevator.elevatorDownSpeed;
    }
    else {
      this.speed = Constants.elevator.elevatorUpSpeed;
    }
  }

  @Override
  public void execute() {
    super.execute();
    elevator.moveElevator(speed);
  }

  @Override
  public void end(boolean interupted) {
    elevator.isUp=!elevator.isUp;
    super.end(interupted);
  }
}