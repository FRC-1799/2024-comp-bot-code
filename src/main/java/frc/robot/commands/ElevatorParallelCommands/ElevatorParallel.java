package frc.robot.commands.ElevatorParallelCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.ElevatorToggle;
import frc.robot.subsystems.Elevator;

public class ElevatorParallel extends ParallelCommandGroup {
    
    final Elevator elevator;
    double speed;
    

    public ElevatorParallel(Elevator elevator, double speed) {
        this.elevator = elevator;
        this.speed = speed;

        addCommands(new ElevatorMove(elevator, speed), new ElevatorToggle(elevator));
    }
}
