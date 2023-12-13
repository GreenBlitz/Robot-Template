package edu.greenblitz.robotName.subsystems.swerve.Modules;

import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;

public class SwerveModuleFactory {


    public static ISwerveModule create(SwerveChassis.Module module) {

        return switch (RobotConstants.ROBOT_TYPE) {
            case REPLAY -> new ReplaySwerveModule();
            case FRANKENSTEIN -> new MK4ISwerveModule(module);
            case PEGA_SWERVE -> new KazaSwerveModule(module);
            default -> new SimulationSwerveModule(module);
        };
    }
}
