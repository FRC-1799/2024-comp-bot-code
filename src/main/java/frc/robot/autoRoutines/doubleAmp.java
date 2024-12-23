package frc.robot.autoRoutines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.FeildPosits;
import frc.robot.Constants;
import frc.robot.Constants.elevator;
import frc.robot.SemiAutoRoutines.ScoreAmp;
import frc.robot.SemiAutoRoutines.scoreAmpRunner;
import frc.robot.SemiAutoRoutines.turnAround;
import frc.robot.commands.ElevatorCommands.elevatorMoveTo;
import frc.robot.commands.IntakeCommands.intake;
import frc.robot.commands.WristComands.WristMoveAuto;
import frc.robot.commands.WristComands.WristMoveHold;
import frc.robot.commands.WristComands.wristReset;
import frc.robot.semiAutoCommands.DriveToPoint;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WristIntake;

public class doubleAmp extends SequentialCommandGroup{
    public doubleAmp(DriveBase drive, Elevator elevator, Intake intake, WristIntake wrist){
        super(
            new ScoreAmp(drive, elevator, intake, wrist),

            new ParallelRaceGroup(
                new SequentialCommandGroup(
                    new turnAround(drive),
                    new DriveToPoint(drive, FeildPosits.startingNotes.rightNote)
                ),
                new SequentialCommandGroup(
                    new ParallelCommandGroup(
                        new elevatorMoveTo(elevator, false),
                        new wristReset(wrist)
                    ),
                    new WristMoveAuto(wrist, Constants.wrist.positions.intake),
                    new intake(intake)
                )
            ),
            new ScoreAmp(drive, elevator, intake, wrist)
        


        );
    }
}
