package edu.greenblitz.robotName.utils.tuneableNumber;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import org.littletonrobotics.junction.Logger;

public class TunableNumber {
    private static final double DEFAULT_VALUE = 0;
    private GenericEntry networkTableEntry;
    protected double value;
    String widgetTitle;

    public TunableNumber(String widgetTitle, String shuffleBoardTabTitle) {
        networkTableEntry = Shuffleboard.getTab(shuffleBoardTabTitle).add(widgetTitle, DEFAULT_VALUE).getEntry();
        this.widgetTitle = widgetTitle;
    }

    public double getValue() {
        return networkTableEntry.getDouble(DEFAULT_VALUE);
    }
    public boolean hasChanged(){
        if(value != getValue()){
            value = getValue();
            Logger.recordOutput(widgetTitle , value);
            return true;
        }
        return false;
    }
}
