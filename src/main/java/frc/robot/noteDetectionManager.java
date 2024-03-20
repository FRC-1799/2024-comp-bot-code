package frc.robot;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class noteDetectionManager {
    static Pose2d current = null;
    static NetworkTable table = NetworkTableInstance.getDefault().getTable("SmartDashboard");
    static int ticksSinceGoodVal=50;
    static final double[] defaultNote = new double[]{0,0};

    public static void periodic(){
        double[] rawNote = table.getEntry("notePosition").getDoubleArray(defaultNote);
        Pose2d newNote= new Pose2d(rawNote[0], rawNote[1], new Rotation2d(0));
        if (newNote.getX()!=0||newNote.getY()!=0){
            current=newNote;
            ticksSinceGoodVal=0;
        }
        else{
            ticksSinceGoodVal++;
        }
    }

    public static Pose2d getNotePose(Pose2d defaultValue){
        if (ticksSinceGoodVal>50){
            return defaultValue; 
        }
        
        return current;
            
        
    }
}
