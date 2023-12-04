package edu.greenblitz.robotName.subsystems.swerve.constants;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class ChassisConstants {
	
	static final Pose2d INITIAL_ROBOT_POSITION = new Pose2d(0, 0, new Rotation2d(0));
	public static final Translation2d[] SWERVE_LOCATIONS_IN_SWERVE_KINEMATICS_COORDINATES = new Translation2d[]{
			//the WPILib coordinate system is stupid. (x is forwards, y is leftwards)
			//the translations are given rotated by 90 degrees clockwise to avoid coordinates system conversion at output
			new Translation2d(0.3020647, 0.25265), /*fl*/
			new Translation2d(0.3020647, -0.25265),/*fr*/
			new Translation2d(-0.3020647, 0.25265),/*bl*/
			new Translation2d(-0.3020647, -0.25265)/*br*/};
	
	public static final double MAX_VELOCITY = 4.1818320981472068;
	public static final double MAX_ACCELERATION = 14.37979171376739;
	public static final double MAX_ANGULAR_SPEED = 10.454580245368017;
}
