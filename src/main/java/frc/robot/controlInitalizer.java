package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.SemiAutoRoutines.*;
import frc.robot.commands.*;
import frc.robot.semiAutoCommands.CancelCurrentRoutine;

import frc.robot.commands.DriveCommands.*;
import frc.robot.commands.ElevatorCommands.*;
import frc.robot.commands.IntakeCommands.*;

import frc.robot.commands.WristComands.*;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;


import frc.robot.subsystems.Pnumatics;
import frc.robot.subsystems.SpeakerShooter;

import frc.robot.subsystems.Midi;

import frc.robot.subsystems.WristIntake;


//object to deal with all ofthe dirty work of multiple control schemes
public class controlInitalizer {


    static DriveBase driveSubsystem;
    static Pnumatics gearBox;
    static WristIntake wrist;
    static Intake intake;
    static Elevator elevator;

    static boolean hasBeenInitalizedFromRobot=false;
    static boolean hasBeenInitalizedFromSemiAutoManager=false;
    public static Command testRoutine;
    public static SpeakerShooter shooter;


    public static void controlInitalizerFromRobot(
        DriveBase DriveSubsystem, Pnumatics GearBox, WristIntake Wrist, Intake Intake, Elevator Elevator, SpeakerShooter Shooter){
        hasBeenInitalizedFromRobot=true;
        gearBox=GearBox;
        driveSubsystem=DriveSubsystem;
        wrist = Wrist;
        intake = Intake;
        shooter=Shooter;
        elevator = Elevator;


    }

    public static void controlInitalizerFromSemiAutoManager(){
        hasBeenInitalizedFromSemiAutoManager=true;

    }


    public static void checkInit(){
        if (! hasBeenInitalizedFromSemiAutoManager || ! hasBeenInitalizedFromRobot){
            throw new Error("control initalizer was not properly initalized!");
        }
    }

    public static final void configureTwoControllersBasic( CommandXboxController movementController, CommandXboxController manipulatorController){
        checkInit();

        driveSubsystem.setDefaultCommand(
        new ArcadeDrive(
              driveSubsystem,
              () -> ((-movementController.getLeftTriggerAxis() + movementController.getRightTriggerAxis())),
              () -> (movementController.getLeftX() )
        ));

      

    }

    public static final void configureOneControllersBasic(CommandXboxController controller){
        checkInit();

        driveSubsystem.setDefaultCommand(
            new ArcadeDrive(
                  driveSubsystem,
                  () -> ((-controller.getLeftTriggerAxis() + controller.getRightTriggerAxis())),
                  () -> (controller.getLeftX() )
            ));

        
    }



    public static final void initalizeJaceControllWithSecondController(CommandXboxController movementController, CommandXboxController manipulatorController){
        driveSubsystem.setDefaultCommand(

            new ArcadeDrive(
                  driveSubsystem,
                  () -> ( movementController.getLeftY()),
                  () -> (-movementController.getRightX())
            ));


        // gearBox.setDefaultCommand(
        //     new shiftGears(() -> ( movementController.rightTrigger().getAsBoolean()), gearBox));

        //movementController.x().onTrue(new shiftGears(false, gearBox)).onFalse(new shiftGears(true, gearBox));
        // shooter.setDefaultCommand(
        //     new SpeakerManual(shooter, () -> (movementController.getRightTriggerAxis()), () -> movementController.getLeftTriggerAxis())
        // );
        //movementController.leftTrigger().onTrue(new WristMove(wrist, Constants.wrist.positions.intake));
        // movementController.a().whileTrue(new IntakeNote(intake));
        // movementController.b().whileTrue(new ShootNote(intake));
        movementController.a().onTrue(new intakeCommand(intake));
        movementController.x().onTrue(new outtake(intake));
        movementController.rightBumper().onTrue(new ElevatorToggle(elevator));
        movementController.y().onTrue(new WristMove(wrist, Constants.wrist.positions.amp));
        //manipulatorController.rightTrigger().onTrue(new WristMove(wrist, Constants.wrist.positions.intake));
        //manipulatorController.leftTrigger().onTrue(new WristMove(wrist, Constants.wrist.positions.up));
        movementController.rightTrigger().onTrue(new scoreAmpPosit(elevator, wrist));
        movementController.b().onTrue(new wristReset(wrist));
        //movementController.leftBumper().onTrue(new ampOuttakeRunner(elevator, wrist, intake));
        //movementController.leftBumper().onTrue(new climb(elevator));
        //movementController.y().onTrue(new shootSpeaker(shooter));
        //movementController.b().onTrue(new ShootSpeakerMain(shooter));   
        //movementController.leftBumper().onTrue(new testEverything(intake, wrist, elevator));   

        

        //movementController.rightTrigger().onTrue(new climb(elevator));


        // movementController.leftTrigger().onTrue(new WristMove(wrist, Constants.wrist.positions.intake));
        // //movementController.a().whileTrue(new IntakeNote(intake));
        // //movementController.b().whileTrue(new ShootNote(intake));
        // movementController.a().onTrue(new IntakeMain(intake));
        // movementController.b().onTrue(new OuttakeMain(intake));
        // movementController.rightBumper().whileTrue(new ElevatorToggle(elevator));
        // movementController.leftBumper().whileTrue(new ElevatorToggle(elevator));
        // movementController.y().onTrue(new wristReset(wrist));
        // movementController.povUp().whileTrue(new stayAtTopMain(elevator));
        

    }


    public static final void initalizeMIDIAloneControl(Midi midi){
        checkInit();

        driveSubsystem.setDefaultCommand(
            new ArcadeDrive(
                  driveSubsystem,
                  () -> (midi.getButtonFromDict("slider1").getValAsOneToNegOne()),
                  () -> (midi.getButtonFromDict("sliderAB").getValAsOneToNegOne())
                  ));

       //midi.getButtonFromDict("button1").buttonTrigger.whileTrue(runIntake);
    }

    public static final void jaceControllWithMidi(CommandXboxController movementController, Midi midi){
        checkInit();

        driveSubsystem.setDefaultCommand(
            new ArcadeDrive(
                  driveSubsystem,
                  () -> ( movementController.getLeftY()),
                  () -> (-movementController.getRightX())
            ));
        
        gearBox.setDefaultCommand(
            new shiftGears(() -> (!movementController.rightTrigger().getAsBoolean()), gearBox));

         movementController.y().onTrue(new toggleCompressor(gearBox));
 
        //midi.getButtonFromDict("button1").buttonTrigger.onFalse(new grabNoteFromGroundRunner(driveSubsystem, elevator, intake, wrist));
        //midi.getButtonFromDict("button2").buttonTrigger.onFalse(new scoreAmpRunner(driveSubsystem, elevator, intake, wrist));
        midi.getButtonFromDict("button1").buttonTrigger.onFalse(new scoreAmpPosit(elevator, wrist));
        midi.getButtonFromDict("button2").buttonTrigger.onFalse(new autoIntake(elevator, wrist, intake));

        midi.getButtonFromDict("button3").buttonTrigger.onFalse(new intakeCommand(intake));
        midi.getButtonFromDict("button4").buttonTrigger.onFalse(new outtake(intake));
        midi.getButtonFromDict("button5").buttonTrigger.onFalse(new WristMove(wrist, Constants.wrist.positions.intake));
        midi.getButtonFromDict("button6").buttonTrigger.onFalse(new WristMove(wrist, Constants.wrist.positions.amp));
        midi.getButtonFromDict("button7").buttonTrigger.onFalse(new WristMove(wrist, Constants.wrist.positions.up));
        midi.getButtonFromDict("button8").buttonTrigger.onFalse(new wristReset(wrist));

        midi.getButtonFromDict("replay").buttonTrigger.onFalse(new ampOuttake(elevator, wrist, intake));
        midi.getButtonFromDict("leftSilverDial").buttonTrigger.onTrue(new ElevatorToggle(elevator));
        midi.getButtonFromDict("rightSilverDial").buttonTrigger.onFalse(new climb(elevator));
        
        
    }

    public static final void autoDriveTest(CommandXboxController controller){
        driveSubsystem.setDefaultCommand(
            new ArcadeDrive(
                  driveSubsystem,
                  () -> ( controller.getLeftY()),
                  () -> (-controller.getRightX())
            )); 


        controller.x().onFalse(new testRoutine(driveSubsystem, elevator, intake, wrist));
        controller.a().onFalse(new ScoreAmp(driveSubsystem, elevator, intake, wrist));
        controller.b().onFalse(new grabNoteGround(driveSubsystem, elevator, intake, wrist));
        controller.leftTrigger().onTrue(new autoIntake(elevator, wrist, intake));
        controller.y().onFalse(new ampOuttake(elevator, wrist, intake));
        controller.rightTrigger().onTrue(new ElevatorToggle(elevator));
        controller.rightBumper().onTrue(new wristReset(wrist));
        controller.leftBumper().onTrue(new scoreAmpPosit(elevator, wrist));

        //controller.rightTrigger().onTrue(new shiftGears(true, gearBox)).onFalse(new shiftGears(false, gearBox));
    }

    public static final void newCompControls(CommandXboxController movementController, CommandXboxController manipController){
        checkInit();

        driveSubsystem.setDefaultCommand(
            new ArcadeDrive(
                driveSubsystem,
                () -> ( movementController.getLeftY()),
                () -> (-movementController.getRightX())
            ));
    
        gearBox.setDefaultCommand(
            new shiftGears(() -> (!movementController.rightTrigger().getAsBoolean()), gearBox));
        

        manipController.a().onFalse(new scoreAmpPosit(elevator, wrist));
        manipController.x().onFalse(new autoIntake(elevator, wrist, intake));
        manipController.y().onFalse(new ampOuttake(elevator, wrist, intake));
        manipController.b().onFalse(new wristReset(wrist));
        manipController.rightTrigger().onFalse(new ElevatorToggle(elevator));
        manipController.leftTrigger().onFalse(new climb(elevator));
        manipController.rightBumper().onFalse(new intakeCommand(intake));
        manipController.leftBumper().onFalse(new outtake(intake));

    }

  



}
