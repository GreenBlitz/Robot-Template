package edu.greenblitz.robotName.subsystems.Gyros;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.math.geometry.Rotation2d;

public class PigeonGyro implements IBaseGyro {

    private PigeonIMU pigeonIMU;
    Rotation2d yawOffset;
    Rotation2d pitchOffset;
    Rotation2d rollOffset;

    private GyroInputsAutoLogged lastInputs = new GyroInputsAutoLogged();

    public PigeonGyro(int id) {
        this.pigeonIMU = new PigeonIMU(id);
    }

    /**
     * the offset sets himself the current angle + the offset
     * because idk but we do it like this
     * <p>
     * ALL IN RADIANS
     */


    @Override
    public void updateYaw(Rotation2d yaw) {
        yawOffset.plus(yaw.plus(getYaw()));
    }

    @Override
    public void updatePitch(Rotation2d pitch) {
        pitchOffset.plus(pitch.plus(getPitch()));
    }

    @Override
    public void updateRoll(Rotation2d roll) {
        rollOffset.plus(roll.plus(getRoll()));
    }

    @Override
    public Rotation2d getYaw() {
        return Rotation2d.fromRadians(lastInputs.yaw);
    }

    @Override
    public Rotation2d getPitch() {
        return Rotation2d.fromRadians(lastInputs.pitch);
    }

    @Override
    public Rotation2d getRoll() {
        return Rotation2d.fromRadians(lastInputs.roll);
    }

    @Override
    public void updateInputs(GyroInputsAutoLogged inputs) {
        inputs.yaw = (Math.toRadians(pigeonIMU.getYaw()) - yawOffset.getRadians()) % (2 * Math.PI);
        inputs.pitch = (Math.toRadians(pigeonIMU.getPitch()) - pitchOffset.getRadians()) % (2 * Math.PI);
        inputs.roll = (Math.toRadians(pigeonIMU.getRoll()) - rollOffset.getRadians()) % (2 * Math.PI);

        lastInputs = inputs;
    }
}
