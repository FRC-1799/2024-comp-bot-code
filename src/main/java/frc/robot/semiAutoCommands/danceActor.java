package frc.robot.semiAutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.fieldPosits;
import frc.robot.FeildPosits;
import frc.robot.subsystems.DriveBase;

public class danceActor extends SequentialCommandGroup{
    public danceActor(DriveBase drive){
        super(
            new DriveToPoint(drive, FeildPosits.dancePosits.secondPosit),
            new DriveToPoint(drive, FeildPosits.dancePosits.firstPosit)
        );
    }
}
