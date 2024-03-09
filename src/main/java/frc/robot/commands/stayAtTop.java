package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.limitSwitch;

public class stayAtTop extends Command {
    limitSwitch topSwitch;
    Elevator elevator;

    public stayAtTop(Elevator elevator){
        this.elevator=elevator;
        this.topSwitch=elevator.topSwitch;
    }

    public void initialize(){
        checkRequirements();
    }

    @Override
    public void execute(){
        if (!topSwitch.getVal()){
            elevator.moveElevator(Constants.elevator.elevatorStayAtTopSpeed);
        }
    }

    @Override
    public void end(boolean wasInterupted){
        elevator.moveElevator(0);
    }

    public void checkRequirements(){
        boolean status=true;
        
        if (!elevator.isUp){
            status=false;
        }

        if (!status){
            CommandScheduler.getInstance().cancel(this);
        }

    }
}
