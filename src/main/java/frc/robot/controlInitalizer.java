package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Midi;
import frc.robot.subsystems.ShiftableGearbox;
import frc.robot.subsystems.WristIntake;

//object to deal with all ofthe dirty work of multiple control schemes
public class controlInitalizer {

  
    final DriveBase m_driveSubsystem;

    final ShiftableGearbox gearBox;
    final WristIntake wrist;
    final WristMove stowed;
    final WristMove scoreAmp;
    final WristMove intakingPosition;
    final ElevatorToggle elevatorToggle;
    final RepetitiveOutake outake;
    final RepetitiveIntake intake;
    final WaitIntakeNote holdToIntake;
    final WaitIntakeNote holdToOuttake;
    final 

    public controlInitalizer(DriveBase m_driveSubsystem, ShiftableGearbox gearBox, WristIntake wrist, Elevator elevator, Intake intake){
        this.gearBox=gearBox;
        this.m_driveSubsystem=m_driveSubsystem;
        this.wrist = wrist;
        stowed = new WristMove(wrist, 0);
        scoreAmp = new WristMove(wrist, Constants.wrist.ampScoreEncoderVal);
        intakingPosition = new WristMove(wrist, Constants.wrist.intakeEncoderVal);
        this.elevatorToggle=new ElevatorToggle(elevator);
        outake = new RepetitiveOutake(intake);
        this.intake = new RepetitiveIntake(intake);
        holdToIntake = new WaitIntakeNote(intake, Constants.intake.intakeSpeeds.intakeSpeed);
        holdToOuttake = new WaitIntakeNote(intake, Constants.intake.intakeSpeeds.outakeSpeed);
        
        
    }


    public final void configureTwoControllersBasic( CommandXboxController movementController, CommandXboxController manipulatorController){
        m_driveSubsystem.setDefaultCommand(
        new ArcadeDrive(
              m_driveSubsystem,
              () -> ((-movementController.getLeftTriggerAxis() + movementController.getRightTriggerAxis())),
              () -> (-movementController.getLeftX() )
        ));


    }

    public final void configureOneControllersBasic(CommandXboxController controller){
        m_driveSubsystem.setDefaultCommand(
            new ArcadeDrive(
                  m_driveSubsystem,
                  () -> ((-controller.getLeftTriggerAxis() + controller.getRightTriggerAxis())),
                  () -> (-controller.getLeftX() )
            ));

        
    }

    public final void initalizeJaceControllWithSecondController(CommandXboxController movementController, CommandXboxController manipulatorController){
        m_driveSubsystem.setDefaultCommand(
            new ArcadeDrive(
                  m_driveSubsystem,
                  () -> ( movementController.getLeftY()),
                  () -> (-movementController.getRightX())
            ));
        movementController.rightTrigger().onTrue(new shiftGears(false, gearBox)).onFalse(new shiftGears(true, gearBox));
    }
    public final void initalizeJaceControllWithMidi(CommandXboxController movementController, Midi coPoilot){
        m_driveSubsystem.setDefaultCommand(
            new ArcadeDrive(
                  m_driveSubsystem,
                  () -> ( movementController.getLeftY()),
                  () -> (-movementController.getRightX())
            ));

        coPoilot.getButtonFromDict("button1").buttonTrigger.onFalse(stowed);
        coPoilot.getButtonFromDict("button2").buttonTrigger.onFalse(scoreAmp);
        coPoilot.getButtonFromDict("button3").buttonTrigger.onFalse(intakingPosition);
        coPoilot.getButtonFromDict("button4").buttonTrigger.onFalse(elevatorToggle);
        coPoilot.getButtonFromDict("button5").buttonTrigger.onFalse(null);
        //movementController.leftTrigger().onTrue(new WristMove(wrist, 100));
        
    }
    public final void initalizeMIDIControl(Midi midi){
        m_driveSubsystem.setDefaultCommand(
            new ArcadeDrive(
                  m_driveSubsystem,
                  () -> (midi.getButtonFromDict("slider1").getValAsOneToNegOne()),
                  () -> (midi.getButtonFromDict("sliderAB").getValAsOneToNegOne())
                  ));

       //midi.getButtonFromDict("button1").buttonTrigger.whileTrue(runIntake);
    }



}
