package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class motorglisiere extends OpMode {
    DcMotor motorglisiere;
    double newTarget=0;
    double ticks=537.7;
   double LIMITA_GLISIERE1=0.1;
   double zeroPower=0;

    @Override
    public void init() {
        motorglisiere=hardwareMap.get(DcMotor.class , "MotorGlisiere");
        motorglisiere.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop() {
        double inSus=gamepad2.right_trigger;
        double inJos=gamepad2.left_trigger;
        if(inSus>LIMITA_GLISIERE1){
            motorglisiere.setPower(inSus-0.2);
        }
    if (inJos>LIMITA_GLISIERE1){
        motorglisiere.setPower(inJos-0.2);
    }
    else motorglisiere.setPower(zeroPower);
    }
   //

}
