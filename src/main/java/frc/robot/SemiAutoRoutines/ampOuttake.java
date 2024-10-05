package frc.robot.SemiAutoRoutines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.semiAutoManager;
import frc.robot.commands.ElevatorCommands.elevatorMoveTo;
import frc.robot.commands.IntakeCommands.Outtake;
import frc.robot.commands.WristComands.WristMove;
import frc.robot.commands.WristComands.wristReset;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WristIntake;

public class ampOuttake extends SequentialCommandGroup{
    public ampOuttake(Elevator elevator, WristIntake wrist, Intake intake){
        super(
            new Outtake(intake),
            new ParallelCommandGroup(
                new elevatorMoveTo(elevator, false),
                new wristReset(wrist)    
            )
        );
        addRequirements(semiAutoManager.hasSemiAutoPerm);
    }

    
}
