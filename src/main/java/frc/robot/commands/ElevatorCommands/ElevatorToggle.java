package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;

public class ElevatorToggle extends ParallelCommandGroup {
    
    final Elevator elevator;
    double speed;
    

    public ElevatorToggle(Elevator elevator) {
        this.elevator = elevator;


        addCommands(new ElevatorToggleBack(elevator, Constants.elevator.elevatorBackupTime), new ElevatorToggleMain(elevator));
    }
}
