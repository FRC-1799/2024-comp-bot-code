package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;

public class ElevatorToggle extends SequentialCommandGroup {

    public ElevatorToggle(Elevator elevator) {
        super(new ElevatorToggleMain(elevator));
    }
}
