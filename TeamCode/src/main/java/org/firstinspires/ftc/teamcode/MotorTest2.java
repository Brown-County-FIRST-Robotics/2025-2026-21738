package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp(name="Motor Testing Code 2", group="Linear OpMode")
public class MotorTest2 extends LinearOpMode {
    private double speed1 = 0.0;
    private double speed2 = 0.0;
    private double speed3 = 0.0;
    private double speed4 = 0.0;
    private int motor_setting = 1;
    private String m1Direction = "forward";
    private String m2Direction = "forward";
    private String m3Direction = "forward";
    private String m4Direction = "forward";
    @Override
    public void runOpMode() {
        DcMotorEx motor1 = hardwareMap.get(DcMotorEx.class, "motor1");
        DcMotorEx motor2 = hardwareMap.get(DcMotorEx.class, "motor2");
        DcMotorEx motor3 = hardwareMap.get(DcMotorEx.class, "motor3");
        DcMotorEx motor4 = hardwareMap.get(DcMotorEx.class, "motor4");

        motor1.setDirection(DcMotorEx.Direction.FORWARD);
        motor2.setDirection(DcMotorEx.Direction.FORWARD);
        motor3.setDirection(DcMotorEx.Direction.FORWARD);
        motor4.setDirection(DcMotorEx.Direction.FORWARD);

        motor1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor3.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor4.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        telemetry.addLine("press the top buttons to select a motor");
        telemetry.addLine("press y to make the motor speed change 10% up");
        telemetry.addLine("press a to make the motor speed change 10% down");
        telemetry.addLine("press up to make the motor speed change 1% up");
        telemetry.addLine("press down to make the motor speed change 1% down");
        telemetry.addLine();
        telemetry.addLine("press x to make the motor go forward");
        telemetry.addLine("press b to make the motor go backwards");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.rightBumperWasPressed() && motor_setting < 4) {
                motor_setting += 1;
            }
            if (gamepad1.leftBumperWasPressed() && motor_setting > 1) {
                motor_setting -= 1;
            }


            motor1.setPower(speed1);
            motor2.setPower(speed2);
            motor3.setPower(speed3);
            motor4.setPower(speed4);


            if (gamepad1.yWasPressed()) {
                if (motor_setting == 1) {
                    if (speed1 <= 0.99) {
                        speed1 += 0.1;
                    }
                }if (motor_setting == 2) {
                    if (speed2 <= 0.99) {
                        speed2 += 0.1;
                    }
                }if (motor_setting == 3) {
                    if (speed3 <= 0.99) {
                        speed3 += 0.1;
                    }
                }else {
                    if (speed4 <= 0.9) {
                        speed4 += 0.1;
                    }
                }
            }
            if (gamepad1.aWasPressed()) {
                if (motor_setting == 1) {
                    if (speed1 >= 0.1) {
                        speed1 -= 0.1;
                    }
                }
                if (motor_setting == 2) {
                    if (speed2 >= 0.1) {
                        speed2 -= 0.1;
                    }
                }
                if (motor_setting == 3) {
                    if (speed3 >= 0.1) {
                        speed3 -= 0.1;
                    }
                } else {
                    if (speed4 >= 0.1) {
                        speed4 -= 0.1;
                    }
                }
            }

            if (gamepad1.dpadUpWasPressed()) {
                if (motor_setting == 1) {
                    if (speed1 < 1.00) {
                        speed1 += 0.01;
                    }
                }
                if (motor_setting == 2) {
                    if (speed2 < 1.00) {
                        speed2 += 0.01;
                    }
                }
                if (motor_setting == 3) {
                    if (speed3 <= 1.00) {
                        speed3 += 0.01;
                    }
                } else {
                    if (speed4 < 1.00) {
                        speed4 += 0.01;
                    }
                }
            }

            if (gamepad1.dpadDownWasPressed()) {
                if (motor_setting == 1) {
                    if (speed1 > 0.00) {
                        speed1 -= 0.01;
                    }
                }
                if (motor_setting == 2) {
                    if (speed2 > 0.00) {
                        speed2 -= 0.01;
                    }
                }
                if (motor_setting == 3) {
                    if (speed3 > 0.00) {
                        speed3 -= 0.01;
                    }
                } else {
                    if (speed4 > 0.00) {
                        speed4 -= 0.01;
                    }
                }
            }


            if (gamepad1.bWasPressed()) {
                if (motor_setting == 1) {
                    motor1.setDirection(DcMotor.Direction.FORWARD);
                    m1Direction = "backward";
                }if (motor_setting == 2) {
                    motor2.setDirection(DcMotor.Direction.FORWARD);
                    m2Direction = "backward";
                }if (motor_setting == 3) {
                    motor3.setDirection(DcMotor.Direction.FORWARD);
                    m3Direction = "backward";
                }else {
                    motor4.setDirection(DcMotor.Direction.FORWARD);
                    m4Direction = "backward";
                }
            }
            if (gamepad1.xWasPressed()) {
                if (motor_setting == 1) {
                    motor1.setDirection(DcMotor.Direction.REVERSE);
                    m1Direction = "forward";
                }if (motor_setting == 2) {
                    motor2.setDirection(DcMotor.Direction.REVERSE);
                    m2Direction = "forward";
                }if (motor_setting == 3) {
                    motor3.setDirection(DcMotor.Direction.REVERSE);
                    m3Direction = "forward";
                } else {
                    motor4.setDirection(DcMotor.Direction.REVERSE);
                    m4Direction = "forward";
                }
            }


            if (motor_setting == 1) {
                telemetry.addData("setting", "M1");
                telemetry.addData("port", "0");
                telemetry.addData("speed", Math.round(speed1 * 100) + "%");
                telemetry.addData("direction", m1Direction);
            } else if (motor_setting == 2) {
                telemetry.addData("setting", "M2");
                telemetry.addData("port", "1");
                telemetry.addData("speed", Math.round(speed2 * 100) + "%");
                telemetry.addData("direction", m2Direction);
            }else if (motor_setting == 3) {
                telemetry.addData("setting", "M3");
                telemetry.addData("port", "2");
                telemetry.addData("speed", Math.round(speed3 * 100) + "%");
                telemetry.addData("direction", m3Direction);
            } else {
                telemetry.addData("setting", "M4");
                telemetry.addData("port", "3");
                telemetry.addData("speed", Math.round(speed4 * 100) + "%");
                telemetry.addData("direction", m4Direction);
            }
            telemetry.update();
        }
    }
}
