package edu.greenblitz.robotName.utils.tuneableNumber;

import java.util.function.Consumer;

public class AutoSetterTunableNumber extends TunableNumber {
    private Consumer<Double> valueSettingFunction;

    public AutoSetterTunableNumber(String widgetTitle, String shuffleBoardTabTitle, Consumer<Double> valueSettingFunction) {
        super(widgetTitle, shuffleBoardTabTitle);
        this.valueSettingFunction = valueSettingFunction;
    }

    @Override
    public boolean hasChanged() {
        boolean hasChanged = super.hasChanged();
        if (hasChanged)
            valueSettingFunction.accept(value);
        return hasChanged;
    }

}
