package frc.robot;

import javax.management.modelmbean.ModelMBean;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.speakerShooter;
import frc.robot.Constants.intake.intakeNote;
import frc.robot.commands.testEverything;
import frc.robot.commands.DriveCommands.*;
import frc.robot.commands.ElevatorCommands.*;
import frc.robot.commands.IntakeCommands.*;
import frc.robot.commands.SpeakerShooterCommands.ShootSpeakerMain;
import frc.robot.commands.SpeakerShooterCommands.SpeakerManual;
import frc.robot.commands.SpeakerShooterCommands.shootIntake;
import frc.robot.commands.SpeakerShooterCommands.shootSpeaker;
import frc.robot.commands.SpeakerShooterCommands.shooterIntakeMain;
import frc.robot.commands.WristComands.*;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pnumatics;
import frc.robot.subsystems.SpeakerShooter;
import frc.robot.subsystems.WristIntake;

//object to deal with all ofthe dirty work of multiple control schemes
public class controlInitalizer {
    final DriveBase m_driveSubsystem;
    final Pnumatics gearBox;
    final WristIntake wrist;
    final Intake intake;
    final Elevator elevator;
    final SpeakerShooter shooter;

    public controlInitalizer(
        DriveBase m_driveSubsystem, Pnumatics gearBox, WristIntake wrist, Intake intake, Elevator elevator, SpeakerShooter shooter){
        this.gearBox=gearBox;
        this.m_driveSubsystem=m_driveSubsystem;
        this.wrist = wrist;
        this.intake = intake;
        this.elevator = elevator;
        this.shooter=shooter;


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

        // gearBox.setDefaultCommand(
        //     new shiftGears(() -> ( movementController.rightTrigger().getAsBoolean()), gearBox));

        //movementController.x().onTrue(new shiftGears(false, gearBox)).onFalse(new shiftGears(true, gearBox));
        // shooter.setDefaultCommand(
        //     new SpeakerManual(shooter, () -> (movementController.getRightTriggerAxis()), () -> movementController.getLeftTriggerAxis())
        // );
        //movementController.leftTrigger().onTrue(new WristMoveAuto(wrist, Constants.wrist.positions.intake));
        // movementController.a().whileTrue(new IntakeNote(intake));
        // movementController.b().whileTrue(new ShootNote(intake));
        movementController.a().onTrue(new intake(intake));
        movementController.x().onTrue(new outtake(intake));
        movementController.rightBumper().onTrue(new ElevatorToggle(elevator));
        movementController.y().onTrue(new WristMoveAuto(wrist, Constants.wrist.positions.amp));
        movementController.rightTrigger().onTrue(new WristMoveAuto(wrist, Constants.wrist.positions.intake));
        movementController.leftTrigger().onTrue(new WristMoveAuto(wrist, Constants.wrist.positions.up));
        movementController.b().onTrue(new wristReset(wrist));
        //movementController.y().onTrue(new shootSpeaker(shooter));
        //movementController.b().onTrue(new ShootSpeakerMain(shooter));   
        movementController.leftBumper().onTrue(new testEverything(intake, wrist, elevator));   


        //movementController.rightTrigger().onTrue(new climb(elevator));
    }
}
