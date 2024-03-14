package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Intake;

public class intake extends SequentialCommandGroup{
    public intake(Intake intake){
        super(new IntakeMain(intake));
    }
}
