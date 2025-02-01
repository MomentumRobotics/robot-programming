package com.qualcomm.robotcore.hardware;

public class AnalogOutput implements HardwareDevice {
    public void setAnalogOutputMode(byte outputMode) {
        throw new UnsupportedOperationException("You can help expand virtual_robot if you implement this.");
    }

    public void setAnalogOutputFrequency(int frequency) {
        throw new UnsupportedOperationException("You can help expand virtual_robot if you implement this.");
    }

    public void setAnalogOutputVoltage(int i) {
        throw new UnsupportedOperationException("You can help expand virtual_robot if you implement this.");
    }

    public Object updateState(double millis, Object state) { return 0.0; }
}
