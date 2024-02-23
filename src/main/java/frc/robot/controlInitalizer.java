package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Midi;
import frc.robot.subsystems.ShiftableGearbox;

//object to deal with all ofthe dirty work of multiple control schemes
public class controlInitalizer {

  
    final DriveBase m_driveSubsystem;

    final ShiftableGearbox gearBox;
    final raisIntake

    public controlInitalizer(
        DriveBase m_driveSubsystem, ShiftableGearbox gearBox){
        this.gearBox=gearBox;
        this.m_driveSubsystem=m_driveSubsystem;


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

        coPoilot.getButtonFromDict("button1").buttonTrigger.onFalse();
        
    }
    public final void initalizeMIDIControl(Midi midi){
        m_driveSubsystem.setDefaultCommand(
            new ArcadeDrive(
                  m_driveSubsystem,
                  () -> (midi.getButtonFromDict("slider1").getValAsOneToNegOne()),
                  () -> (midi.getButtonFromDict("sliderAB").getValAsOneToNegOne())
                  ));

       midi.getButtonFromDict("button1").buttonTrigger.whileTrue(runIntake);
    }


}
