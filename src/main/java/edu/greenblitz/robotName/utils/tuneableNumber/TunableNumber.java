package edu.greenblitz.robotName.utils.tuneableNumber;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import org.littletonrobotics.junction.Logger;

public class TunableNumber {
    private static final double DEFAULT_VALUE = 0;
    private GenericEntry networkTableEntry;
    private double value;
    String widgetTitle;

    public TunableNumber(String widgetTitle, String shuffleBoardTabTitle) {
        networkTableEntry = Shuffleboard.getTab(shuffleBoardTabTitle).add(widgetTitle, DEFAULT_VALUE).getEntry();
        this.widgetTitle = widgetTitle;
    }

    public double getValue() {
        return networkTableEntry.getDouble(DEFAULT_VALUE);
    }
    public void periodic(){
        if(value != getValue()){
            value = getValue();
            Logger.recordOutput(widgetTitle , value);
        }
    }
}
