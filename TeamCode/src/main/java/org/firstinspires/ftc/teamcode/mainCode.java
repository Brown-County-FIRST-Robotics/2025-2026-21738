package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="bumperbot WORKING CODE!")
public class mainCode extends LinearOpMode {
    @Override
    public void runOpMode() {

        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontRightDrive");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRightDrive");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeftDrive");

        waitForStart();
        while (opModeIsActive()) {
            double rotate = gamepad1.right_stick_x;
            double forward = -gamepad1.left_stick_y;
            double shifting = gamepad1.left_stick_x;

            double frontLeftPower = forward + shifting - rotate;
            double frontRightPower = forward - shifting - rotate;
            double backLeftPower = forward - shifting + rotate;
            double backRightPower = forward + shifting + rotate;

            double max;
            max = Math.max(frontLeftPower, frontRightPower);
            max = Math.max(max, backLeftPower);
            max = Math.max(max, backRightPower);

            if (max > 1) {
                frontLeftPower = frontLeftPower / max;
                frontRightPower = frontRightPower / max;
                backLeftPower = backLeftPower / max;
                backRightPower = backRightPower / max;
            }

            frontLeft.setPower(-frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(-backLeftPower);
            backRight.setPower(backRightPower);
        }
    }
}
