package frc.robot.commands.ElevatorParallelCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.stayAtTop;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.limitSwitch;

public class ElevatorMove extends Command {
  private final Elevator elevator;
  private double speed;

  public ElevatorMove(Elevator elevator, double speed) {
    this.speed = speed;
    this.elevator = elevator;
  }

  @Override
  public void initialize(){
    
  }

  @Override
  public void execute() {
    elevator.moveElevator(speed);
  }

  @Override
  public void end(boolean wasInterupted) {
    if (!wasInterupted){
      elevator.isUp=!elevator.isUp;
    }

    elevator.stop();
    new stayAtTop(elevator).schedule();
  }
  public void checkRequirements(){
    boolean status=true;
    limitSwitch checkSwitch;

    if (elevator.isUp){
      checkSwitch=elevator.bottomSwitch;
    }
    else{
      checkSwitch=elevator.topSwitch;
    }

    if (!checkSwitch.isOk()){
      status=false;
    }
    
    if (!status){
      CommandScheduler.getInstance().cancel(this);
    }

  }
}
