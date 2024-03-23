package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.ElevatorCommands.ElevatorToggle;
import frc.robot.commands.ElevatorCommands.climb;
import frc.robot.commands.IntakeCommands.intake;
import frc.robot.commands.IntakeCommands.outtake;
import frc.robot.commands.SpeakerShooterCommands.shootIntake;
import frc.robot.commands.WristComands.WristMove;
import frc.robot.commands.WristComands.wristReset;
import frc.robot.subsystems.*;

public class testEverything extends SequentialCommandGroup {
    public testEverything(Intake intake, WristIntake wrist, Elevator elevator){
        super( 
         new wristReset(wrist),
         new WristMove(wrist, Constants.wrist.positions.intake),
         new intake(intake), 
         new WristMove(wrist, Constants.wrist.positions.amp),
         new outtake(intake),
         new WristMove(wrist, Constants.wrist.positions.up),
         new ElevatorToggle(elevator),
         new ElevatorToggle(elevator),
         new ElevatorToggle(elevator),
         new climb(elevator)
        );
    }
}
