package edu.greenblitz.robotName.utils;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class TunableNumber {
    private static final double DEFAULT_VALUE = 0;
    private GenericEntry networkTableEntry;

    public TunableNumber (String widgetTitle,String shuffleBoardTabTitle){
        networkTableEntry = Shuffleboard.getTab(shuffleBoardTabTitle).add(widgetTitle, DEFAULT_VALUE).getEntry();
    }

    public double getValue(){
        return networkTableEntry.getDouble(DEFAULT_VALUE);
    }
}
