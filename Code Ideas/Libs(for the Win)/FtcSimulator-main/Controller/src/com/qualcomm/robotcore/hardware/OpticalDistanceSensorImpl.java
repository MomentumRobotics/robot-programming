package com.qualcomm.robotcore.hardware;

public class OpticalDistanceSensorImpl implements OpticalDistanceSensor{
    private double distance = Double.MAX_VALUE;

    @Override
    public double getLightDetected() {
        return distance;
    }


    //NOTE: with this virtual sensor, can use getRawLightDetected() getRawLightDetectedMax() to make sure sensor is showing valid value
    @Override
    public double getRawLightDetected() {
        return distance;
    }

    @Override
    public double getRawLightDetectedMax() {
        return Double.MAX_VALUE;
    }

    @Override
    public void enableLed(boolean enable) {
        //NO-OP
    }

    @Override
    public String status() {
        return "Virtual optical distance sensor connected";
    }

    @Override
    public Object updateState(double milliseconds, Object doubleDistance) {
        distance = (double)doubleDistance;
        return null;
    }
}
