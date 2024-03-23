package frc.robot.commands.WristComands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.WristIntake;

public class WristMove extends SequentialCommandGroup{
    public WristMove(WristIntake wrist, double Position){
        super(
            new WristMoveAuto(wrist, Position),
            new WristMoveHold(wrist, Math.toRadians(Position))
        );
    }
}
