package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShiftableGearbox;

public class ShiftGears extends InstantCommand {
    
    public ShiftGears(boolean isOn, ShiftableGearbox gearBox){
        super(()->{gearBox.shift(isOn);}, gearBox);
        addRequirements(gearBox);
    }
}