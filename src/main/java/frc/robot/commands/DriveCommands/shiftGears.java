package frc.robot.commands.DriveCommands;


import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Pnumatics;

public class shiftGears extends RunCommand {

    public shiftGears(BooleanSupplier isHigh, Pnumatics gearBox){
        super(
            ()->{
              SmartDashboard.putBoolean("Pnumatics", isHigh.getAsBoolean());
              gearBox.shift(isHigh.getAsBoolean());
            },
            gearBox
          );
          //addRequirements(gearBox);

    }

}
