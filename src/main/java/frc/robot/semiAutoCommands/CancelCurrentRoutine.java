package frc.robot.semiAutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.controlInitalizer;
import frc.robot.semiAutoManager;

public class CancelCurrentRoutine extends InstantCommand{
    public CancelCurrentRoutine(){
        addRequirements(semiAutoManager.hasSemiAutoPerm);
    }
}
