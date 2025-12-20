package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Drivetrain;


@TeleOp(name="Code For Driving", group="Linear OpMode")
public class DrivingCode extends LinearOpMode {

    SparkFunOTOS myOtos;

    @Override
    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        // Declare OpMode members for each of the 4 motors.
        DcMotor frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        DcMotor backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        DcMotor frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        DcMotor backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        myOtos = hardwareMap.get(SparkFunOTOS.class, "sensor_otos");

        Drivetrain driverTrain = new Drivetrain(
                frontLeftDrive,
                frontRightDrive,
                backLeftDrive,
                backRightDrive,
                myOtos
        );

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        boolean mode = false;

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if (gamepad1.aWasPressed()) {
                mode = !mode;
            }
            driverTrain.drive(gamepad1, mode);
            telemetry.addData("Status", "Started");
            telemetry.addData("Field Centric", mode);
            telemetry.update();
        }
    }
}
