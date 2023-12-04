package edu.greenblitz.robotName;

public class RobotConstants {
	
	public static final Robot.RobotType ROBOT_TYPE = Robot.RobotType.FRANKENSTEIN;
	public static final String SIMULATION_LOG_PATH = System.getProperty("user.home") + "\\Desktop\\SimulationLogs";
	public static final String ROBORIO_LOG_PATH = "/media/sda1/";
	
	public static class Pneumatics{
		public static final int PCM_ID = 20;
	}
	
	public static class General {
		public final static double RAMP_RATE_VAL = 0.4;
		
		public static class Motors {
			
			public final static double SPARKMAX_TICKS_PER_RADIAN = Math.PI * 2;
			public final static double SPARKMAX_VELOCITY_UNITS_PER_RPM = 1;
			public static final double NEO_PHYSICAL_TICKS_TO_RADIANS = SPARKMAX_TICKS_PER_RADIAN / 42; //do not use unless you understand the meaning
			public final static double FALCON_TICKS_PER_RADIAN = 2 * Math.PI / 2048.0;
			public final static double FALCON_TICKS_PER_ROTATION = 2048.0;
			
			public final static double FALCON_VELOCITY_UNITS_PER_RPM = 600.0 / 2048;
		}
	}
	
	public static class Frankenstein {
		public static final double ROBOT_LENGTH_IN_METERS = 0.89;
		public static final double BUMPER_LENGTH = 0.05;
	}
	
}
