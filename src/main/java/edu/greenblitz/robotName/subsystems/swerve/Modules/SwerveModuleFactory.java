package edu.greenblitz.robotName.subsystems.swerve.Modules;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;

public class SwerveModuleFactory {


    public static ISwerveModule create(SwerveChassis.Module module) {

        switch (RobotConstants.ROBOT_TYPE) {
            case REPLAY:
                return new ReplaySwerveModule();
            case FRANKENSTEIN:
                return new MK4ISwerveModule(module);
            case PEGA_SWERVE:
                return new KazaSwerveModule(module);
            case SIMULATION:
            default:
                return new SimulationSwerveModule(module);

        }
    }
}
