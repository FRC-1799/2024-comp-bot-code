package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.limitSwitch;

public class StayAtTop extends Command {
    limitSwitch topSwitch;
    Elevator elevator;

    public StayAtTop(Elevator elevator){
        this.elevator=elevator;
        this.topSwitch=elevator.topSwitch;
    }

    @Override
    public void execute(){
        elevator.moveElevator(Constants.elevator.elevatorStayAtTopSpeed);
    }

    @Override
    public void end(boolean wasInterupted){
        elevator.moveElevator(0);
    }
}
