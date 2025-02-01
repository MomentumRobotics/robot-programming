package com.qualcomm.robotcore.hardware;

public class VoltageSensor implements HardwareDevice{
    double voltage = 12.5;

    @Override
    public Object updateState(double milliseconds, Object doubleVoltage) {
        voltage = (double)doubleVoltage;
        return null;
    }

    public double getVoltage() {
        return voltage;
    }
}
