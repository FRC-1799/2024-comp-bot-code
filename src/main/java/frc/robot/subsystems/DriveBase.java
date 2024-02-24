package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.semiAutoManager;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.None;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveBase extends SubsystemBase {
  // left side
  public final CANSparkMax sparkMaxLeftBack = new CANSparkMax(Constants.drive.leftBackMotor, MotorType.kBrushless);
  public final CANSparkMax sparkMaxLeftFront = new CANSparkMax(Constants.drive.leftFrontMotor, MotorType.kBrushless);

  //right side
  public final CANSparkMax sparkMaxRightBack = new CANSparkMax(Constants.drive.rightBackMotor, MotorType.kBrushless);
  public final CANSparkMax sparkMaxRightFront = new CANSparkMax(Constants.drive.rightFrontMotor, MotorType.kBrushless);


  public final RelativeEncoder encoderR;
  public final RelativeEncoder encoderL;

  private final Field2d field = new Field2d();






  final MotorControllerGroup leftMotors = new MotorControllerGroup(
    sparkMaxLeftFront, sparkMaxLeftBack
      );

  final MotorControllerGroup rightMotors = new MotorControllerGroup(
    sparkMaxRightFront, sparkMaxRightBack

      );

  final DifferentialDrive m_RobotDrive;

  public DriveBase() {


    //left voltage ramping
    encoderR=sparkMaxRightFront.getEncoder();    
    encoderL= sparkMaxLeftFront.getEncoder();
    sparkMaxLeftBack.setInverted(true);
    sparkMaxLeftFront.setInverted(true);

    sparkMaxLeftBack.setOpenLoopRampRate(Constants.drive.rampspeed);
    sparkMaxLeftFront.setOpenLoopRampRate(Constants.drive.rampspeed);


    encoderL.setPositionConversionFactor(Constants.drive.encoderToMetersRatio);
    encoderR.setPositionConversionFactor(Constants.drive.encoderToMetersRatio);

    

;


    //leftMotors.setInverted(true);
    //m_RobotDrive = new DifferentialDrive(rightMotors, leftMotors)
    m_RobotDrive = new DifferentialDrive(leftMotors, rightMotors);
    resetEncoder();
    

    addChild("Drive", m_RobotDrive);
    SmartDashboard.putNumber("encoder", (getEncoderAvrg()));
  }

  public void resetEncoder(){
    encoderL.setPosition(0);
    encoderR.setPosition(0);
  }

  public void changeRatio(double ratio){
    encoderL.setVelocityConversionFactor(ratio);
    encoderR.setVelocityConversionFactor(ratio);
  }


  public void drive(final double ySpeed, final double rotateValue) {
    SmartDashboard.putNumber("encoder", getEncoderAvrg());
    m_RobotDrive.arcadeDrive(ySpeed, rotateValue);

  }


  public double getEncoderAvrg(){
    return (getRightEncoder()+getLeftEncoder())/2;
  }

  public double getRightEncoder(){
    return encoderR.getPosition();
  }

  public double getLeftEncoder(){
    return encoderL.getPosition();
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    SmartDashboard.putNumber("leftVolt", leftVolts);
    sparkMaxLeftFront.setVoltage(leftVolts);
    SmartDashboard.putNumber("rightVolts", rightVolts);
    sparkMaxRightFront.setVoltage(rightVolts);
    SmartDashboard.putNumber("voltageDiff", leftVolts-rightVolts);
    m_RobotDrive.feed();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(encoderL.getVelocity()*60, encoderR.getVelocity()*60);
  }

  public void shift(boolean isLow){
    if (isLow){
      changeRatio(Constants.drive.gearRatioHigh);
    } 
    else{
      changeRatio(Constants.drive.gearRatioLow);
    }
  }

  public void periodic() {
    field.setRobotPose(semiAutoManager.getCoords());
    SmartDashboard.putData("Field", field);
  }

}
