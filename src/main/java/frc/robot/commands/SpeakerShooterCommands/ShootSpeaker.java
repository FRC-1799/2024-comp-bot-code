package frc.robot.commands.SpeakerShooterCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.SpeakerShooter;

public class shootSpeaker extends SequentialCommandGroup{
    public shootSpeaker(SpeakerShooter shooter){
        super(new shooterIntakeMain(shooter), new ShootSpeakerBack(shooter));
    }
}
