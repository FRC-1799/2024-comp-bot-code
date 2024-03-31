package frc.robot.SemiAutoRoutines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.semiAutoManager;
import frc.robot.Constants.auto;
import frc.robot.commands.ElevatorCommands.elevatorMoveTo;
import frc.robot.commands.IntakeCommands.intake;
import frc.robot.commands.WristComands.WristMoveAuto;
import frc.robot.commands.WristComands.WristMoveSmart;
import frc.robot.commands.WristComands.wristReset;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WristIntake;

public class autoIntake extends SequentialCommandGroup{
    public autoIntake(Elevator elevator, WristIntake wrist, Intake intake){
        super(
            new ParallelCommandGroup(
                new elevatorMoveTo(elevator, false),
                new WristMoveAuto(wrist, Constants.wrist.positions.intake)
            ),
            new intake(intake),
            new wristReset(wrist)
        );
    }
}
