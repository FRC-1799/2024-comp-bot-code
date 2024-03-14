package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Intake;

public class outtake extends SequentialCommandGroup{
    public outtake(Intake intake){
        super(new OuttakeMain(intake),new OuttakeBack(intake));
    }
}
