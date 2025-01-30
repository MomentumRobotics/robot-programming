package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="AE86" , group="OPMODE")
public class AE86 extends OpMode {
    DcMotor stangaJos;
    DcMotor stangaSus;
    DcMotor dreaptaJos;
    DcMotor dreaptaSus;
    DcMotor motorGlisiere;
    DcMotor motorBrat;
    double newTarget0=0;
    double ticks=1400;
    @Override
    public void init() {
        telemetry.addData("ftc","first");
        telemetry.addData("Initialization","success");
        stangaJos =hardwareMap.get(DcMotor .class,"stangaJos");
        stangaSus =hardwareMap.get(DcMotor .class,"stangaSus");
        dreaptaJos =hardwareMap.get(DcMotor .class,"dreaptaJos");
        dreaptaSus =hardwareMap.get(DcMotor .class,"dreaptaSus");

        motorBrat =hardwareMap.get(DcMotor .class,"motorBrat");

        motorGlisiere =hardwareMap.get(DcMotor .class,"MotorGlisiere");

        stangaJos.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        stangaSus.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        dreaptaJos.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        dreaptaSus.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBrat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        motorGlisiere.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        stangaSus.setDirection(DcMotor.Direction.REVERSE);
        dreaptaSus.setDirection(DcMotor.Direction.REVERSE);
        dreaptaJos.setDirection(DcMotor.Direction.FORWARD);
        stangaJos.setDirection(DcMotor.Direction.FORWARD);

        motorGlisiere.setDirection(DcMotor.Direction.FORWARD);




        stangaSus.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dreaptaSus.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dreaptaJos.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        stangaJos.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorGlisiere.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("hardware","initialized");

    }

    @Override
    public void loop() {
        double speed = -gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;
        double inSus = gamepad2.left_trigger;
        double inJos = gamepad2.right_trigger;

        if (speed < -STICK_THRESH || speed > STICK_THRESH) {
            double putereStanga = Range.clip(speed, -1.0, 1);
            double putereDreapta = Range.clip(speed - turn, -1.0, 1);

            InFata(putereDreapta, putereStanga);

        } else if (gamepad1.left_stick_x < -STICK_THRESH || gamepad1.left_stick_x > STICK_THRESH) {
            InStanga(-gamepad1.left_stick_x);
        } else {
            InFata(0, 0);
        }
        if (gamepad1.right_trigger > STICK_THRESH) {
            IntorsDreapta(TURN_POWER);
        }
        if (gamepad1.left_trigger > STICK_THRESH) {
            IntorsStanga(TURN_POWER);
        }
        if (inSus > LIMITA_GLISIERE) {
            motorGlisiereSus(inSus - 0.01);
        }
        if (inJos > LIMITA_GLISIERE) {
            motorGlisiereJos(inJos + 0.2);
        } else {
            motorGlisiereZero();
        }
        if (gamepad1.a) {
            encoder(-0.3);
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
    public void motoareStanga(double putereStanga){
        stangaSus.setPower(putereStanga);
        stangaJos.setPower(putereStanga);
    }
    public void motoareDreapta(double putereDreapta){
        dreaptaJos.setPower(putereDreapta);
        dreaptaSus.setPower(putereDreapta);
    }

    public void InFata(double putereDreapta, double putereStanga){
        stangaSus.setPower(-putereStanga);
        stangaJos.setPower(putereStanga);
        dreaptaSus.setPower(putereDreapta);
        dreaptaJos.setPower(-putereDreapta);
    }
    public void InSpate(double putere){
        stangaSus.setPower(-putere);
        stangaJos.setPower(-putere);
        dreaptaSus.setPower(-putere);
        dreaptaJos.setPower(-putere);
    }
    public void InDreapta(double putere){
        stangaSus.setPower(putere);
        stangaJos.setPower(-putere);
        dreaptaSus.setPower(-putere);
        dreaptaJos.setPower(putere);
    }
    public void InStanga(double putere){
        stangaSus.setPower(putere);
        stangaJos.setPower(putere);
        dreaptaSus.setPower(putere);
        dreaptaJos.setPower(putere);
    }
    public void IntorsDreapta(double putere){
        stangaSus.setPower(-putere);
        stangaJos.setPower(putere);
        dreaptaSus.setPower(-putere);
        dreaptaJos.setPower(putere);
    }
    public void IntorsStanga(double putere){
        stangaSus.setPower(putere);
        stangaJos.setPower(-putere);
        dreaptaSus.setPower(putere);
        dreaptaJos.setPower(-putere);
    }
    public void motorGlisiereJos(double InJos){
        motorGlisiere.setPower(-InJos);
    }
    public void motorGlisiereSus(double InSus){
        motorGlisiere.setPower(InSus);
    }
    public void motorGlisiereZero(){
        motorGlisiere.setPower(0);
    }
    public void Sus(double turnage){
        newTarget0=ticks/turnage;
        motorBrat.setTargetPosition((int) newTarget0);
        motorBrat.setPower(0.2);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void reset(double turnage1){

        motorBrat.setTargetPosition(0);
        motorBrat.setPower(0.2);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public static final double STICK_THRESH=0.20;
    public static final double TURN_POWER=0.8;
    public static final double LIMITA_GLISIERE=0.1;
}
