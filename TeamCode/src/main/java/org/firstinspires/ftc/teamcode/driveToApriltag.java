package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class driveToApriltag {
    final AprilTagProcessor aprilTag;
    final DcMotorEx frontLeft;
    final DcMotorEx frontRight;
    final DcMotorEx backRight;
    final DcMotorEx backLeft;

    double DESIRED_DISTANCE = 25.0; //  this is how close the camera should get to the target (inches)

    private static final boolean USE_WEBCAM = true;  // Set true to use a webcam, or false for a phone camera

    final private double minPower = 0.1; // was 0.1
    final private double lowPower = 0.025; // was 0.025

    public int target;

    final double SPEED_GAIN = 0.02;   //  Forward Speed Control "Gain". e.g. Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double STRAFE_GAIN = 0.01;   // was 0.015//  Strafe Speed Control "Gain".  e.g. Ramp up to 37% power at a 25 degree Yaw error.   (0.375 / 25.0)
    final double TURN_GAIN = 0.01;   //  Turn Control "Gain".  e.g. Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MAX_AUTO_SPEED = 0.75;   //  i changed from .5 //Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_STRAFE = 0.5;   //  Clip the strafing speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN = 0.3;   // i changed from .3 // Clip the turn speed to this max value (adjust for your robot)
    double drive = 0;
    double turn = 0;
    double strafe = 0;
    private AprilTagDetection desiredTag = null;

    public driveToApriltag(int target, AprilTagProcessor aprilTag, DcMotorEx frontLeft, DcMotorEx frontRight, DcMotorEx backLeft, DcMotorEx backRight) {
        this.aprilTag = aprilTag;
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;
        this.target = target;
        this.desiredTag = null;
    }

    public boolean isAprilTag() {
        List<AprilTagDetection> currentDetections = this.aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null && detection.id == target) {
                this.desiredTag = detection;
                return true;
            }
        }
        return false;
    }

    public boolean isThere() {
        if (this.isAprilTag()) {
            // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
            double rangeError = (desiredTag.ftcPose.range - DESIRED_DISTANCE);
            double headingError = desiredTag.ftcPose.bearing;
            double yawError = desiredTag.ftcPose.yaw;

            if (Math.abs(rangeError) < 1 && Math.abs(yawError) < 1 && Math.abs(headingError) < 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void autoDrive() {
        // work on later
        if (this.isAprilTag()) {
            // Determine heading, range and Yaw (tag image rotation) error so we can use them to control the robot automatically.
            double rangeError = (desiredTag.ftcPose.range - DESIRED_DISTANCE);
            double headingError = desiredTag.ftcPose.bearing;
            double yawError = desiredTag.ftcPose.yaw;

            // Use the speed and turn "gains" to calculate how we want the robot to move.
            this.drive = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
            this.turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
            this.strafe = Range.clip(-yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
            this.moveRobot(drive, strafe, turn);
        }
        this.moveRobot(0, 0, 0);
    }

    private void moveRobot(double x, double y, double yaw) {
        // Calculate wheel powers.
        double frontLeftPower = x - y - yaw;
        double frontRightPower = x + y + yaw;
        double backLeftPower = x + y - yaw;
        double backRightPower = x - y + yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower /= max;
            frontRightPower /= max;
            backLeftPower /= max;
            backRightPower /= max;
        }

        // set min power
        if (Math.abs(frontLeftPower) < minPower && Math.abs(frontLeftPower) > lowPower) {
            if (frontLeftPower > 0) {
                frontLeftPower = minPower;
            } else {
                frontLeftPower = minPower * -1;
            }
        }
        if (Math.abs(frontRightPower) < minPower && Math.abs(frontRightPower) > lowPower) {
            if (frontRightPower > 0) {
                frontRightPower = minPower;
            } else {
                frontRightPower = minPower * -1;
            }
        }
        if (Math.abs(backLeftPower) < minPower && Math.abs(backLeftPower) > lowPower) {
            if (backLeftPower > 0) {
                backLeftPower = minPower;
            } else {
                backLeftPower = minPower * -1;
            }
        }
        if (Math.abs(backRightPower) < minPower && Math.abs(backRightPower) > lowPower) {
            if (backRightPower > 0) {
                backRightPower = minPower;
            } else {
                backRightPower = minPower * -1;
            }
        }

        // Send powers to the wheels V1
        // frontLeft.setPower(frontLeftPower);
        // frontRight.setPower(frontRightPower);
        // backLeft.setPower(backLeftPower);
        // backRight.setPower(backRightPower);

        // Send powers to the wheels V2
        frontLeft.setVelocity(frontLeftPower);
        frontRight.setVelocity(frontRightPower);
        backLeft.setVelocity(backLeftPower);
        backRight.setVelocity(backRightPower);


    }
}
