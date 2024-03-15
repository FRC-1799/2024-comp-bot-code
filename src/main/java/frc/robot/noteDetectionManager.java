package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class noteDetectionManager {
    static Pose2d current = null;
    static NetworkTable table = NetworkTableInstance.getDefault().getTable("notePosition");
    static int ticksSinceGoodVal=0;

    public static void periodic(){
        Pose2d newNote= new Pose2d(table.getEntry("notePoseX").getDouble(0), table.getEntry("notePoseY").getDouble(0), new Rotation2d(0));
        if (!(newNote.getX()==0)){
            current=newNote;
            ticksSinceGoodVal=0;
        }
        else{
            ticksSinceGoodVal++;
        }
    }

    public static Pose2d getNotePose(){
        if (ticksSinceGoodVal>50){
            return null; 
        }
        
        return current;
            
        
    }
}
