package edu.greenblitz.robotName.subsystems.swerve.constants;

import com.revrobotics.CANSparkMax;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.SwerveModuleConfigObject;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBSparkMax;

public class KazaSwerveConstants {
	
	public static SwerveModuleConfigObject KazaModuleFrontLeft = new SwerveModuleConfigObject(1, 10, 0, false); //front left
	public static SwerveModuleConfigObject KazaModuleFrontRight = new SwerveModuleConfigObject(3, 11, 2, true); //front right
	public static SwerveModuleConfigObject KazaModuleBackLeft = new SwerveModuleConfigObject(2, 8, 1, false); //back left
	public static SwerveModuleConfigObject KazaModuleBackRight = new SwerveModuleConfigObject(12, 5, 3, true); //back right
	public static final double ANG_GEAR_RATIO = 6.0;
	public static final double LIN_GEAR_RATIO = 8.0;
	
	public static final double ks = 0.14876;
	public static final double kv = 3.3055;
	public static final double ka = 0.11023;
	
	public static final double WHEEL_CIRC = 0.0517 * 2 * Math.PI;
	public static final double linTicksToMeters = RobotConstants.General.Motors.SPARKMAX_TICKS_PER_RADIAN * WHEEL_CIRC / LIN_GEAR_RATIO / (2 * Math.PI);
	public static final double angleTicksToWheelToRPM = RobotConstants.General.Motors.SPARKMAX_VELOCITY_UNITS_PER_RPM / ANG_GEAR_RATIO;
	public static final double linTicksToMetersPerSecond = RobotConstants.General.Motors.SPARKMAX_VELOCITY_UNITS_PER_RPM * WHEEL_CIRC / 60 / LIN_GEAR_RATIO;
	public static final PIDObject linPID = new PIDObject().withKp(0.0003).withMaxPower(0.5);
	public static final GBSparkMax.SparkMaxConfObject baseLinConfObj = new GBSparkMax.SparkMaxConfObject().withIdleMode(CANSparkMax.IdleMode.kBrake).withCurrentLimit(40).withRampRate(RobotConstants.General.RAMP_RATE_VAL).withPID(linPID).withPositionConversionFactor(linTicksToMeters).withVelocityConversionFactor(linTicksToMetersPerSecond);
	public static final double angleTicksToRadians = RobotConstants.General.Motors.SPARKMAX_TICKS_PER_RADIAN / ANG_GEAR_RATIO;
	public static final PIDObject angPID = new PIDObject().withKp(0.5).withMaxPower(1.0);
	public static final GBSparkMax.SparkMaxConfObject baseAngConfObj = new GBSparkMax.SparkMaxConfObject().withIdleMode(CANSparkMax.IdleMode.kBrake).withCurrentLimit(30).withRampRate(RobotConstants.General.RAMP_RATE_VAL).withInverted(true).withPID(angPID).withPositionConversionFactor(angleTicksToRadians).withVelocityConversionFactor(angleTicksToWheelToRPM);
	public static final int LAMPREY_AVERAGE_BITS = 2;
	
}
