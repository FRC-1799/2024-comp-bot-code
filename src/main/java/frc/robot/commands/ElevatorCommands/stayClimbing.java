package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.limitSwitch;

public class stayClimbing extends Command {
    limitSwitch bottomSwitch;
    Elevator elevator;
    boolean canRun;

    public stayClimbing(Elevator elevator){
        this.elevator=elevator;
        this.bottomSwitch=elevator.bottomSwitch;
        addRequirements(elevator);
    }

    @Override
    public void initialize(){
        canRun=elevator.isUp||bottomSwitch.isOk();
 
    }

    @Override
    public void execute(){
        if (bottomSwitch.getVal()){
            elevator.moveElevator(Constants.elevator.elevatorStayClimbingSpeed);
        }
        else{
            elevator.moveElevator(0);
        }
    }

    @Override
    public void end(boolean wasInterupted){
        elevator.moveElevator(0);
    }

    public boolean isFinished(){
        return !canRun;
    }
}


