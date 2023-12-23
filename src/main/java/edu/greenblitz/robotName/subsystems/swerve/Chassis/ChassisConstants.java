package edu.greenblitz.robotName.subsystems.swerve.Chassis;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class ChassisConstants {

    static final Pose2d INITIAL_ROBOT_POSITION = new Pose2d(0, 0, new Rotation2d(0));
    public static final Translation2d[] SWERVE_LOCATIONS_IN_SWERVE_KINEMATICS_COORDINATES = new Translation2d[]{
            new Translation2d(0.3020647, 0.25265), /*fl*/
            new Translation2d(0.3020647, -0.25265),/*fr*/
            new Translation2d(-0.3020647, 0.25265),/*bl*/
            new Translation2d(-0.3020647, -0.25265)/*br*/};

    public static final double MAX_VELOCITY = 4.1818320981472068;
    public static final double MAX_ACCELERATION = 14.37979171376739;
    public static final double MAX_ANGULAR_SPEED = 10.454580245368017;

    public static final double DRIVER_ANGULAR_SPEED_FACTOR = MAX_ANGULAR_SPEED / 2;
    public static final double DRIVER_LINEAR_SPEED_FACTOR = MAX_VELOCITY;

    public static final double DRIVER_ANGULAR_SPEED_FACTOR_SLOW = MAX_ANGULAR_SPEED / 4;
    public static final double DRIVER_LINEAR_SPEED_FACTOR_SLOW = 0.5;
}
