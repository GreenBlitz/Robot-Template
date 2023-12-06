package edu.greenblitz.robotName.subsystems.swerve.Modules;

import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import org.littletonrobotics.junction.Logger;

public class SwerveModule {


    ISwerveModule swerveModule;
    SwerveModuleInputsAutoLogged swerveModuleInputs;

    SwerveChassis.Module module;

    public double targetAngle;
    public double targetVel;

    public SwerveModule(SwerveChassis.Module module) {
        this.module = module;

        this.swerveModule = SwerveModuleFactory.create(module);

        swerveModuleInputs = new SwerveModuleInputsAutoLogged();
        this.periodic();
        
    }



    public void rotateToAngle(Rotation2d angle) {
        double diff = Math.IEEEremainder(angle.getRadians() - getModuleAngle(), 2 * Math.PI);
        diff -= diff > Math.PI ? 2 * Math.PI : 0;
        
        swerveModule.rotateToAngle(Rotation2d.fromRadians(getModuleAngle() + diff));
    }

    public double getModuleAngle() {
        return swerveModuleInputs.angularPositionInRads;
    }

    public double getCurrentVelocity() {
        return swerveModuleInputs.linearVelocity;
    }

    public double getCurrentMeters() {
        return swerveModuleInputs.linearMetersPassed;
    }

    public SwerveModulePosition getCurrentPosition() {
        return new SwerveModulePosition(getCurrentMeters(), new Rotation2d(getModuleAngle()));
    }

    public void resetEncoderToValue(Rotation2d angle) {
        swerveModule.resetAngle(angle);
    }
    public void resetEncoderToValue() {
        swerveModule.resetAngle(Rotation2d.fromRadians(0));
    }

    public void periodic() {
        swerveModule.updateInputs(swerveModuleInputs);
        Logger.processInputs("DriveTrain/Module" + this.module.toString(), swerveModuleInputs);
    }


    public void resetEncoderByAbsoluteEncoder() {
        resetEncoderToValue(Rotation2d.fromRadians(swerveModuleInputs.absoluteEncoderPosition));
    }

    public void setLinSpeed(double speed) {
        swerveModule.setLinearVelocity(speed);
    }

    public void stop() {
        swerveModule.stop();
    }

    public SwerveModuleState getModuleState(){
        return new SwerveModuleState(
                getCurrentVelocity(),
                new Rotation2d(getModuleAngle())
        );
    }

    public boolean isAtAngle(double targetAngleInRads, double tolerance) {
        double currentAngleInRads = getModuleAngle();
        return (currentAngleInRads - targetAngleInRads) %(2*Math.PI) < tolerance
                || (targetAngleInRads - currentAngleInRads) % (2*Math.PI) < tolerance;
    }
    public boolean isAtAngle (double errorInRads){
        return isAtAngle(targetAngle, errorInRads);
    }
    public void setModuleState(SwerveModuleState moduleState){
        setLinSpeed(moduleState.speedMetersPerSecond);
        rotateToAngle(moduleState.angle);
    }
    public double getAbsoluteEncoderValue(){
        return swerveModuleInputs.absoluteEncoderPosition;
    }

    public double getLinearCurrent (){
        return swerveModuleInputs.linearCurrent;
    }

    public void setRotPowerOnlyForCalibrations(double power){
        swerveModule.setAngularVoltage(power * Battery.getInstance().getCurrentVoltage());
    }
    public void setLinPowerOnlyForCalibrations(double power){
        swerveModule.setLinearVoltage(power * Battery.getInstance().getCurrentVoltage());
    }
    public void setLinIdleModeBrake (){
        swerveModule.setLinearIdleModeBrake(true);
    }
    public void setLinIdleModeCoast (){
        swerveModule.setLinearIdleModeBrake(false);
    }
    public void setRotIdleModeBrake(){
        swerveModule.setAngularIdleModeBrake(true);

    }
    public void setRotIdleModeCoast (){
        swerveModule.setAngularIdleModeBrake(false);
    }
    public boolean isEncoderBroken(){
        return !swerveModuleInputs.isAbsoluteEncoderConnected;
    }
    public double getLinVoltage(){
        return swerveModuleInputs.linearVoltage;
    }

}
