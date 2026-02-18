package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.controller.PIDController;

@Disabled
@TeleOp(name="Limelight Targeting artifacts")
public class limelightTargetingArtifacts extends LinearOpMode {
    private Limelight3A limelight;
    private boolean complete;
    private Servo servo;
    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(1);

        DcMotorEx frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        DcMotorEx frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        DcMotorEx backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        DcMotorEx backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        servo = hardwareMap.get(Servo.class, "limeservo");
        servo.setPosition(0/300);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        PIDController pid = new PIDController(0.045, 0, 0.10);
        pid.setSetPoint(0);
        pid.setTolerance(2.5);
        limelight.start();
        waitForStart();
        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();
            double right = 0;
            if (result != null) {
                if (result.isValid()) {
                    telemetry.addData("tx", result.getTx());
                    right = pid.calculate(result.getTx());
                    telemetry.update();
                }else {
                    telemetry.addLine("No Target.");
                }
            }else {
                telemetry.addLine("No Target.");
            }
            if (pid.atSetPoint() || complete) {
                telemetry.addLine("Complete.");
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
                complete = true;
            }
            if (!complete) {
                double frontLeftPower = -right;
                double frontRightPower = right;
                double backLeftPower = right;
                double backRightPower = -right;

                double max;
                max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
                max = Math.max(max, Math.abs(backLeftPower));
                max = Math.max(max, Math.abs(backRightPower));

                if (max > 1.0) {
                    frontLeftPower /= max;
                    frontRightPower /= max;
                    backLeftPower /= max;
                    backRightPower /= max;
                }

                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(backRightPower);
            }
            telemetry.update();
        }
        limelight.stop();
    }
}