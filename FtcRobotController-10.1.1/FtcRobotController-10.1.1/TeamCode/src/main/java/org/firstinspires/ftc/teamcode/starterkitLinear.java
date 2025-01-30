package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name="Ballin'")
public class AUTONOMIE extends LinearOpMode {
    DcMotor stangaSus;//
    DcMotor dreaptaSus;//
    DcMotor stangaJos;//
    DcMotor dreaptaJos;
    double ticksSasiu=537.7;
    double newTarget1sj;
    double newTarget1ss;
    double newTarget1dj;
    double newTarget1ds;
    @Override
    public void runOpMode() throws InterruptedException {
        stangaSus=hardwareMap.get(DcMotor.class, "stangaSus");
        stangaSus.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        stangaSus.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        stangaSus.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        stangaJos=hardwareMap.get(DcMotor.class, "stangaJos");
        stangaJos.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        stangaJos.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        stangaJos.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        dreaptaSus=hardwareMap.get(DcMotor.class, "dreaptaSus");
        dreaptaSus.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dreaptaSus.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dreaptaSus.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        dreaptaJos=hardwareMap.get(DcMotor.class, "dreaptaJos");
        dreaptaJos.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dreaptaJos.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dreaptaJos.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetrie();

        waitForStart();

        InFata(2.5);// aici a  mers in fata cosului

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InStanga(-1);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        turnaround(-1.61);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InFata(0.5);//aici ajunge in fata cubului

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InFata(-0.5);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        turnaround(1.55);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InStanga(1);//asta acum e toata secventa care il face sa mearga la primul cub si inapoi sa cos
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InStanga(-1);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InFata(0.77);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        turnaround(-1.70);//la dreapta
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InFata(0.7);//AJUNGE IN FATA LA AL 2 LEA CUB
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InFata(-0.6);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        turnaround(1.6);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InFata(-0.5);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InStanga(1);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        InStanga(-1);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }InStanga(1);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);}
        InStanga(-1.1);








    }
    public void InFata(double rotatii){
        stangaSus.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        stangaJos.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dreaptaSus.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dreaptaJos.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // aici  e gen ca sa mearga in fata, au tick-uri negative motoarele de stanga parca, I FoRgOr
        newTarget1dj=ticksSasiu*(-rotatii);
        newTarget1ds=ticksSasiu*(-rotatii);
        newTarget1sj=ticksSasiu*rotatii;
        newTarget1ss=ticksSasiu*rotatii;

        //aici e poszitia la care   trebuie sa ajunga, modul de a ajunge la cea pozitie si puterea cu care sa mearga motorul
        stangaJos.setTargetPosition((int) newTarget1sj);
        stangaJos.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // acum repet pentru celelate patru motoare

        //aa si valori negative pentru a merge in fata... hopefully
        stangaSus.setTargetPosition((int) newTarget1ss);
        stangaSus.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        dreaptaJos.setTargetPosition((int) newTarget1dj);
        dreaptaJos.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        //ma intreb daca ar trbui schimbat acel int din paranteza, sper sa nu tho
        dreaptaSus.setTargetPosition((int)newTarget1ds);
        dreaptaSus.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if(rotatii>0) {
            dreaptaSus.setPower(-0.5);
            dreaptaJos.setPower(-0.5);
            stangaSus.setPower(0.5);
            stangaJos.setPower(0.5);
        }
        else{
            dreaptaSus.setPower(0.5);
            dreaptaJos.setPower(0.5);
            stangaSus.setPower(-0.5);
            stangaJos.setPower(-0.5);
        }
        //la toate metodele

        while(opModeIsActive() &&stangaJos.isBusy()&&dreaptaJos.isBusy()&&stangaSus.isBusy()&&dreaptaSus.isBusy()){
            telemetrie();
            //idle();
        }


        // si aici cum ar veni orpesc encoder-ul, conform unuii sample opresc modul run to position
        stangaSus.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        stangaJos.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dreaptaSus.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dreaptaJos.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        stangaJos.setPower(0);
        stangaSus.setPower(0);
        dreaptaJos.setPower(0);
        dreaptaSus.setPower(0);
    }

    public void InStanga(double rotatii){
        stangaSus.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        stangaJos.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dreaptaSus.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dreaptaJos.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        newTarget1dj = ticksSasiu*rotatii;
        newTarget1ds = -ticksSasiu*rotatii;
        newTarget1sj = ticksSasiu*rotatii;
        newTarget1ss = -ticksSasiu*rotatii;

        stangaJos.setTargetPosition((int)(newTarget1sj));
        dreaptaJos.setTargetPosition((int)(newTarget1dj));
        dreaptaSus.setTargetPosition((int)(newTarget1ds));
        stangaSus.setTargetPosition((int)(newTarget1ss));

        stangaJos.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        stangaSus.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dreaptaJos.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dreaptaSus.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if(rotatii>0) {
            dreaptaSus.setPower(-0.5);
            dreaptaJos.setPower(0.5);
            stangaSus.setPower(-0.5);
            stangaJos.setPower(0.5);
        }
        else{
            dreaptaSus.setPower(-0.5);
            dreaptaJos.setPower(0.5);
            stangaSus.setPower(-0.5);
            stangaJos.setPower(0.5);
        }

        //SETATE PT STRAFE

        while(opModeIsActive() &&stangaJos.isBusy() && stangaSus.isBusy() && dreaptaJos.isBusy() && dreaptaSus.isBusy()){
            telemetrie();
            //idle();
        }

        stangaJos.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        stangaSus.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dreaptaJos.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dreaptaSus.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        stangaJos.setPower(0.0);
        stangaSus.setPower(0.0);
        dreaptaJos.setPower(0.0);
        dreaptaSus.setPower(0.0);

    }

    public void turnaround(double rotatii){
        stangaSus.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        stangaJos.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dreaptaSus.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dreaptaJos.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // aici  e gen ca sa mearga in fata, au tick-uri negative motoarele de stanga parca, I FoRgOr
        newTarget1dj=ticksSasiu*(-rotatii);
        newTarget1ds=ticksSasiu*(-rotatii);
        newTarget1sj=ticksSasiu*(-rotatii);
        newTarget1ss=ticksSasiu*(-rotatii);

        //aici e poszitia la care   trebuie sa ajunga, modul de a ajunge la cea pozitie si puterea cu care sa mearga motorul
        stangaJos.setTargetPosition((int) newTarget1sj);
        stangaJos.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // acum repet pentru celelate patru motoare

        //aa si valori negative pentru a merge in fata... hopefully
        stangaSus.setTargetPosition((int) newTarget1ss);
        stangaSus.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        dreaptaJos.setTargetPosition((int) newTarget1dj);
        dreaptaJos.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        //ma intreb daca ar trbui schimbat acel int din paranteza, sper sa nu tho
        dreaptaSus.setTargetPosition((int)newTarget1ds);
        dreaptaSus.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        if(rotatii>0) {
            dreaptaSus.setPower(-0.5);
            dreaptaJos.setPower(-0.5);
            stangaSus.setPower(0.5);
            stangaJos.setPower(0.5);
        }
        else{
            dreaptaSus.setPower(0.5);
            dreaptaJos.setPower(0.5);
            stangaSus.setPower(-0.5);
            stangaJos.setPower(-0.5);
        }
        //la toate metodele

        while(opModeIsActive() &&stangaJos.isBusy()&&dreaptaJos.isBusy()&&stangaSus.isBusy()&&dreaptaSus.isBusy()){
            telemetrie();
            //idle();
        }


        // si aici cum ar veni orpesc encoder-ul, conform unuii sample opresc modul run to position
        stangaSus.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        stangaJos.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dreaptaSus.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dreaptaJos.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        stangaJos.setPower(0);
        stangaSus.setPower(0);
        dreaptaJos.setPower(0);
        dreaptaSus.setPower(0);
    } // +-1.58

    public void telemetrie(){
        telemetry.addData("stanga jos",stangaJos.getCurrentPosition());
        telemetry.addData("stanga Sus",stangaSus.getCurrentPosition());
        telemetry.addData("dreapta jos",dreaptaJos.getCurrentPosition());
        telemetry.addData("dreapta sus",dreaptaSus.getCurrentPosition());
        telemetry.update();
    }
}