package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants;
public class Elevator extends SubsystemBase {
	public final CANSparkMax motorLeft = new CANSparkMax(Constants.elevator.motorPortLeft, MotorType.kBrushless);
	public final CANSparkMax motorRight = new CANSparkMax(Constants.elevator.motorPortRight, MotorType.kBrushless);
	public final MotorControllerGroup armMotors = new MotorControllerGroup(motorLeft, motorRight);
	
	public final limitSwitch topSwitch = new limitSwitch(Constants.elevator.topLimitSwitch);
	public final limitSwitch bottomSwitch = new limitSwitch(Constants.elevator.bottomLimitSwitch);
	public boolean isUp = false;
	


	public void moveElevator(double speed){
		SmartDashboard.putNumber("eleatorSpeed", speed);
    	armMotors.set(speed);
  	}

	public void stop(){
    	armMotors.set(0);
  	}

	public boolean checkOkLimitSwitches(){
		return topSwitch.isOk() && bottomSwitch.isOk();
	}
	public void periodic(){
		SmartDashboard.putBoolean("elvator is up", isUp);
		SmartDashboard.putBoolean("elevatorUpSwitch", topSwitch.getVal());
		SmartDashboard.putBoolean("elevatorDownSwitch", bottomSwitch.getVal());

		//SmartDashboard.putBoolean("elvator is up", isUp);


	}

}
