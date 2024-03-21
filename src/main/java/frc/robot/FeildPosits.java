package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.semiAutoCommands.routineValidator;

public final class FeildPosits {
    public static final class startingNotes{
        public final static Pose2d leftNote = new Pose2d();
        public final static Pose2d middleNote = new Pose2d();
        public final static Pose2d rightNote = new Pose2d();
    }
        public static final class startingPosit{
        public static final Pose2d cornerStart = new Pose2d();
        public static final Pose2d leftSpeakerStart = new Pose2d();
        public static final Pose2d middleSpeakerStart = new Pose2d();
        public static final Pose2d rightSpeakerStart = new Pose2d();
        public static final Pose2d farRightStartForPosits = new Pose2d(0, 8.2042, new Rotation2d(0));
    }
    public static final Pose2d ampScore = new Pose2d();
    public static final Pose2d speakerScore = new Pose2d();

    public static final Pose2d notePickup = new Pose2d();
    public static final Pose2d noteFromSorce = new Pose2d();
    public static final Pose2d testPosit = new Pose2d(0, 0.0, new Rotation2d(Math.toRadians(90)));
}
