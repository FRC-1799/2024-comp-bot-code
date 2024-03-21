package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.limitSwitch;

public class stayAtTopBack extends WaitCommand {

    Elevator elevator;
    boolean canRun=true;

    public stayAtTopBack(Elevator elevator){
        super(20);
        this.elevator=elevator;
        addRequirements(elevator);

    }


    @Override
    public void initialize(){
        canRun=elevator.isUp||!elevator.topSwitch.isOk();
    }


    @Override
    public void execute(){
        elevator.moveElevator(Constants.elevator.elevatorStayAtTopSpeed);
    }

    @Override public void end(boolean wasInterupted){
        elevator.moveElevator(0);
    }

    @Override
    public boolean isFinished(){
      
      return super.isFinished()||!canRun;
    }
  


}
