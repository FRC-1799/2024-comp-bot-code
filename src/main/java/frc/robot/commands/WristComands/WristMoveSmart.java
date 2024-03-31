package frc.robot.commands.WristComands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.WristIntake;

public class WristMoveSmart extends SequentialCommandGroup{
    public WristMoveSmart(WristIntake wrist, double position){
        super(new WristMoveAuto(wrist, position), new WristMoveHold(wrist, position));
    }


}
