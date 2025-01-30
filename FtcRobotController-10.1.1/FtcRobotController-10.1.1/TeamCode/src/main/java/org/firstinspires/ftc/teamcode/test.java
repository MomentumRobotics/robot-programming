package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;


@TeleOp(name="AE86" , group="OPMODE")
public class AE86 extends OpMode {
    DcMotor stangaJos;
    DcMotor stangaSus;
    DcMotor dreaptaJos;
    DcMotor dreaptaSus;
    DcMotor motorGlisiere;
    DcMotor motorGlisiere2;
    DcMotor motorBrat;
    Servo servoOrientare;
    CRServo cleste;
    Servo gearboxStanga;
    Servo gearboxDreapta;
    CRServo linearDreapta;
    CRServo linearStanga;

    boolean toggle = false;
    boolean lastButtonState = false;
    boolean toggle2 = false;
    boolean lastButtonState2 = false;

    boolean toggle3 = false; // Keeps track of the toggle state
    boolean lastButtonState3 = false; // Tracks the previous state of the button
    double targetPosition = 0.5; // Target position of the servo
    double currentServoPosition = 0.5; // Current position of the servo
    double servoSpeed = 0.01;
    double newTarget0=0;
    double ticks=1425;
    @Override
    public void init() {
        telemetry.addData("ftc","first");
        telemetry.addData("Initialization","success");
        stangaJos =hardwareMap.get(DcMotor .class,"stangaJos");
        stangaSus =hardwareMap.get(DcMotor .class,"stangaSus");
        dreaptaJos =hardwareMap.get(DcMotor .class,"dreaptaJos");
        dreaptaSus =hardwareMap.get(DcMotor .class,"dreaptaSus");
        dreaptaSus =hardwareMap.get(DcMotor .class,"dreaptaSus");


        motorGlisiere2 =hardwareMap.get(DcMotor .class,"MotorGlisiere2");

        //hardwarebrat
        motorBrat =hardwareMap.get(DcMotor .class,"motorBrat");
        cleste=hardwareMap.get(CRServo.class,"cleste");
        servoOrientare=hardwareMap.get(Servo.class,"servoOrientare");
        gearboxStanga=hardwareMap.get(Servo.class,"gearboxStanga");
        gearboxDreapta=hardwareMap.get(Servo.class,"gearboxDreapta");
        linearDreapta=hardwareMap.get(CRServo.class,"servoLinearDreapta");
        linearStanga=hardwareMap.get(CRServo.class,"servoLinearStanga");


        motorGlisiere =hardwareMap.get(DcMotor.class,"MotorGlisiere");

        stangaJos.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        stangaSus.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        dreaptaJos.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        dreaptaSus.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        motorBrat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBrat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        gearboxDreapta.setDirection(Servo.Direction.FORWARD);

        motorGlisiere.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorGlisiere2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        stangaSus.setDirection(DcMotor.Direction.REVERSE);
        dreaptaSus.setDirection(DcMotor.Direction.REVERSE);
        dreaptaJos.setDirection(DcMotor.Direction.FORWARD);
        stangaJos.setDirection(DcMotor.Direction.FORWARD);

        motorGlisiere.setDirection(DcMotor.Direction.FORWARD);
        motorGlisiere2.setDirection(DcMotor.Direction.REVERSE);
        motorBrat.setDirection(DcMotor.Direction.REVERSE);




        stangaSus.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dreaptaSus.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        dreaptaJos.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        stangaJos.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorGlisiere.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorGlisiere2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        servoOrientare.setPosition(currentServoPosition);

        telemetry.addData("hardware","initialized");

    }

    @Override
    public void loop() {
        double speed = -gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;

        double InSus = gamepad2.left_trigger;
        double InJos = gamepad2.right_trigger;

        boolean currentButtonState = gamepad1.b;


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
        if (InSus > LIMITA_GLISIERE) {
            motorGlisiereSus(InSus - 0.4);
        }
        if (InJos > LIMITA_GLISIERE) {
            motorGlisiereJos(InJos + 0.3);
        }
        else {
            motorGlisiereZero();
        }
        if (gamepad1.dpad_up) {//LOW BASKETA
            linearStanga.setPower(0.2);
            linearDreapta.setPower(0.2);
            encoder(0.2);
            gearboxDreapta.setPosition(0.2);


        }
        if(gamepad1.y){//reset linear
            gearboxDreapta.setPosition(-1);
        }

        if(gamepad1.dpad_down){//luat de jos
            encoder(0.00001);
            ridicatLinear();
            servoOrientare.setPosition(0.);
            gearboxDreapta.setPosition(0.3);//0.1 , 0.4  ,-0.4 , -.3, -0.02,.3
        }

        if(gamepad2.left_trigger>0){
            motorGlisiereSus(InSus);
        }
        if(gamepad2.right_trigger<0){
            motorGlisiereJos(InJos);
            //orientare
        }
        if (currentButtonState && !lastButtonState) {
            toggle = !toggle; // Flip the toggle state
        }
        if (toggle) {
            servoOrientare.setPosition(0.28);
            telemetry.addData("positie orientare: ",servoOrientare.getPosition());// Position for "on" state
        } else {
            servoOrientare.setPosition(0.3);
            telemetry.addData("positie orientare: ",servoOrientare.getPosition());// Position for "off" state (adjust as needed)
        }
      if(gamepad1.a){
          cleste.setPower(0.1);
      }
      else{
          cleste.setPower(-0.5);
      }
        telemetry.update();

        // Save the current button state for the next loop
        lastButtonState = currentButtonState;





        telemetry.addData("pos y joystick: ", speed);
        telemetry.addData("glisiere : ", InJos);
        telemetry.addData("glisiere : ", InSus);
        telemetry.addData("servo liniar : ", linearDreapta.getPower());
        telemetry.addData("servo liniar : ", linearStanga.getPower());
        if (((DcMotorEx) motorBrat).isOverCurrent()) {
            telemetry.addLine("MOTOR EXCEEDED CURRENT LIMIT!");

        }
        telemetry.addData("arm Encoder: ", motorBrat.getCurrentPosition());
        telemetry.addData("gearbox stanga : ",gearboxStanga.getPosition());
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
        stangaJos.setPower(-putereStanga);
        dreaptaSus.setPower(putereDreapta);
        dreaptaJos.setPower(putereDreapta);
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
        motorGlisiere2.setPower(-InJos);
    }
    public void motorGlisiereSus(double InSus){
        motorGlisiere.setPower(InSus);
        motorGlisiere2.setPower(InSus);
    }
    public void motorGlisiereZero(){
        motorGlisiere.setPower(0);
        motorGlisiere2.setPower(0);
    }
    public void encoder(double val){
        newTarget0=-ticks*val;
        motorBrat.setTargetPosition((int) newTarget0);
        motorBrat.setPower(0.08);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void reset(){

        motorBrat.setTargetPosition(0);
        motorBrat.setPower(0.2);
        motorBrat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void ridicatLinear(){
        linearStanga.setPower(0.27);
        linearDreapta.setPower(0.27);

    }
    public void resetLinear(){
        linearStanga.setPower(-0.5);
        linearDreapta.setPower(-0.5);

    }
    public void highChamber(){
        ridicatLinear();
        encoder(0.28);
    }

    public static final double STICK_THRESH=0.20;
    public static final double TURN_POWER=0.8;
    public static final double LIMITA_GLISIERE=0.1;

}
