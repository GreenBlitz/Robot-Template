package edu.greenblitz.robotName.subsystems.swerve.Modules;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.sensors.CANCoder;
import edu.greenblitz.robotName.subsystems.Battery;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.greenblitz.robotName.subsystems.swerve.SwerveModuleConfigObject;
import edu.greenblitz.robotName.subsystems.swerve.constants.MK4iSwerveConstants;
import edu.greenblitz.robotName.utils.Conversions;
import edu.greenblitz.robotName.utils.motors.GBFalcon;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class MK4ISwerveModule implements ISwerveModule {

    private GBFalcon angularMotor;
    private GBFalcon linearMotor;
    private CANCoder canCoder;
    private SimpleMotorFeedforward feedforward;
    private double encoderOffset;


    public MK4ISwerveModule(SwerveChassis.Module module) {

        SwerveModuleConfigObject configObject;
        switch (module) {

            case FRONT_LEFT:
                configObject = MK4iSwerveConstants.SdsModuleFrontLeft;
                break;
            case FRONT_RIGHT:
                configObject = MK4iSwerveConstants.SdsModuleFrontRight;
                break;
            case BACK_LEFT:
                configObject = MK4iSwerveConstants.SdsModuleBackLeft;
                break;
            case BACK_RIGHT:
                configObject = MK4iSwerveConstants.SdsModuleBackRight;
                break;
            default:
                throw new IllegalArgumentException("Invalid module");
        }

        angularMotor = new GBFalcon(configObject.angleMotorID);
        angularMotor.config(new GBFalcon.FalconConfObject(MK4iSwerveConstants.baseAngConfObj));

        linearMotor = new GBFalcon(configObject.linearMotorID);
        linearMotor.config(new GBFalcon.FalconConfObject(MK4iSwerveConstants.baseLinConfObj).withInverted(configObject.linInverted));

        canCoder = new CANCoder(configObject.AbsoluteEncoderID);
        this.encoderOffset = configObject.encoderOffset;

        this.feedforward = new SimpleMotorFeedforward(MK4iSwerveConstants.ks, MK4iSwerveConstants.kv, MK4iSwerveConstants.ka);
    }


    @Override
    public void setLinearVelocity(double speed) {
        linearMotor.set(
                TalonFXControlMode.Velocity,
                speed / MK4iSwerveConstants.linTicksToMetersPerSecond,
                DemandType.ArbitraryFeedForward,
                feedforward.calculate(speed) / Battery.getInstance().getCurrentVoltage());

    }

    @Override
    public void rotateToAngle(Rotation2d angle) {
        angularMotor.set(ControlMode.Position, Conversions.MK4IConversions.convertRadsToTicks(angle.getRadians()));
    }

    @Override
    public void setLinearVoltage(double voltage) {
        linearMotor.set(ControlMode.PercentOutput, voltage / Battery.getInstance().getCurrentVoltage());
    }

    @Override
    public void setAngularVoltage(double voltage) {
        angularMotor.set(ControlMode.PercentOutput, voltage / Battery.getInstance().getCurrentVoltage());
    }

    @Override
    public void setLinearIdleModeBrake(boolean isBrake) {
        linearMotor.setNeutralMode(isBrake ? NeutralMode.Brake : NeutralMode.Coast);
    }

    @Override
    public void setAngularIdleModeBrake(boolean isBrake) {
        angularMotor.setNeutralMode(isBrake ? NeutralMode.Brake : NeutralMode.Coast);
    }

    @Override
    public void resetAngle(Rotation2d angle) {
        angularMotor.setSelectedSensorPosition(Conversions.MK4IConversions.convertRadsToTicks(angle.getRadians()));
    }

    @Override
    public void stop() {
        linearMotor.set(ControlMode.PercentOutput, 0);
        angularMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void updateInputs(SwerveModuleInputsAutoLogged inputs) {
        inputs.linearVelocity = Conversions.MK4IConversions.convertSensorVelocityToMeterPerSecond(linearMotor.getSelectedSensorVelocity());
        inputs.angularVelocity =  Conversions.MK4IConversions.convertSensorVelocityToRPM(angularMotor.getSelectedSensorVelocity());

        inputs.linearVoltage = linearMotor.getMotorOutputVoltage();
        inputs.angularVoltage = angularMotor.getMotorOutputVoltage();

        inputs.linearCurrent = linearMotor.getSupplyCurrent();
        inputs.angularCurrent = angularMotor.getStatorCurrent();

        inputs.linearMetersPassed = Conversions.MK4IConversions.convertTicksToMeters(linearMotor.getSelectedSensorPosition());
        inputs.angularPositionInRads = Conversions.MK4IConversions.convertTicksToRads(angularMotor.getSelectedSensorPosition());

        if (Double.isNaN(Units.degreesToRadians(canCoder.getAbsolutePosition()))){
            inputs.absoluteEncoderPosition = 0;
        }else{
            inputs.absoluteEncoderPosition = Units.degreesToRadians(canCoder.getAbsolutePosition()) - Units.rotationsToRadians(encoderOffset);
        }
        inputs.isAbsoluteEncoderConnected = canCoder.getFirmwareVersion() != -1;
    }
}
