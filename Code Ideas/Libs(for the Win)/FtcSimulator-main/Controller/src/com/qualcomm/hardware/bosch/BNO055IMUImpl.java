package com.qualcomm.hardware.bosch;

import com.qualcomm.robotcore.util.RobotLog;

import virtual_robot.controller.VirtualBot;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * Implementation of the BNO055IMU interface
 */
public class BNO055IMUImpl implements BNO055IMU {
    private VirtualBot bot = null;
    private Parameters parameters = null;
    private double initialHeadingRadians = 0;
    private double headingRadians = 0;
    private boolean initialized = false;

    private long latencyNanos = 0;
    private long prevNanos = System.nanoTime();
    private AngularVelocity angularVelocity;

    public BNO055IMUImpl(VirtualBot bot){
        this.bot = bot;
    }

    public BNO055IMUImpl(VirtualBot bot, int latencyMillis){
        this.bot = bot;
        latencyNanos = latencyMillis * 1000000;
    }


    /**
     * Initialize the BNO055IMU
     * @param parameters Parameters object
     * @return true to indicate initialization was successful
     */
    public synchronized boolean initialize(Parameters parameters){
        initialized = true;
        this.parameters = parameters;
        double tempHeadingRadians = bot.getHeadingRadians();
        headingRadians = tempHeadingRadians;
        initialHeadingRadians = tempHeadingRadians;
        return true;
    }

    public synchronized Parameters getParameters() { return parameters; }

    /**
     * Close the BNO055IMU
     */
    public synchronized void close(){
        initialized = false;
        headingRadians = 0;
        initialHeadingRadians = 0;
    }

    /**
     * Get the angular orientation (as an Orientation object), using the AxesReference, AxesOrder, and AngleUnit
     * specified by the imu's Parameters object
     * @return angular orientation
     */
    public synchronized Orientation getAngularOrientation() {
        org.firstinspires.ftc.robotcore.external.navigation.AngleUnit angleUnit = parameters.angleUnit == AngleUnit.DEGREES ?
                org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES :
                org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS;
        return getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, angleUnit);
    }

    Quaternion ToQuaternion(float yaw, float pitch, float roll) // yaw (Z), pitch (Y), roll (X)
    {
        // Abbreviations for the various angular functions
        double cy = Math.cos(yaw * 0.5);
        double sy = Math.sin(yaw * 0.5);
        double cp = Math.cos(pitch * 0.5);
        double sp = Math.sin(pitch * 0.5);
        double cr = Math.cos(roll * 0.5);
        double sr = Math.sin(roll * 0.5);

        Quaternion q = new Quaternion(
                (float)(cr * cp * cy + sr * sp * sy),
                (float)(sr * cp * cy - cr * sp * sy),
                (float)(cr * sp * cy + sr * cp * sy),
                (float)(cr * cp * sy - sr * sp * cy),
        0);

        return q;
    }

    @Override
    public Quaternion getQuaternionOrientation() {
        Orientation orientation = getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS);
        return ToQuaternion(orientation.firstAngle, orientation.secondAngle, orientation.thirdAngle);
    }

    @Override
    public Acceleration getAcceleration() {
        RobotLog.i("Virtual BNO055IMU does not implement acceleration.");
        return new Acceleration();
    }

    @Override
    public Velocity getVelocity() {
        RobotLog.i("Virtual BNO055IMU does not implement velocity.");
        return new Velocity();
    }

    @Override
    public Position getPosition() {
        RobotLog.i("Virtual BNO055IMU does not implement position.");
        return new Position();
    }

    /**
     * Get the angular orientation (as an Orientation object), using the AxesReference, AxesOrder, and AngleUnit
     * specified by the arguments
     * @param reference axes reference
     * @param order axes order
     * @param angleUnit angle unit
     * @return angular orientation
     */
    public synchronized Orientation getAngularOrientation(AxesReference reference, AxesOrder order, org.firstinspires.ftc.robotcore.external.navigation.AngleUnit angleUnit) {
        if (!initialized) return null;

        double heading = headingRadians - initialHeadingRadians;
        if (heading > Math.PI) heading -= 2.0 * Math.PI;
        else if (heading < -Math.PI) heading += 2.0 * Math.PI;

        double piOver2;
        double firstAngle = 0.0, secondAngle = 0.0, thirdAngle = 0.0;
        if (angleUnit == org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES) {
            heading *= 180.0 / Math.PI;
            piOver2 = Math.PI / 2.0;
        } else {
            piOver2 = 90.0;
        }

        switch (order) {
            case ZXY: case ZXZ: case ZYX: case ZYZ:
                firstAngle = heading;
                break;
            case XZX: case XZY: case YZX: case YZY:
                secondAngle = heading;
                break;
            case XYZ: case YXZ:
                thirdAngle = heading;
                break;
            case YXY:
                secondAngle = heading;
                if (reference == AxesReference.INTRINSIC){
                    firstAngle = -piOver2;
                    thirdAngle = piOver2;
                } else {
                    firstAngle = piOver2;
                    thirdAngle = -piOver2;
                }
                break;
            case XYX:
                secondAngle = heading;
                if (reference == AxesReference.INTRINSIC){
                    firstAngle = piOver2;
                    thirdAngle = -piOver2;
                } else {
                    firstAngle = -piOver2;
                    thirdAngle = piOver2;
                }
        }
        return new Orientation(reference, order, angleUnit, (float)firstAngle, (float)secondAngle, (float)thirdAngle,
                System.nanoTime());

    }


    public synchronized AngularVelocity getAngularVelocity(org.firstinspires.ftc.robotcore.external.navigation.AngleUnit unit)
    {
        return angularVelocity.toAngleUnit(unit);
    }

    @Override
    public synchronized AngularVelocity getAngularVelocity()
    {
        org.firstinspires.ftc.robotcore.external.navigation.AngleUnit angleUnit = parameters.angleUnit == AngleUnit.DEGREES ?
                org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES :
                org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS;
        return getAngularVelocity(angleUnit);
    }

    /**
     * For internal use only
     * @param millis
     * @param headingRadiansDouble
     * @return
     */
    public synchronized Object updateState(double millis, Object headingRadiansDouble) {
        double headingDouble = (double)headingRadiansDouble;
        long nanos = System.nanoTime();
        if (nanos < (prevNanos + latencyNanos)) return null;

        // calculate velocity as rate of change of headingRadians per unit time
        float zVelocity = (float) ((headingDouble - headingRadians) / (prevNanos - nanos));
        angularVelocity =
                new AngularVelocity(org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS,
                        0, 0, zVelocity, nanos);

        headingRadians = headingDouble;
        prevNanos = nanos;
        return null;
    }

}
