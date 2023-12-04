package edu.greenblitz.robotName.subsystems.swerve;

public class SwerveModuleConfigObject {

    public int angleMotorID;
    public int linearMotorID;
    public int AbsoluteEncoderID;
    public boolean linInverted;
    public double encoderOffset;
    public static SwerveModuleConfigObject SdsModuleFrontLeft =new SwerveModuleConfigObject(1, 0, 1, 0.8486328125,false); //front left
    public static SwerveModuleConfigObject SdsModuleFrontRight=new SwerveModuleConfigObject(3, 2, 2,0.2939453125 ,true); //front right
    public static SwerveModuleConfigObject SdsModuleBackLeft = new SwerveModuleConfigObject(5, 4, 3, 0.5524,false); //back left
    public static SwerveModuleConfigObject SdsModuleBackRight =new SwerveModuleConfigObject(7, 6, 4, 0.8718,true); //back right
    
    
    public SwerveModuleConfigObject(int angleMotorID, int linearMotorID, int AbsoluteEncoderID,double encoderOffset, boolean linInverted) {
        this.angleMotorID = angleMotorID;
        this.linearMotorID = linearMotorID;
        this.AbsoluteEncoderID = AbsoluteEncoderID;
        this.linInverted = linInverted;
        this.encoderOffset = encoderOffset;
    }
    public SwerveModuleConfigObject(int angleMotorID, int linearMotorID, int AbsoluteEncoderID,boolean linInverted) {
        this(
                angleMotorID,
                linearMotorID,
                AbsoluteEncoderID,
                0,
                linInverted
        );
    }



}
