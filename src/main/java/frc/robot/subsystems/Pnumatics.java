package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.DifferentialDriveWheelPositions;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pnumatics extends SubsystemBase{
  
  PneumaticHub hub = new PneumaticHub(Constants.pneumatics.hubID);

  DoubleSolenoid shifter = hub.makeDoubleSolenoid(Constants.pneumatics.solenoidPortA, Constants.pneumatics.solenoidPortB);
  Compressor compressor = hub.makeCompressor();
  DriveBase drive;
  Solenoid ClimberPiston = hub.makeSolenoid(Constants.pneumatics.elevatorPistonID);
  int count=0;
  
  public Pnumatics(DriveBase drive){
    compressor.enableDigital();
    this.drive=drive;
  }


  public void toggleCompressor(){
    if(compressor.isEnabled()){
      compressor.disable();
      // display Compressor Data
    }else{
      compressor.enableDigital();
    }
  }

  public void shift(boolean isHigh){
    shifter.set(DoubleSolenoid.Value.kForward);

    // SmartDashboard.putBoolean("pnumatics2", isHigh);
    // count++;
    // SmartDashboard.putNumber("count", count);
    // //drive.shift(isHigh);
    // if (isHigh){
    //   shifter.set(DoubleSolenoid.Value.kForward);
    // } 
    // else{
    //     shifter.set(DoubleSolenoid.Value.kReverse);
    // }
  }


  public void extendClimber(){
    ClimberPiston.set(true);
  }



}
