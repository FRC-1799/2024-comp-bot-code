package frc.robot.semiAutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.semiAutoManager;

public class routineValidator extends Command{
    public routineValidator(Command ToBeChecked){
        if(semiAutoManager.getCurrent()!=ToBeChecked){
            CommandScheduler.getInstance().cancel(ToBeChecked);
        }
    }
}
