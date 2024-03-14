package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;

public class ElevatorToggle extends SequentialCommandGroup {
    
    final Elevator elevator;
    double speed;
    

    public ElevatorToggle(Elevator elevator) {
        this.elevator = elevator;

        addCommands(new ElevatorToggleMain(elevator), new ElevatorToggleBack(elevator, Constants.elevator.elevatorBackupTime));
    }
}
