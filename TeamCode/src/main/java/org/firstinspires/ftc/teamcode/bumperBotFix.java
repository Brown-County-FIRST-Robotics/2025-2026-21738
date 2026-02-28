package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp(name="bumper bot fix")
public class bumperBotFix extends LinearOpMode {
    @Override
    public void runOpMode() {

        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontRightDrive");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRightDrive");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeftDrive");

        waitForStart();
        while (opModeIsActive()) {
            boolean FL = gamepad1.y;
            boolean FR = gamepad1.b;
            boolean BL = gamepad1.x;
            boolean BR = gamepad1.a;

            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            if (FL)
                frontLeft.setPower(1);
            if (FR)
                frontRight.setPower(1);
            if (BL)
                backLeft.setPower(1);
            if (BR)
                backRight.setPower(1);
        }
    }
}
