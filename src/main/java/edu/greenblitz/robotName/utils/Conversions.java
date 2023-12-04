package edu.greenblitz.robotName.utils;


import edu.greenblitz.robotName.RobotConstants;
import edu.greenblitz.robotName.subsystems.swerve.constants.MK4iSwerveConstants;

public class Conversions {

    public static double convertRPMToRadsPerSec (double rpm){
        return rpm * Math.PI / 30; //thats da formula dat i saw on stackoverflow - https://stackoverflow.com/questions/55562081/how-can-i-scale-a-velocity-value-from-native-units-ticks-per-100ms-to-meters-p
    }
    public static double convertRPMToMeterPerSecond (double rpm, double wheelRadius){
        return (2 * Math.PI * wheelRadius) / 60 * rpm;
    }

    public static class MK4IConversions{
        public static double convertRevolutionToMeters(double angInTicks){
            return angInTicks * MK4iSwerveConstants.WHEEL_CIRC;
        }


        public static double revolutionsToMeters(double revolutions) {
            return revolutions * MK4iSwerveConstants.WHEEL_CIRC;
        }
        public static double convertRadsToTicks(double angInRads) {
            return angInRads / MK4iSwerveConstants.angleTicksToRadians;
        }

        public static double convertTicksToRads(double angInTicks) {
            return angInTicks * MK4iSwerveConstants.angleTicksToRadians;
        }

        public static double convertMetersToTicks(double distanceInMeters) {
            return distanceInMeters / MK4iSwerveConstants.linTicksToMeters;
        }

        public static double convertTicksToMeters(double angInTicks){
            return angInTicks * MK4iSwerveConstants.linTicksToMeters;
        }

        public static double convertTicksPer100msToRPM(double ticksPer100ms){
            return ticksPer100ms * RobotConstants.General.Motors.FALCON_VELOCITY_UNITS_PER_RPM;
        }

        public static double convertSensorVelocityToRPM(double ticks){
            return ticks * MK4iSwerveConstants.angleTicksToWheelToRPM;
        }
        public static double convertSensorTicksToRadPerSecond(double ticks){
            return convertRPMToRadsPerSec(convertSensorVelocityToRPM(ticks));
        }
        public static double convertSensorVelocityToMeterPerSecond(double selectedSensorVelocity){
            return selectedSensorVelocity * MK4iSwerveConstants.linTicksToMetersPerSecond;
        }
        public static double convertRPMToMeterPerSecond (double rpm){
            return Conversions.convertRPMToMeterPerSecond(rpm, MK4iSwerveConstants.WHEEL_CIRC / (2 * Math.PI));
        }
    }






}
