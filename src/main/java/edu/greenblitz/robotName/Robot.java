package edu.greenblitz.robotName;

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
        OI.getInstance();
        CommandScheduler.getInstance().enable();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit(){
    }
    
    private void initializeLogger(){
        
        Logger logger = Logger.getInstance();
        
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
