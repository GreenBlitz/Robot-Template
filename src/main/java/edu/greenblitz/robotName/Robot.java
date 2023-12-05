package edu.greenblitz.robotName;

import edu.greenblitz.robotName.commands.swerve.MoveByJoysticks;
import edu.greenblitz.robotName.subsystems.swerve.Chassis.SwerveChassis;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StructPublisher;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

public class Robot extends LoggedRobot {

    @Override
    public void robotInit() {
        initializeLogger();

        SwerveChassis.init();
        SwerveChassis.getInstance().setDefaultCommand(new MoveByJoysticks(false));

        OI.getInstance();
        CommandScheduler.getInstance().enable();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    private void initializeLogger(){

        NetworkTableInstance.getDefault()
                .getStructTopic("RobotPose", Pose2d.struct).publish();

        NetworkTableInstance.getDefault()
                .getStructTopic("MechanismPoses", Pose3d.struct).publish();

        switch (RobotConstants.ROBOT_TYPE) {
            // Running on a real robot, log to a USB stick
            case FRANKENSTEIN:
            case PEGA_SWERVE:
                Logger.addDataReceiver(new WPILOGWriter(RobotConstants.ROBORIO_LOG_PATH));
                Logger.addDataReceiver(new NT4Publisher());
                break;
            // Replaying a log, set up replay source
            case REPLAY:
                setUseTiming(false); // Run as fast as possible
                String logPath = LogFileUtil.findReplayLog();
                Logger.setReplaySource(new WPILOGReader(logPath));
                Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_simulation")));
                break;
            case SIMULATION:
            default:
                Logger.addDataReceiver(new NT4Publisher());
                Logger.addDataReceiver(new WPILOGWriter(RobotConstants.SIMULATION_LOG_PATH));
                break;
        }
        Logger.start();
    }
    
    public enum RobotType {
        PEGA_SWERVE,
        FRANKENSTEIN,
        SIMULATION,
        REPLAY
    }
}
