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

import com.qualcomm.robotcore.hardware.DcMotor;

import TrcCommonLib.trclib.TrcPose2D;

public class RobotInfo
{
    //
    // Hardware names.
    //
    static final String HWNAME_IMU                              = "imu";
    static final String HWNAME_LEFT_FRONT_WHEEL                 = "front_left_motor";
    static final String HWNAME_RIGHT_FRONT_WHEEL                = "front_right_motor";
    static final String HWNAME_LEFT_BACK_WHEEL                  = "back_left_motor";
    static final String HWNAME_RIGHT_BACK_WHEEL                 = "back_right_motor";
    //
    // DriveBase subsystem.
    //
    static final DcMotor.RunMode DRIVE_MOTOR_MODE               = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    static final boolean LEFT_WHEEL_INVERTED                    = false;
    static final boolean RIGHT_WHEEL_INVERTED                   = true;
    static final boolean DRIVE_WHEEL_BRAKE_MODE                 = true;
    static final double TURN_POWER_LIMIT                        = 0.5;
    static final double SLOW_DRIVE_POWER_SCALE                  = 0.5;
    static final double X_ODOMETRY_OFFSET                       = 8.0;  //8 inches in front of centroid
    //
    // Velocity controlled constants.
    //
    static final double MOTOR_MAX_VELOCITY                      = 2800;     //encoder counts/second

    static final double ENCODER_X_KP                            = 0.095;
    static final double ENCODER_X_KI                            = 0.0;
    static final double ENCODER_X_KD                            = 0.001;
    static final double ENCODER_X_TOLERANCE                     = 1.0;
    static final double ENCODER_X_INCHES_PER_COUNT              = 0.0163125145666872;

    static final double ENCODER_Y_KP                            = 0.06;
    static final double ENCODER_Y_KI                            = 0.0;
    static final double ENCODER_Y_KD                            = 0.002;
    static final double ENCODER_Y_TOLERANCE                     = 1.0;
    static final double ENCODER_Y_INCHES_PER_COUNT              = 0.0174484434975099;

    static final double GYRO_KP                                 = 0.009;
    static final double GYRO_KI                                 = 0.0;
    static final double GYRO_KD                                 = 0.0005;
    static final double GYRO_TOLERANCE                          = 2.0;

    static final double PIDDRIVE_STALL_TIMEOUT                  = 0.2;  //in seconds.
    //
    // Pure Pursuit parameters.
    //
    // Neverest 40 motor, max shaft speed = 160 RPM
    // motor-to-wheel tooth ratio = 24:16 = 3:2
    // wheel max angular speed = (3 / 2) * 160 RPM
    // max tangential speed of wheel (in/s) = wheel max angular speed * 2 * pi * radius / 60.0
    // = (3 / 2) * (160 RPM) * 2 * 3.1415926 * (2 in.) / 60.0
    // = 50.2654816 in./sec.
    static final double ROBOT_MAX_VELOCITY                      = 50.2654816;
    static final double ROBOT_MAX_ACCELERATION                  = 24.0;

    static final double ROBOT_VEL_KP                            = 0.0;
    static final double ROBOT_VEL_KI                            = 0.0;
    static final double ROBOT_VEL_KD                            = 0.0;
    // KF should be set to the reciprocal of max tangential velocity (time to travel unit distance), units: sec./in.
    static final double ROBOT_VEL_KF                            = 1.0 / ROBOT_MAX_VELOCITY;
    //
    // Assuming the robot is placed at the center of the field for which we will set as field origin (i.e. x=0, y=0,
    // heading=0), this path will drive an infinity pattern.
    //
    static final TrcPose2D[] PURE_PURSUIT_PATH = new TrcPose2D[]{
        new TrcPose2D(-24.0, 0, 45.0),
        new TrcPose2D(-24.0, 48.0, 135.0),
        new TrcPose2D(24.0, 48.0, 225.0),
        new TrcPose2D(0.0, 46.0, 270.0),
        new TrcPose2D(0.0, 0.0, 0.0),
        new TrcPose2D(-23.0, 47.0, 225.0),
        new TrcPose2D(0.0, 0.0, 0.0)
    };
}   //class RobotInfo
