/*
 * Copyright (c) 2021 Titan Robotics Club (http://www.titanrobotics.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package teamcode.ftc3543;

import TrcCommonLib.trclib.TrcDriveBase;
import TrcCommonLib.trclib.TrcGyro;
import TrcCommonLib.trclib.TrcMecanumDriveBase;
import TrcCommonLib.trclib.TrcPidController;
import TrcCommonLib.trclib.TrcPidDrive;
import TrcCommonLib.trclib.TrcRobot;
import TrcFtcLib.ftclib.FtcBNO055Imu;
import TrcFtcLib.ftclib.FtcDashboard;
import TrcFtcLib.ftclib.FtcOpMode;
import TrcFtcLib.ftclib.FtcDcMotor;

/**
 * This class creates the robot object that consists of sensors, indicators, drive base and all the subsystems.
 */
public class Robot
{
    public enum DriveMode
    {
        TANK_MODE,
        HOLONOMIC_MODE,
    }   //enum DriveMode

    //
    // Global objects.
    //
    public FtcOpMode opMode;
    public FtcDashboard dashboard;
    //
    // Sensors and indicators.
    //
    public FtcBNO055Imu imu;
    public TrcGyro gyro;
    //
    // DriveBase subsystem.
    //
    public FtcDcMotor leftFrontWheel = null;
    public FtcDcMotor rightFrontWheel = null;
    public FtcDcMotor leftBackWheel = null;
    public FtcDcMotor rightBackWheel = null;

    public TrcDriveBase driveBase = null;
    public DriveMode driveMode = DriveMode.HOLONOMIC_MODE;
    public TrcPidController encoderXPidCtrl = null;
    public TrcPidController encoderYPidCtrl = null;
    public TrcPidController gyroPidCtrl = null;
    public TrcPidDrive pidDrive = null;

    // Pure Pursuit PID controllers.
    public TrcPidController.PidCoefficients xPosPidCoeff = null;
    public TrcPidController.PidCoefficients yPosPidCoeff = null;
    public TrcPidController.PidCoefficients turnPidCoeff = null;
    public TrcPidController.PidCoefficients velPidCoeff = null;

    //
    // Subsystems.
    //

    /**
     * Constructor: Create an instance of the object.
     *
     * @param runMode specifies robot running mode (Auto, TeleOp, Test), can be used to create and initialize mode
     *                specific sensors and subsystems if necessary.
     */
    public Robot(TrcRobot.RunMode runMode)
    {
        //
        // Initialize global objects.
        //
        opMode = FtcOpMode.getInstance();
        opMode.hardwareMap.logDevices();
        dashboard = FtcDashboard.getInstance();
        //
        // Initialize sensors.
        //
        imu = new FtcBNO055Imu(RobotInfo.HWNAME_IMU);
        gyro = imu.gyro;
        //
        // Initialize DriveBase.
        //
        initDriveBase();
        //
        // Initialize other subsystems.
        //

    }   //Robot

    /**
     * This method is call when the robot mode is about to start. It contains code to initialize robot hardware
     * necessary for running the robot mode.
     *
     * @param runMode specifies the robot mode it is about to start, can be used to initialize mode specific hardware.
     */
    public void startMode(TrcRobot.RunMode runMode)
    {
        final String funcName = "startMode";
        //
        // Since the IMU gyro is giving us cardinal heading, we need to enable its cardinal to cartesian converter.
        //
        if (gyro != null)
        {
            gyro.setEnabled(true);
        }
        //
        // Enable odometry only for autonomous or test modes.
        //
        if (driveBase != null && (runMode == TrcRobot.RunMode.AUTO_MODE || runMode == TrcRobot.RunMode.TEST_MODE))
        {
            leftFrontWheel.setOdometryEnabled(true);
            rightFrontWheel.setOdometryEnabled(true);
            leftBackWheel.setOdometryEnabled(true);
            rightBackWheel.setOdometryEnabled(true);
            driveBase.setOdometryEnabled(true);
        }
    }   //startMode

    /**
     * This method is call when the robot mode is about to end. It contains code to cleanup robot hardware before
     * exiting the robot mode.
     *
     * @param runMode specifies the robot mode it is about to start, can be used to cleanup mode specific hardware.
     */
    public void stopMode(TrcRobot.RunMode runMode)
    {
        final String funcName = "stopMode";
        //
        // Disable odometry.
        //
        if (driveBase != null)
        {
            driveBase.setOdometryEnabled(false);
            leftFrontWheel.setOdometryEnabled(false);
            rightFrontWheel.setOdometryEnabled(false);
            leftBackWheel.setOdometryEnabled(false);
            rightBackWheel.setOdometryEnabled(false);
        }
        //
        // Disable gyro task.
        //
        if (gyro != null)
        {
            gyro.setEnabled(false);
        }
    }   //stopMode

    /**
     * This method creates and initializes the drive base related components.
     */
    private void initDriveBase()
    {
        leftFrontWheel = new FtcDcMotor(RobotInfo.HWNAME_LEFT_FRONT_WHEEL);
        rightFrontWheel = new FtcDcMotor(RobotInfo.HWNAME_RIGHT_FRONT_WHEEL);
        leftBackWheel = new FtcDcMotor(RobotInfo.HWNAME_LEFT_BACK_WHEEL);
        rightBackWheel = new FtcDcMotor(RobotInfo.HWNAME_RIGHT_BACK_WHEEL);

        leftFrontWheel.motor.setMode(RobotInfo.DRIVE_MOTOR_MODE);
        rightFrontWheel.motor.setMode(RobotInfo.DRIVE_MOTOR_MODE);
        leftBackWheel.motor.setMode(RobotInfo.DRIVE_MOTOR_MODE);
        rightBackWheel.motor.setMode(RobotInfo.DRIVE_MOTOR_MODE);

        leftFrontWheel.setInverted(RobotInfo.LEFT_WHEEL_INVERTED);
        leftBackWheel.setInverted(RobotInfo.LEFT_WHEEL_INVERTED);
        rightFrontWheel.setInverted(RobotInfo.RIGHT_WHEEL_INVERTED);
        rightBackWheel.setInverted(RobotInfo.RIGHT_WHEEL_INVERTED);

        leftFrontWheel.setBrakeModeEnabled(RobotInfo.DRIVE_WHEEL_BRAKE_MODE);
        leftBackWheel.setBrakeModeEnabled(RobotInfo.DRIVE_WHEEL_BRAKE_MODE);
        rightFrontWheel.setBrakeModeEnabled(RobotInfo.DRIVE_WHEEL_BRAKE_MODE);
        rightBackWheel.setBrakeModeEnabled(RobotInfo.DRIVE_WHEEL_BRAKE_MODE);

        driveBase = new TrcMecanumDriveBase(leftFrontWheel, leftBackWheel, rightFrontWheel, rightBackWheel, gyro);
//        if (Preferences.useExternalOdometry)
//        {
//            //
//            // Create the external odometry device that uses the left front encoder port as the X odometry and
//            // the left and right back encoder ports as the Y1 and Y2 odometry. Gyro will serve as the angle
//            // odometry.
//            //
//            TrcDriveBaseOdometry driveBaseOdometry = new TrcDriveBaseOdometry(
//                new TrcDriveBaseOdometry.AxisSensor(leftFrontWheel, RobotInfo.X_ODOMETRY_OFFSET),
//                new TrcDriveBaseOdometry.AxisSensor[] {
//                    new TrcDriveBaseOdometry.AxisSensor(leftBackWheel),
//                    new TrcDriveBaseOdometry.AxisSensor(rightBackWheel)},
//                gyro);
//            //
//            // Set the drive base to use the external odometry device overriding the built-in one.
//            //
//            driveBase.setDriveBaseOdometry(driveBaseOdometry);
//        }
        driveBase.setOdometryScales(RobotInfo.ENCODER_X_INCHES_PER_COUNT, RobotInfo.ENCODER_Y_INCHES_PER_COUNT);
        driveMode = DriveMode.HOLONOMIC_MODE;
        //
        // Initialize PID drive.
        //
        xPosPidCoeff = new TrcPidController.PidCoefficients(
            RobotInfo.ENCODER_X_KP, RobotInfo.ENCODER_X_KI, RobotInfo.ENCODER_X_KD);
        yPosPidCoeff = new TrcPidController.PidCoefficients(
            RobotInfo.ENCODER_Y_KP, RobotInfo.ENCODER_Y_KI, RobotInfo.ENCODER_Y_KD);
        turnPidCoeff = new TrcPidController.PidCoefficients(
            RobotInfo.GYRO_KP, RobotInfo.GYRO_KI, RobotInfo.GYRO_KD);
        velPidCoeff = new TrcPidController.PidCoefficients(
            RobotInfo.ROBOT_VEL_KP, RobotInfo.ROBOT_VEL_KI, RobotInfo.ROBOT_VEL_KD, RobotInfo.ROBOT_VEL_KF);

        encoderXPidCtrl = new TrcPidController(
            "encoderXPidCtrl", xPosPidCoeff, RobotInfo.ENCODER_X_TOLERANCE, driveBase::getXPosition);
        encoderYPidCtrl = new TrcPidController(
            "encoderYPidCtrl", yPosPidCoeff, RobotInfo.ENCODER_Y_TOLERANCE, driveBase::getYPosition);
        gyroPidCtrl = new TrcPidController(
            "gyroPidCtrl", turnPidCoeff, RobotInfo.GYRO_TOLERANCE, driveBase::getHeading);
        gyroPidCtrl.setAbsoluteSetPoint(true);
        gyroPidCtrl.setOutputLimit(RobotInfo.TURN_POWER_LIMIT);

        pidDrive = new TrcPidDrive("pidDrive", driveBase, encoderXPidCtrl, encoderYPidCtrl, gyroPidCtrl);
        pidDrive.setAbsoluteTargetModeEnabled(true);
        pidDrive.setStallTimeout(RobotInfo.PIDDRIVE_STALL_TIMEOUT);
    }   //initDriveBase

}   //class Robot
