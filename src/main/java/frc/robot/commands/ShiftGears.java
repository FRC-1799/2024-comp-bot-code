package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.ShiftableGearbox;

public class ShiftGears extends RunCommand {
    
    public ShiftGears(ShiftableGearbox gearBox, BooleanSupplier isHigh){
        super(()->{gearBox.shift(isHigh.getAsBoolean());}, gearBox);
    }
}