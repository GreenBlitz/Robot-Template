package edu.greenblitz.robotName.subsystems.Gyros;


import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;

public class SimulationGyro implements IGyro {

    private GyroInputsAutoLogged lastInputs = new GyroInputsAutoLogged();
    @Override
    public void updateInputs(GyroInputsAutoLogged inputs) {
        inputs.roll = 0;
        inputs.pitch = 0;
        inputs.yaw += SwerveChassis.getInstance().getChassisSpeeds().omegaRadiansPerSecond * RobotConstants.SimulationConstants.TIME_STEP;

        lastInputs = inputs;
    }

    @Override
    public void setYaw(double yaw) {
        lastInputs.yaw = yaw;
    }

    @Override
    public void setPitch(double pitch) {
        lastInputs.pitch = pitch;
    }

    @Override
    public void setRoll(double roll) {
        lastInputs.roll = roll;
    }

    @Override
    public double getYaw() {
        return lastInputs.yaw;
    }

    @Override
    public double getPitch() {
        return lastInputs.pitch;
    }

    @Override
    public double getRoll() {
        return lastInputs.roll;
    }
}
