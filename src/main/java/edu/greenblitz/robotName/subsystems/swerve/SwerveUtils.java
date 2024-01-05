package edu.greenblitz.robotName.subsystems.swerve;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.wpi.first.math.geometry.Rotation2d;

public class SwerveUtils {



    public static double joystickValueToLinearVelocity(double joystickValue, double velocityFactor) {
        double factoredOutputValue;
        if (Math.abs(joystickValue * velocityFactor) < velocityFactor) {
            factoredOutputValue = joystickValue * velocityFactor;
        } else {
            factoredOutputValue = velocityFactor;
        }
        return factoredOutputValue * (ChassisConstants.LINEAR_JOYSTICK_INVERTED ? -1 : 1);
    }

    public static double joystickValueToAngularVelocity(double joystickValue, double velocityFactor) {
        double factoredOutputValue;
        if (Math.abs(joystickValue * velocityFactor) < velocityFactor) {
            factoredOutputValue = joystickValue * velocityFactor;
        } else {
            factoredOutputValue = velocityFactor;
        }
        return factoredOutputValue * (ChassisConstants.ANGULAR_JOYSTICK_INVERTED ? -1 : 1);
    }

    /**
     * @param modulePosition the position of the motor (with gear ratio) in rotations
     * */
    public static double getCouplingCompensatedDistance (Rotation2d modulePosition, double distance, double couplingRatio) {
        return distance - modulePosition.getRotations() * couplingRatio;
    }
}
