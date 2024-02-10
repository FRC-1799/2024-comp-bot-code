package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.ShiftableGearbox;

public class shiftGears extends RunCommand {
    
    public shiftGears(ShiftableGearbox gearBox, BooleanSupplier isHigh){
        super(
        ()->{

            gearBox.shift(isHigh.getAsBoolean());
        },
    gearBox
    );
    }
}
