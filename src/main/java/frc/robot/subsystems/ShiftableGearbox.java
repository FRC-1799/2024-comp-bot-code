package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShiftableGearbox extends SubsystemBase{
  
  PneumaticHub hub = new PneumaticHub(Constants.pneumatics.hubID);

private final DoubleSolenoid shifter = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.pneumatics.forwardPort, Constants.pneumatics.reversePort);
  Compressor compressor = hub.makeCompressor();

  public ShiftableGearbox(){
    compressor.enableDigital();
  }


  public void toggleCompressor(){
    if(compressor.isEnabled()){
      compressor.disable();
      // display Compressor Data
    }else{
      compressor.enableDigital();
    }
  }

  public void shift(boolean isOn){
    if (isOn){
      shifter.set(DoubleSolenoid.Value.kForward);  
    }
    else{
      shifter.set(DoubleSolenoid.Value.kReverse);
    }
  }



}
