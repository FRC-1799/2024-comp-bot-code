package frc.robot.commands.SpeakerShooterCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.speakerShooter;
import frc.robot.subsystems.SpeakerShooter;

public class shootIntake extends SequentialCommandGroup{
    public shootIntake(SpeakerShooter shooter){
        super(new shooterIntakeMain(shooter), new shooterIntakeBack(shooter));
    }
}
