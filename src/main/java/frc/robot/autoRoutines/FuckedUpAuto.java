package frc.robot.autoRoutines;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.units.Time;
import frc.robot.subsystems.DriveBase;
import frc.robot.Constants;


public class FuckedUpAuto extends WaitCommand{
    DriveBase driveBase;
    public FuckedUpAuto(DriveBase driveBase, double time) {
        super(time);
        this.driveBase=driveBase;
      }
      @Override
      public void execute() {
        driveBase.drive(Constants.drive.autoSpeed,0);
    }
}
