package frc.robot.autoRoutines;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveBase;
import frc.robot.Constants;


public class AutoDriveStraight extends WaitCommand{
    DriveBase driveBase;
    public AutoDriveStraight(DriveBase driveBase, double time) {
      super(time);
      this.driveBase=driveBase;

      addRequirements(driveBase);
    }

    @Override
    public void execute() {
      super.execute();
      driveBase.drive(Constants.drive.autoSpeed,0);
    }

    @Override
    public void end(boolean interupted){
      super.end(interupted);
      driveBase.drive(0,0);
    }
}