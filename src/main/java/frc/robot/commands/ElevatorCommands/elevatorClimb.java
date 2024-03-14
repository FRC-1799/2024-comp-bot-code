package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elevator;

public class elevatorClimb extends SequentialCommandGroup{
    public elevatorClimb(Elevator elevator){
        super(new climb(elevator), new climb(elevator));
    }   
}
