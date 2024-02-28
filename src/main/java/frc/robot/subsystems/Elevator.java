package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimitManager;

public class Elevator extends SubsystemBase {
	public final CANSparkMax motorLeft = new CANSparkMax(Constants.elevator.motorPortLeft, MotorType.kBrushless);
	public final CANSparkMax motorRight = new CANSparkMax(Constants.elevator.motorPortLeft, MotorType.kBrushless);
	public final MotorControllerGroup armMotors = new MotorControllerGroup(motorLeft, motorRight);
	
	public final limitSwitch topSwitch = LimitManager.getSwitch(Constants.elevator.topLimitSwitch);
	public final limitSwitch bottomSwitch = LimitManager.getSwitch(Constants.elevator.bottomLimitSwitch);
	public boolean isUp = false;
	


	public void moveElevator(double speed){
    	armMotors.set(speed);
  	}

}


