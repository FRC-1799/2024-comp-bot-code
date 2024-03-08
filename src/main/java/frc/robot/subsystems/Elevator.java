package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
	public final CANSparkMax motorLeft = new CANSparkMax(Constants.CAN.Port_3, MotorType.kBrushless);
	public final CANSparkMax motorRight = new CANSparkMax(Constants.CAN.Port_4, MotorType.kBrushless);
	public final MotorControllerGroup armMotors = new MotorControllerGroup(motorLeft, motorRight);
	
	public final limitSwitch topSwitch = new limitSwitch(Constants.DIO.Port_1);
	public final limitSwitch bottomSwitch = new limitSwitch(Constants.DIO.Port_0);
	public boolean isUp = false;
	


	public void moveElevator(double speed){
    	armMotors.set(-speed);
  	}

}


