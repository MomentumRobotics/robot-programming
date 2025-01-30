package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.HardwareMap;
import static org.firstinspires.ftc.teamcode.MotoareSiChestii.STICK_THRESH;
import com.qualcomm.robotcore.util.ElapsedTime;
import static org.firstinspires.ftc.teamcode.MotoareSiChestii.TURN_POWER;
import static org.firstinspires.ftc.teamcode.MotoareSiChestii.LIMITA_GLISIERE;


import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp(name="AE86" , group="OPMODE")
public class AE86FINALL extends LinearOpMode {

    public DcMotor motorBrat = null; //the arm motor
    final double TICKS_PE_360 = 1425.1;
    final double ARM_COLLECT = 0.5 * TICKS_PE_360;
    ElapsedTime runtime = new ElapsedTime();
    final double ARM_COLLAPSED_INTO_ROBOT = 0;
    double armPosition = (int) ARM_COLLAPSED_INTO_ROBOT;

    @Override
    public void runOpMode() throws InterruptedException {
        MotoareSiChestii MotoareSiChestii = new MotoareSiChestii(hardwareMap, telemetry);

        motorBrat = hardwareMap.get(DcMotor.class, "motorBrat");
        motorBrat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ((DcMotorEx) motorBrat).setCurrentAlert(5, CurrentUnit.AMPS);
        motorBrat.setTargetPosition(0);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addLine("Robot Ready.");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            double speed = -gamepad1.left_stick_y;
            double turn = gamepad1.left_stick_x;
            double inSus = gamepad2.right_trigger;
            double inJos = gamepad2.left_trigger;

            if (speed < -STICK_THRESH || speed > STICK_THRESH) {
                double putereStanga = Range.clip(speed, -1.0, 1);
                double putereDreapta = Range.clip(speed - turn, -1.0, 1);

                MotoareSiChestii.InFata(putereDreapta, putereStanga);

            } else if (gamepad1.left_stick_x < -STICK_THRESH || gamepad1.left_stick_x > STICK_THRESH) {
                MotoareSiChestii.InStanga(-gamepad1.left_stick_x);
            } else {
                MotoareSiChestii.InFata(0, 0);
            }
            if (gamepad1.right_trigger > STICK_THRESH) {
                MotoareSiChestii.IntorsDreapta(TURN_POWER);
            }
            if (gamepad1.left_trigger > STICK_THRESH) {
                MotoareSiChestii.IntorsStanga(TURN_POWER);
            }
            if (inSus > LIMITA_GLISIERE) {
                MotoareSiChestii.motorGlisiereSus(inSus - 0.01);
            }
            if (inJos > LIMITA_GLISIERE) {
                MotoareSiChestii.motorGlisiereJos(inJos + 0.2);
            } else {
                MotoareSiChestii.motorGlisiereZero();
            }
            if (gamepad1.a) {
                armPosition = ARM_COLLECT;
            }


            telemetry.addData("pos y joystick: ", speed);
            telemetry.addData("glisiere : ", inJos);
            telemetry.addData("glisiere : ", inSus);
            if (((DcMotorEx) motorBrat).isOverCurrent()) {
                telemetry.addLine("MOTOR EXCEEDED CURRENT LIMIT!");

            }
            telemetry.addData("arm Encoder: ", motorBrat.getCurrentPosition());
            telemetry.update();
        }


    }
}
    /*public void encoderBratSus(double turnage){
        motorBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        newTarget0=ticks*turnage;
        motorBrat.setTargetPosition((int)newTarget0);
        motorBrat.setPower(0.2);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

