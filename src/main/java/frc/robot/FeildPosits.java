package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;


public final class FeildPosits {
    public static final class startingNotes{
        public final static Pose2d leftNote = new Pose2d(2.9, 7, new Rotation2d());
        public final static Pose2d middleNote = new Pose2d(2.9, 5.5, new Rotation2d());
        public final static Pose2d rightNote = new Pose2d(2.9, 4.13, new Rotation2d());
    }
        public static final class startingPosit{
        public static final Pose2d cornerStart = new Pose2d(0,0, new Rotation2d(0));
        public static final Pose2d leftSpeakerStart = new Pose2d();
        public static final Pose2d middleSpeakerStart = new Pose2d();
        public static final Pose2d rightSpeakerStart = new Pose2d();
        public static final Pose2d farRightStartForPosits = new Pose2d(.05, 7.764, new Rotation2d(0));
    }
    public static final Pose2d ampScore = new Pose2d(1.66, 7.56, new Rotation2d(Math.toRadians(90)));
    public static final Pose2d speakerScore = new Pose2d();

    public static final Pose2d notePickup = new Pose2d(14.1, 0.74, new Rotation2d(0));
    public static final Pose2d noteFromSorce = new Pose2d();
    public static final Pose2d testPosit = new Pose2d(0, 0.0, new Rotation2d(Math.toRadians(90)));

    public static final Pose2d getOut = new Pose2d(6.5, 0.85, new Rotation2d());
    public static final Pose2d getIn = new Pose2d(4.2, 1.2, new Rotation2d());
    public static final Pose2d midNote5 = new Pose2d(8.3, 0.8, new Rotation2d());
    public static final Pose2d midNote4 = new Pose2d(8.3, 4.3, new Rotation2d());
    public static final Pose2d getOutLeft = new Pose2d(6.5, 7.2, new Rotation2d());


}
