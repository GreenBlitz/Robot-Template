package edu.greenblitz.robotName.subsystems.swerve;

import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
public class SwerveChassisUtils {



    public static double joystickValueToLinearVelocity(double joystickValue, double velocityFactor) {
        if (Math.abs(joystickValue * velocityFactor) < velocityFactor) {
            return joystickValue * velocityFactor * (ChassisConstants.LINEAR_JOYSTICK_INVERTED ? -1 : 1);
        } else {
            return velocityFactor * (ChassisConstants.LINEAR_JOYSTICK_INVERTED ? -1 : 1);
        }
    }

    public static double joystickValueToAngularVelocity(double joystickValue, double velocityFactor) {
        if (Math.abs(joystickValue * velocityFactor) < velocityFactor) {
            return joystickValue * velocityFactor  * (ChassisConstants.ANGULAR_JOYSTICK_INVERTED ? -1 : 1);
        } else {
            return velocityFactor * (ChassisConstants.ANGULAR_JOYSTICK_INVERTED ? -1 : 1);
        }
    }
}
