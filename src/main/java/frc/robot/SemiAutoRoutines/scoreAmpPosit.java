package frc.robot.SemiAutoRoutines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Constants.elevator;
import frc.robot.commands.ElevatorCommands.elevatorMoveTo;
import frc.robot.commands.ElevatorCommands.stayAtTop;
import frc.robot.commands.WristComands.WristMove;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.WristIntake;

public class scoreAmpPosit extends ParallelCommandGroup{
    public scoreAmpPosit(Elevator elevator, WristIntake wrist){
        super(
            new elevatorMoveTo(elevator, true),
            new WristMove(wrist, Constants.wrist.positions.amp)
        ); 
    }
}
