package com.qualcomm.hardware.modernrobotics;

import com.qualcomm.robotcore.hardware.ColorSensor;
import virtual_robot.controller.VirtualRobotController;

public class ModernRoboticsI2cColorSensor implements ColorSensor {
    ColorSensor sensor;

    public ModernRoboticsI2cColorSensor(ColorSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public int red() {
       return sensor.red();
    }

    @Override
    public int green() {
        return sensor.green();
    }

    @Override
    public int blue() {
        return sensor.blue();
    }

    @Override
    public int alpha() {
        return sensor.alpha();
    }

    @Override
    public int argb() {
        return sensor.argb();
    }

    @Override
    public Object updateState(double milliseconds, Object state) {
        return sensor.updateState(milliseconds, state);
    }

    public void updateColor(double x, double y) {
        ((VirtualRobotController.ColorSensorImpl)sensor).updateColor(x, y);
    }
};