package frc.robot.SemiAutoRoutines;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.semiAutoManager;
import frc.robot.semiAutoCommands.DriveToPoint;
import frc.robot.subsystems.DriveBase;

public class turnAround extends SequentialCommandGroup{
    public turnAround(DriveBase drive){
        new DriveToPoint(drive, new Pose2d(semiAutoManager.getCoords().getX(), semiAutoManager.getCoords().getY(), new Rotation2d(semiAutoManager.getCoords().getRotation().getRadians()+Math.PI)));
    }
}
