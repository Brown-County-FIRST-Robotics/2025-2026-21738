package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name="OpMode for testing", group="Linear OpMode")
public class motorTest extends LinearOpMode {
    private DcMotorEx motor1 = null;
    private DcMotorEx motor2 = null;
    private double speed1 = 0.0;
    private double speed2 = 0.0;
    private int motor_setting = 1;
    private String m1Direction = "forward";
    private String m2Direction = "forward";

    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotorEx.class, "motor1");
        motor2 = hardwareMap.get(DcMotorEx.class, "motor2");

        motor1.setDirection(DcMotorEx.Direction.FORWARD);
        motor2.setDirection(DcMotorEx.Direction.FORWARD);

        motor1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);


        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.right_bumper) {
                motor_setting = 1;
            }
            if (gamepad1.left_bumper) {
                motor_setting = 2;
            }


            motor1.setPower(speed1);
            motor2.setPower(speed2);

            if (gamepad1.yWasPressed()) {
                if (motor_setting == 1) {
                    if (speed1 < 0.9) {
                        speed1 += 0.1;
                    }
                }else {
                    if (speed2 < 0.9) {
                        speed2 += 0.1;
                    }
                }
            }
            if (gamepad1.aWasPressed()) {
                if (motor_setting == 1) {
                    if (speed1 >= 0.1) {
                        speed1 -= 0.1;
                    }
                }else {
                    if (speed2 >= 0.1) {
                        speed2 -= 0.1;
                    }
                }
            }


            if (gamepad1.dpadUpWasPressed()) {
                if (motor_setting == 1) {
                    if (speed1 < 0.99) {
                        speed1 += 0.01;
                    }
                }else {
                    if (speed2 < 0.99) {
                        speed2 += 0.01;
                    }
                }
            }
            if (gamepad1.dpadDownWasPressed()) {
                if (motor_setting == 1) {
                    if (speed1 > 0.00) {
                        speed1 -= 0.01;
                    }
                }else {
                    if (speed2 > 0.00) {
                        speed2 -= 0.01;
                    }
                }
            }

            if (gamepad1.bWasPressed()) {
                if (motor_setting == 1) {
                    motor1.setDirection(DcMotor.Direction.FORWARD);
                    m1Direction = "backward";
                }else {
                    motor2.setDirection(DcMotor.Direction.FORWARD);
                    m2Direction = "backward";
                }
            }
            if (gamepad1.xWasPressed()) {
                if (motor_setting == 1) {
                    motor1.setDirection(DcMotor.Direction.REVERSE);
                    m1Direction = "forward";
                } else {
                    motor2.setDirection(DcMotor.Direction.REVERSE);
                    m2Direction = "forward";
                }
            }


            telemetry.addData("setting", "M" + motor_setting);
            telemetry.addData("speed M1", Math.round(speed1 * 100) + "%");
            telemetry.addData("direction M1", m1Direction);
            telemetry.addData("Velocity RPM M1", Math.round((motor1.getVelocity() * 60) / 28));
            telemetry.addData("Velocity M1", motor1.getVelocity());
            telemetry.addData("speed M2", Math.round(speed2 * 100) + "%");
            telemetry.addData("direction M2", m2Direction);
            telemetry.addData("Velocity RPM M2", Math.round((motor2.getVelocity() * 60) / 28));
            telemetry.addData("Velocity M2", motor2.getVelocity());
            telemetry.update();
        }
    }
}
