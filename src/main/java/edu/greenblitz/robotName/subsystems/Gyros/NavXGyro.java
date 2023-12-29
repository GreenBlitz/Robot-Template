package edu.greenblitz.robotName.subsystems.Gyros;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SerialPort;

public class NavXGyro implements IBaseGyro {
	private AHRS gyro;
	private Rotation2d yawOffset;
	private Rotation2d pitchOffset;
	private Rotation2d rollOffset;

	private GyroInputsAutoLogged lastInputs = new GyroInputsAutoLogged();

	public NavXGyro(){
		this.gyro = new AHRS(SerialPort.Port.kMXP);

		yawOffset = new Rotation2d();
		pitchOffset = new Rotation2d();
		rollOffset = new Rotation2d();
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
	public void updateYaw(Rotation2d yaw) {
		yawOffset.plus(yaw.plus(getYaw()));
	}

	@Override
	public void updatePitch(Rotation2d pitch) {
		pitchOffset.plus(pitch.plus(getPitch()));
	}

	@Override
	public void updateRoll (Rotation2d roll) {
		rollOffset.plus(roll.plus(getRoll()));
	}


	@Override
	public void updateInputs(GyroInputsAutoLogged inputs) {
		inputs.yaw =  -((Math.toRadians(gyro.getYaw()) - yawOffset.getRadians())%(2* Math.PI));
		inputs.pitch = ((Math.toRadians(gyro.getPitch()) - pitchOffset.getRadians())%(2* Math.PI));
		inputs.roll = ((Math.toRadians(gyro.getRoll()) - rollOffset.getRadians())%(2* Math.PI));

		lastInputs = inputs;
	}
}
