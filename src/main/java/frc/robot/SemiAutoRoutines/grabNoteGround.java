package frc.robot.SemiAutoRoutines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.FeildPosits;
import frc.robot.semiAutoManager;
import frc.robot.commands.IntakeCommands.intake;
import frc.robot.commands.WristComands.WristMoveAuto;
import frc.robot.commands.WristComands.wristReset;
import frc.robot.commands.ElevatorCommands.elevatorMoveTo;
import frc.robot.semiAutoCommands.DriveToPoint;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WristIntake;

public class grabNoteGround extends SequentialCommandGroup {
    public grabNoteGround(DriveBase drive, WristIntake wrist, Intake intake, Elevator elevator){
        super(
            new ParallelRaceGroup(
                new DriveToPoint(drive, FeildPosits.notePickup),
                new SequentialCommandGroup(
                    new ParallelCommandGroup(
                        new elevatorMoveTo(elevator, false),
                        new wristReset(wrist)
                    ),
                    new WristMoveAuto(wrist, Constants.wrist.positions.intake),
                    new intake(intake)
                )
            )
        );
    }
}
