package edu.greenblitz.robotName.utils.tuneableNumber;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import org.littletonrobotics.junction.Logger;

public class TunableNumber {
    private double defaultValue = 0;
    private final GenericEntry networkTableEntry;
    protected double value;
    private String widgetTitle;

    public TunableNumber(String widgetTitle, String shuffleBoardTabTitle) {
        this.widgetTitle = widgetTitle;
        value = defaultValue;

        networkTableEntry = Shuffleboard.getTab(shuffleBoardTabTitle).add(widgetTitle, defaultValue).getEntry();
    }

    public TunableNumber(String widgetTitle, String shuffleBoardTabTitle, double defaultValue) {
        this.widgetTitle = widgetTitle;
        this.defaultValue = defaultValue;
        value = defaultValue;

        networkTableEntry = Shuffleboard.getTab(shuffleBoardTabTitle).add(widgetTitle, defaultValue).getEntry();
    }

    public double getValue() {
        return networkTableEntry.getDouble(defaultValue);
    }

    public double getDefaultValue(){
        return defaultValue;
    }

    public boolean hasChanged() {
        if (value != getValue()) {
            value = getValue();
            Logger.recordOutput(widgetTitle, value);
            return true;
        }
        return false;
    }
}
