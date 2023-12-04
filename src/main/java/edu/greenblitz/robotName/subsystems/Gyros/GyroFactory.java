package edu.greenblitz.robotName.subsystems.Gyros;

import edu.greenblitz.robotName.RobotConstants;

public class GyroFactory {

    public static IGyro create(){
        switch (RobotConstants.ROBOT_TYPE) {
            case FRANKENSTEIN:
            case PEGA_SWERVE:
                return new PigeonGyro(GyroConstants.PigeonGyro.ID);
            case REPLAY:
                return new ReplayGyro();
            case SIMULATION:
            default:
                return new SimulationGyro();
        }
    }

}
