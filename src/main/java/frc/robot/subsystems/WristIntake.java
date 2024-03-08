package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristIntake extends SubsystemBase {
	public final CANSparkMax wristMotor;
    public final limitSwitch wristLimitSwitch;
    public final RelativeEncoder encoder;

    

    public WristIntake(){
        wristLimitSwitch = new limitSwitch(Constants.DIO.Port_20);
        wristMotor = new CANSparkMax(Constants.CAN.Port_10, MotorType.kBrushless);
        encoder = wristMotor.getEncoder();

        encoder.setPositionConversionFactor(Constants.wrist.gearRatio/360);
    }

	public void move(double speed){
        SmartDashboard.putNumber("speed", speed);
    	wristMotor.set(speed);
  	}

    public void resetEncoder(){
        encoder.setPosition(0);
    }

    public double getEncoder(){
        return encoder.getPosition();
    }

    public void stop(){
        move(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("wristencoder2", getEncoder());
        if (wristLimitSwitch.getVal()){
            resetEncoder();
        }
    }
}


