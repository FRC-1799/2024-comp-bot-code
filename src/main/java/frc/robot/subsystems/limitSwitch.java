package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.switchInfoBox;

import java.util.function.BooleanSupplier;

public class limitSwitch extends SubsystemBase{

    int id;
    boolean value=false;
    boolean status;

    ShuffleboardTab tab;
    GenericEntry valueWrite;
    GenericEntry statusWrite;
    GenericEntry statusRead;

    String name;


    public limitSwitch(switchInfoBox info){
        this.id=info.ID;
        this.name=info.name;
        tab = Shuffleboard.getTab(name);
        valueWrite= tab.add("Value", false).getEntry();
        statusWrite = tab.add("Status", false).getEntry();
        statusRead = tab.add("manual status", true).getEntry();

    }

    public boolean getVal(){
        return value;
    }
    public boolean getStatus(){
        //needs to be added later. should use the fact that the input pins will always have one active
        return status;
    }

    public void update(boolean value, boolean status){
        this.value=value;
        this.status = status&&statusRead.getBoolean(true);
        valueWrite.setBoolean(value);
        statusWrite.setBoolean(status);
    }


    

}
