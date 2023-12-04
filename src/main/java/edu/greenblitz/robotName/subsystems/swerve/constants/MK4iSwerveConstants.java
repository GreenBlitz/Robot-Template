package edu.greenblitz.robotName.subsystems.swerve.constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.SwerveModuleConfigObject;
import edu.greenblitz.robotName.utils.PIDObject;
import edu.greenblitz.robotName.utils.motors.GBFalcon;

public class MK4iSwerveConstants {
	
	public static SwerveModuleConfigObject SdsModuleFrontLeft =new SwerveModuleConfigObject(1, 0, 1, 0.8486328125,false); //front left
	public static SwerveModuleConfigObject SdsModuleFrontRight=new SwerveModuleConfigObject(3, 2, 2,0.2939453125 ,true); //front right
	public static SwerveModuleConfigObject SdsModuleBackLeft = new SwerveModuleConfigObject(5, 4, 3, 0.5524,false); //back left
	public static SwerveModuleConfigObject SdsModuleBackRight =new SwerveModuleConfigObject(7, 6, 4, 0.8718,true); //back right
	
	public static final double ANG_GEAR_RATIO = (150.0 / 7);
	public static final double LIN_GEAR_RATIO = 8.14;
	
	public static final double ks = 0.16411;
	public static final double kv = 2.6824;
	public static final double ka = 0.25968;
	
	public static final double WHEEL_CIRC = 0.0517 * 2 * Math.PI;
	public static final double linTicksToMeters = RobotConstants.General.Motors.FALCON_TICKS_PER_RADIAN * WHEEL_CIRC / 2 / Math.PI / LIN_GEAR_RATIO;
	public static final double angleTicksToWheelToRPM = RobotConstants.General.Motors.FALCON_VELOCITY_UNITS_PER_RPM / ANG_GEAR_RATIO;
	public static final double linTicksToMetersPerSecond = RobotConstants.General.Motors.FALCON_VELOCITY_UNITS_PER_RPM / LIN_GEAR_RATIO * WHEEL_CIRC / 60;
	public static final double angleTicksToRadians = RobotConstants.General.Motors.FALCON_TICKS_PER_RADIAN / ANG_GEAR_RATIO;
	public static final double magEncoderTicksToFalconTicks = 2 * Math.PI / angleTicksToRadians;
	
	public static final PIDObject angPID = new PIDObject().withKp(0.05).withMaxPower(1.0).withFF(0);//.withKd(10).withMaxPower(0.8);
	public static final GBFalcon.FalconConfObject baseAngConfObj = new GBFalcon.FalconConfObject().withNeutralMode(NeutralMode.Brake).withCurrentLimit(30).withRampRate(RobotConstants.General.RAMP_RATE_VAL).withInverted(true).withPID(angPID);
	
	public static final PIDObject linPID = new PIDObject().withKp(0.0003).withMaxPower(0.5);
	public static final GBFalcon.FalconConfObject baseLinConfObj = new GBFalcon.FalconConfObject()
			.withNeutralMode(NeutralMode.Brake)
			.withCurrentLimit(40)
			.withRampRate(RobotConstants.General.RAMP_RATE_VAL)
			.withPID(linPID);
	public static final double FREE_CURRENT = 1.5;
}
