package edu.greenblitz.robotName.commands.swerve;

import edu.greenblitz.robotName.OI;
import edu.greenblitz.robotName.subsystems.swerve.SwerveChassisUtils;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.ChassisConstants;
import edu.greenblitz.robotName.utils.hid.SmartJoystick;

import java.util.function.DoubleSupplier;

public class MoveByJoysticks extends SwerveCommand {

    private double angularSpeedFactor;
    private double linearSpeedFactor;
    private DoubleSupplier angularVelocitySupplier;
    private boolean isSlow;


    public MoveByJoysticks(boolean isSlow, DoubleSupplier angularVelocitySupplier) {
        this.isSlow = isSlow;
        this.angularVelocitySupplier = angularVelocitySupplier;
    }

    public MoveByJoysticks(boolean isSlow) {
        this(isSlow, () -> -OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.RIGHT_X));
    }

    @Override
    public void initialize() {
        if (isSlow) {
            linearSpeedFactor = ChassisConstants.DRIVER_LINEAR_SPEED_FACTOR_SLOW;
            angularSpeedFactor = ChassisConstants.DRIVER_ANGULAR_SPEED_FACTOR_SLOW;
        } else {
            linearSpeedFactor = ChassisConstants.DRIVER_LINEAR_SPEED_FACTOR;
            angularSpeedFactor = ChassisConstants.DRIVER_ANGULAR_SPEED_FACTOR;
        }
    }


    public void execute() {
        double leftwardSpeed = SwerveChassisUtils.joystickValueToLinearVelocity(
                -OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_X),
                linearSpeedFactor
        );
        double forwardSpeed = SwerveChassisUtils.joystickValueToLinearVelocity(
                OI.getInstance().getMainJoystick().getAxisValue(SmartJoystick.Axis.LEFT_Y),
                linearSpeedFactor
        );

        double angSpeed = SwerveChassisUtils.joystickValueToAngularVelocity(
                angularVelocitySupplier.getAsDouble(),
                angularSpeedFactor
        );

        if (forwardSpeed == 0 && leftwardSpeed == 0 && angSpeed == 0) {
            swerve.stop();
            return;
        }
        swerve.moveByChassisSpeeds(
                forwardSpeed,
                leftwardSpeed,
                angSpeed,
                swerve.getChassisAngle()
        );
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        swerve.stop();
    }

}
