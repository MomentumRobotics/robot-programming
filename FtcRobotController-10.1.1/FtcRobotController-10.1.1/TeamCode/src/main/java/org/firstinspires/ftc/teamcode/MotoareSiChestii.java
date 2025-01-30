package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class MotoareSiChestii  {

    DcMotor stangaJos;
    DcMotor stangaSus;
    DcMotor dreaptaJos;
    DcMotor dreaptaSus;
    HardwareMap hardwareMap;
    DcMotor motorglisiere;
    DcMotor motorBrat;
    Servo motorBratPrimar;
    double ticks=1425;

    public MotoareSiChestii(HardwareMap hardwareMap, Telemetry telemetry){
        this.hardwareMap=hardwareMap;
        telemetry.addData("ftc","first");
        telemetry.addData("Initialization","success");
        stangaJos =hardwareMap.get(DcMotor .class,"stangaJos");
        stangaSus =hardwareMap.get(DcMotor .class,"stangaSus");
        dreaptaJos =hardwareMap.get(DcMotor .class,"dreaptaJos");
        dreaptaSus =hardwareMap.get(DcMotor .class,"dreaptaSus");
        motorglisiere=hardwareMap.get(DcMotor.class , "MotorGlisiere");
        motorBrat=hardwareMap.get(DcMotor.class,"motorBrat");
        motorBratPrimar=hardwareMap.get(Servo.class, "servoBratPrimar");

        //motoare
        stangaJos.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        stangaSus.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        dreaptaJos.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        dreaptaSus.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //motoare brat,glisiere
        motorglisiere.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);;
        motorBrat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        stangaSus.setDirection(DcMotor.Direction.REVERSE);
        dreaptaSus.setDirection(DcMotor.Direction.REVERSE);
        dreaptaJos.setDirection(DcMotor.Direction.FORWARD);
        stangaJos.setDirection(DcMotor.Direction.FORWARD);

        motorglisiere.setDirection(DcMotor.Direction.FORWARD);

        stangaSus.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dreaptaSus.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dreaptaJos.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        stangaJos.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorglisiere.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("hardware","initialized");

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
    public void motorGlisiereSus(double Insus){
        motorglisiere.setPower(Insus);
    }
    public void motorGlisiereJos(double Injos){
        motorglisiere.setPower(-Injos);
    }
    public void motorGlisiereZero(){
        motorglisiere.setPower(0);
    }


    public void encoderBratSus(double turnage)
    {
        double newTarget=ticks*turnage;
        motorBrat.setTargetPosition((int) newTarget);
        motorBrat.setPower(0.8);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public static final double STICK_THRESH=0.20;
    public static final double TURN_POWER=0.8;
    public static final double LIMITA_GLISIERE=0.1;



}