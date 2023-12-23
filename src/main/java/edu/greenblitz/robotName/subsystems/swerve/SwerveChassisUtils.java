package edu.greenblitz.robotName.subsystems.swerve;

import java.util.function.DoubleSupplier;

public class SwerveChassisUtils {
    public static double joystickValueToLinearVelocity(double joystickValue, double velocityFactor) {
        if(Math.min(joystickValue, velocityFactor) == joystickValue) {
            return joystickValue * velocityFactor;
        }else {
            return velocityFactor;
        }
    }

    public static double joystickValueToAngularVelocity(double joystickValue, double velocityFactor) {
        if(Math.min(joystickValue, velocityFactor) == joystickValue) {
            return joystickValue * velocityFactor;
        }else {
            return velocityFactor;
        }
    }
}
