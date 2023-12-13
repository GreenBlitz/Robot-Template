package edu.greenblitz.robotName.subsystems.swerve.constants;

import com.pathplanner.lib.util.PIDConstants;

public class SimulationConstants{
    public static final PIDConstants ANGULAR_PID_CONSTANTS = new PIDConstants(1, 0, 0);
    public static class SIMULATION_LINEAR_MOTOR{

        public static final int NUMBER_OF_MOTORS = 1;
        public static final double GEAR_RATIO = MK4iSwerveConstants.LIN_GEAR_RATIO;
        public static final double MOMENT_OF_INERTIA = 0.01;
    }

    public static class SIMULATION_ANGULAR_MOTOR{

        public static final int NUMBER_OF_MOTORS = 1;
        public static final double GEAR_RATIO = MK4iSwerveConstants.LIN_GEAR_RATIO;
        public static final double MOMENT_OF_INERTIA = 0.01;
    }
}
