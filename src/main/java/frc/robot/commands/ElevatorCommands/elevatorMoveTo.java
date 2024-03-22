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
    boolean canRun;
    double speed;
    limitSwitch activeSwitch;
    public elevatorMoveTo(Elevator elevator, boolean goingUp){
        this.elevator=elevator;
        this.goingUp=goingUp;
        addRequirements(elevator);
        SmartDashboard.putBoolean("hello", false);
    }

    @Override
    public void initialize(){
        canRun=elevator.isUp!=goingUp;
        SmartDashboard.putBoolean("canrun", canRun);
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
        SmartDashboard.putBoolean("hello", true);
        return activeSwitch.getVal()||!canRun;
    }

    @Override 
    public void end(boolean wasInterupted){
        SmartDashboard.putBoolean("hewwo", true);
        elevator.stop();

        if (!wasInterupted){
          elevator.isUp=!elevator.isUp;
          if (elevator.isUp){
            new stayAtTopMain(elevator).schedule();
          }
        }
    }
}
