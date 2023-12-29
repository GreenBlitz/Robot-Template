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

    public double getDefaultValue() {
        return defaultValue;
    }

    public boolean hasChanged() {
        return value != getValue();
    }

    public void setValueByDashboard() {
        value = getValue();
        Logger.recordOutput(widgetTitle, value);
    }

    public void periodic() {
        if (hasChanged())
            setValueByDashboard();
    }
}
