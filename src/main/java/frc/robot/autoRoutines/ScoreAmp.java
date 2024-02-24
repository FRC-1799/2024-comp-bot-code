package frc.robot.autoRoutines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.ElevatorToggle;
import frc.robot.commands.RepetitiveIntake;
import frc.robot.commands.RepetitiveOutake;
import frc.robot.commands.TurnTo;
import frc.robot.commands.WristMove;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WristIntake;

public class scoreAmp extends SequentialCommandGroup{
    scoreAmp(DriveBase drive, Gyro gyro, Elevator elevator, WristIntake wrist, Intake intake){
        super(
            new DriveStraight(drive, 1.93294),

            new TurnTo(drive, -90, gyro),

            new ParallelCommandGroup(
                new ElevatorToggle(elevator),
                new WristMove(wrist, Constants.auto.scoreAmp)
            ),
            new RepetitiveOutake(intake),

            new TurnTo(drive, 141.5, gyro),

            new ParallelRaceGroup(
                new DriveStraight(drive, 1.55),
                new RepetitiveIntake(intake)
            ),
            new ParallelCommandGroup(
                new TurnTo(drive, 180, gyro),
                new ElevatorToggle(elevator)
            ),

            new DriveStraight(drive, 3),

            new TurnTo(drive, 141.5, gyro),

            new RepetitiveOutake(intake)
        );
    }
}
