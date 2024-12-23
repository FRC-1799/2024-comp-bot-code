package frc.robot.SemiAutoRoutines;

import java.util.List;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.FeildPosits;
import frc.robot.semiAutoManager;
import frc.robot.commands.ToggleElevator;
import frc.robot.commands.ElevatorCommands.elevatorMoveTo;
import frc.robot.commands.IntakeCommands.OuttakeMain;
import frc.robot.commands.WristComands.WristMoveAuto;
import frc.robot.commands.WristComands.wristReset;
import frc.robot.semiAutoCommands.BlinkinGreen;
import frc.robot.semiAutoCommands.BlinkinRed;
import frc.robot.semiAutoCommands.BlinkinYellow;
import frc.robot.semiAutoCommands.DriveToPoint;
import frc.robot.semiAutoCommands.stealDriveCommand;
import frc.robot.subsystems.Blinkin;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WristIntake;




public class ScoreAmp extends SequentialCommandGroup {
    public ScoreAmp(DriveBase drive, Elevator intakeElevator, Intake intake, WristIntake wrist){        
        super(
            //Init
            

            //MainLoop
            new ParallelCommandGroup(

                new DriveToPoint(drive, FeildPosits.ampScore),
                new elevatorMoveTo(intakeElevator, true),
                new wristReset(wrist)


            ),

            new WristMoveAuto(wrist, Constants.wrist.positions.amp),

            //Ending set
            new ParallelRaceGroup(
                new OuttakeMain(intake),
                new stealDriveCommand(drive)
            )

            //passOff
        );

    }
}
