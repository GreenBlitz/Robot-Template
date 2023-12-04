package edu.greenblitz.robotName.subsystems.Gyros;


public interface IGyro {

    void setYaw(double yaw);

    void setPitch(double pitch);

    void setRoll(double roll);

    double getYaw();

    double getPitch();

    double getRoll();


    void updateInputs(GyroInputsAutoLogged inputs);


}
