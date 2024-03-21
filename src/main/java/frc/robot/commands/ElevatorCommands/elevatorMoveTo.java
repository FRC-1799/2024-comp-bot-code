package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Elevator;

public class elevatorMoveTo extends InstantCommand{
    Elevator elevator;
    boolean goingUp;

    public elevatorMoveTo(Elevator elevator, boolean goingUp){
        this.elevator=elevator;
        this.goingUp=goingUp;
    }

    @Override
    public void execute(){
        if (goingUp!=elevator.isUp){
            new ElevatorToggle(elevator).schedule();;
        }
    }
}
