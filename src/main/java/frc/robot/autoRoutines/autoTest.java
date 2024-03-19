package frc.robot.autoRoutines;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.DriveBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


public class autoTest extends SequentialCommandGroup {

    public autoTest (DriveBase drive, ArcadeDrive arcadeDrive, ArcadeDrive MotorControllerGroup){
        super(
           new DriveStraight(drive, 2),

           new WaitCommand(0.5)

            
        );
    }
}
