package frc.robot.SemiAutoRoutines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.FeildPosits;
import frc.robot.semiAutoManager;

import frc.robot.semiAutoCommands.DriveToPoint;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WristIntake;

public class grabNoteGround extends SequentialCommandGroup {
    public grabNoteGround(DriveBase drive, Elevator elevator, Intake intake, WristIntake wrist){
        super(
            new ParallelRaceGroup(
                new DriveToPoint(drive, FeildPosits.notePickup),
                new autoIntake(elevator, wrist, intake)
                
            )
        );
        addRequirements(semiAutoManager.hasSemiAutoPerm);

    }
}
