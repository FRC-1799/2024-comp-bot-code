package frc.robot.SemiAutoRoutines;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.FeildPosits;
import frc.robot.semiAutoManager;
import frc.robot.semiAutoCommands.DriveToPoint;
import frc.robot.semiAutoCommands.stealDriveCommand;
//import frc.robot.commands.testDriveStraight;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WristIntake;


public class testRoutine extends SequentialCommandGroup{
public testRoutine(DriveBase drive, Elevator intakeElevator, Intake intake, WristIntake wrist){        
        super(
            //Init
            

            //MainLoop
            new ParallelCommandGroup(
                new scoreAmpPosit(intakeElevator, wrist)
            ),
            //Ending set
            new ampOuttake(intakeElevator, wrist, intake)

            // new ParallelRaceGroup(
            //     new stealDriveCommand(drive)
            // )

            //passOff
        );
        //addRequirements(semiAutoManager.hasSemiAutoPerm);


    }
}
