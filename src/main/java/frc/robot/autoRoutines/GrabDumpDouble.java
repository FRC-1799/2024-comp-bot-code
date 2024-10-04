package frc.robot.autoRoutines;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.FeildPosits;
import frc.robot.Constants.drive;
import frc.robot.commands.IntakeCommands.IntakeMain;
import frc.robot.commands.IntakeCommands.OuttakeMain;
import frc.robot.semiAutoCommands.DriveToPoint;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WristIntake;

public class GrabDumpDouble extends SequentialCommandGroup{
    public GrabDumpDouble(DriveBase drive, Intake intake, WristIntake wrist){
        super(
            new OuttakeMain(intake),
            new ParallelRaceGroup(
                new DriveToPoint(drive, FeildPosits.midNote5), new IntakeMain(intake)
            ),
            new DriveToPoint(drive , FeildPosits.getIn),
            new OuttakeMain(intake),
            new ParallelRaceGroup(
                new DriveToPoint(drive, FeildPosits.midNote4), new IntakeMain(intake)
            ),
            new DriveToPoint(drive, FeildPosits.getIn)
        );
        
    }
}
