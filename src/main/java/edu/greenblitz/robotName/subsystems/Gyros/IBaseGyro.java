package edu.greenblitz.robotName.subsystems.Gyros;


import edu.wpi.first.math.geometry.Rotation2d;

public interface IBaseGyro {

    void updateYaw(Rotation2d yaw);

    void updatePitch(Rotation2d pitch);

    void updateRoll(Rotation2d roll);

    Rotation2d getYaw();

    Rotation2d getPitch();

    Rotation2d getRoll();


    void updateInputs(GyroInputsAutoLogged inputs);


}
