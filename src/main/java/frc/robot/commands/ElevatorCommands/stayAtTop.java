package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elevator;

public class stayAtTop extends SequentialCommandGroup { 
    public stayAtTop(Elevator elevator) {
        super(new stayAtTopMain(elevator), new stayAtTopBack(elevator));
    }
}
