package frc.robot.SemiAutoRoutines;

import edu.wpi.first.wpilibj2.command.RepeatCommand;
import frc.robot.semiAutoCommands.danceActor;
import frc.robot.subsystems.DriveBase;

public class danceCaller extends RepeatCommand{
    public danceCaller(DriveBase drive){
        super(new danceActor(drive));
    }
}
