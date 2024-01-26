package frc.robot.commands;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import javax.sound.midi.DefaultButtonModel;

import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Gyro;

public class NetworkTable extends CommandBase {

    DoublePublisher xPub;
    DoublePublisher yPub;

    DefaultButtonModel m_midi = new DefaultButtonModel();

    /** Creates a new Network Table. */
    public NetworkTable() {

        // Init the network table and publishers
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable("datatable");
        xPub = table.getDoubleTopic("x").publish();
        yPub = table.getDoubleTopic("y").publish();
        int y = 0
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Update network table
        if (m_midi.getModel().isPressed()) {
            System.out.println("The button is pressed.");
            int x = m_midi.getModel().isPressed();
            y += 1
            xPub.set(x);
            yPub.set(y);
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}

