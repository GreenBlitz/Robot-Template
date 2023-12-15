package edu.greenblitz.robotName.subsystems.swerve;

import java.util.function.DoubleSupplier;

public class SwerveChassisUtils {
    public static double joystickValueToLinearVelocity(double joystickValue, double velocityFactor) {
        return Math.min(joystickValue, velocityFactor) == joystickValue ? joystickValue * velocityFactor : velocityFactor;
    }

    public static double joystickValueToAngularVelocity(double joystickValue, double velocityFactor) {
        return Math.min(joystickValue, velocityFactor) == joystickValue ? joystickValue * velocityFactor : velocityFactor;
    }
}
