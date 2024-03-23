package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.limitSwitch;

public class elevatorMoveTo extends Command{
    Elevator elevator;
    boolean goingUp;
    double speed;
    limitSwitch activeSwitch;
    public elevatorMoveTo(Elevator elevator, boolean goingUp){
        this.elevator=elevator;
        this.goingUp=goingUp;
        addRequirements(elevator);
    }

    @Override
    public void initialize(){
        SmartDashboard.putBoolean("elevatorEndStatus", false);

        if (goingUp){
            speed=Constants.elevator.elevatorUpSpeed;
            activeSwitch=elevator.topSwitch;
        }
        else{
            speed=Constants.elevator.elevatorDownSpeed;
            activeSwitch=elevator.bottomSwitch;
        }
    }


    @Override
    public void execute(){
        elevator.moveElevator(speed);
    }

    @Override 
    public boolean isFinished(){
        return activeSwitch.getVal();
    }

    @Override 
    public void end(boolean wasInterupted){
        SmartDashboard.putBoolean("elevatorEndStatus", true);
        SmartDashboard.putBoolean("elevatorWasInterupted", wasInterupted);
        elevator.stop();
        if (!wasInterupted){
          elevator.isUp=goingUp;
          if (elevator.isUp){
            new stayAtTopMain(elevator).schedule();
        }
        }


    }
}
