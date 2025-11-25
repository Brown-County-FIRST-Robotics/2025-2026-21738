package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class Location {
    GoBildaPinpointDriver pinpoint;
    SparkFunOTOS optical;
    CameraCode apriltag;
    Telemetry display;
    private Pose2D estimate;
    public Location(AprilTagProcessor camera, SparkFunOTOS optical, Telemetry display) {//, GoBildaPinpointDriver pinpoint) {
        this.pinpoint = pinpoint;
        this.optical = optical;
        apriltag = new CameraCode(camera);
        this.display = display;

        configureOptical();
        // configurePinpoint();
    }

    public Pose2D getLocation() {
        return estimate;
    }

    public void update() {
        estimate = getOptical();

        List<AprilTagDetection> detections = apriltag.getCamera();
        if (detections != null) {
            for (AprilTagDetection detection : detections) {
                if (detection.metadata != null) {
                    // Only use tags that don't have Obelisk in them
                    if (!detection.metadata.name.contains("Obelisk")) {
                        long t = detection.frameAcquisitionNanoTime;
                        int id = detection.id;
                        long system = System.nanoTime();
                        display.addLine("tag: " + id + " time: " + t / 1000000000.0 + " tag delay: " + (system - t));
                        double x = detection.robotPose.getPosition().x;
                        double y = detection.robotPose.getPosition().y;
                        double h = detection.robotPose.getOrientation().getYaw(AngleUnit.DEGREES);
                        // detection.decisionMargin;

                        // update
                        double betterX = (estimate.getX(DistanceUnit.INCH) * 1 + x * 1) / 2;
                        double betterY = (estimate.getY(DistanceUnit.INCH) * 1 + y * 1) / 2;
                        double betterH = (estimate.getHeading(AngleUnit.DEGREES) * 1 + h * 1) / 2;
                        estimate = new Pose2D(DistanceUnit.INCH, betterX, betterY, AngleUnit.DEGREES, betterH);
                        SetPoseOptical(estimate);
                    }
                }
            }
        }
    }



    private void SetPoseOptical(Pose2D setTo) {
        optical.resetTracking();
        SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(setTo.getX(DistanceUnit.INCH), setTo.getY(DistanceUnit.INCH), setTo.getHeading(AngleUnit.DEGREES));
        optical.setPosition(currentPosition);
    }
    private Pose2D getOptical() {
        SparkFunOTOS.Pose2D pose = optical.getPosition();
        return new Pose2D(DistanceUnit.INCH, pose.x, pose.y, AngleUnit.DEGREES, pose.h);
    }

    private void configureOptical() {
        // Set the desired units for linear and angular measurements. Can be either
        // meters or inches for linear, and radians or degrees for angular. If not
        // set, the default is inches and degrees. Note that this setting is not
        // persisted in the sensor, so you need to set at the start of all your
        // OpModes if using the non-default value.
        // myOtos.setLinearUnit(DistanceUnit.METER);
        optical.setLinearUnit(DistanceUnit.INCH);
        // myOtos.setAngularUnit(AngleUnit.RADIANS);
        optical.setAngularUnit(AngleUnit.DEGREES);

        // Assuming you've mounted your sensor to a robot and it's not centered,
        // you can specify the offset for the sensor relative to the center of the
        // robot. The units default to inches and degrees, but if you want to use
        // different units, specify them before setting the offset! Note that as of
        // firmware version 1.0, these values will be lost after a power cycle, so
        // you will need to set them each time you power up the sensor. For example, if
        // the sensor is mounted 5 inches to the left (negative X) and 10 inches
        // forward (positive Y) of the center of the robot, and mounted 90 degrees
        // clockwise (negative rotation) from the robot's orientation, the offset
        // would be {-5, 10, -90}. These can be any value, even the angle can be
        // tweaked slightly to compensate for imperfect mounting (eg. 1.3 degrees).
        SparkFunOTOS.Pose2D offset = new SparkFunOTOS.Pose2D(0, 0, 0);
        optical.setOffset(offset);

        // Here we can set the linear and angular scalars, which can compensate for
        // scaling issues with the sensor measurements. Note that as of firmware
        // version 1.0, these values will be lost after a power cycle, so you will
        // need to set them each time you power up the sensor. They can be any value
        // from 0.872 to 1.127 in increments of 0.001 (0.1%). It is recommended to
        // first set both scalars to 1.0, then calibrate the angular scalar, then
        // the linear scalar. To calibrate the angular scalar, spin the robot by
        // multiple rotations (eg. 10) to get a precise error, then set the scalar
        // to the inverse of the error. Remember that the angle wraps from -180 to
        // 180 degrees, so for example, if after 10 rotations counterclockwise
        // (positive rotation), the sensor reports -15 degrees, the required scalar
        // would be 3600/3585 = 1.004. To calibrate the linear scalar, move the
        // robot a known distance and measure the error; do this multiple times at
        // multiple speeds to get an average, then set the linear scalar to the
        // inverse of the error. For example, if you move the robot 100 inches and
        // the sensor reports 103 inches, set the linear scalar to 100/103 = 0.971
        optical.setLinearScalar(1.0);
        optical.setAngularScalar(1.0);

        // The IMU on the OTOS includes a gyroscope and accelerometer, which could
        // have an offset. Note that as of firmware version 1.0, the calibration
        // will be lost after a power cycle; the OTOS performs a quick calibration
        // when it powers up, but it is recommended to perform a more thorough
        // calibration at the start of all your OpModes. Note that the sensor must
        // be completely stationary and flat during calibration! When calling
        // calibrateImu(), you can specify the number of samples to take and whether
        // to wait until the calibration is complete. If no parameters are provided,
        // it will take 255 samples and wait until done; each sample takes about
        // 2.4ms, so about 612ms total
        optical.calibrateImu();

        // Reset the tracking algorithm - this resets the position to the origin,
        // but can also be used to recover from some rare tracking errors
        optical.resetTracking();

        // After resetting the tracking, the OTOS will report that the robot is at
        // the origin. If your robot does not start at the origin, or you have
        // another source of location information (eg. vision odometry), you can set
        // the OTOS location to match and it will continue to track from there.
        SparkFunOTOS.Pose2D currentPosition = new SparkFunOTOS.Pose2D(0, 0, 0);
        optical.setPosition(currentPosition);
    }



    private void SetPosePinpoint(Pose2D setTo) {
        pinpoint.setPosition(setTo);
    }
    private Pose2D getPinpoint() {
        pinpoint.update();
        return pinpoint.getPosition();
    }
    private void configurePinpoint(){
        /*
         *  Set the odometry pod positions relative to the point that you want the position to be measured from.
         *
         *  The X pod offset refers to how far sideways from the tracking point the X (forward) odometry pod is.
         *  Left of the center is a positive number, right of center is a negative number.
         *
         *  The Y pod offset refers to how far forwards from the tracking point the Y (strafe) odometry pod is.
         *  Forward of center is a positive number, backwards is a negative number.
         */
        pinpoint.setOffsets(-175, -50, DistanceUnit.MM); // needs fixed with real values

        /*
         * Set the kind of pods used by your robot. If you're using goBILDA odometry pods, select either
         * the goBILDA_SWINGARM_POD, or the goBILDA_4_BAR_POD.
         * If you're using another kind of odometry pod, uncomment setEncoderResolution and input the
         * number of ticks per unit of your odometry pod.  For example:
         *     pinpoint.setEncoderResolution(13.26291192, DistanceUnit.MM);
         */
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);

        /*
         * Set the direction that each of the two odometry pods count. The X (forward) pod should
         * increase when you move the robot forward. And the Y (strafe) pod should increase when
         * you move the robot to the left.
         */
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);

        /*
         * Before running the robot, recalibrate the IMU. This needs to happen when the robot is stationary
         * The IMU will automatically calibrate when first powered on, but recalibrating before running
         * the robot is a good idea to ensure that the calibration is "good".
         * resetPosAndIMU will reset the position to 0,0,0 and also recalibrate the IMU.
         * This is recommended before you run your autonomous, as a bad initial calibration can cause
         * an incorrect starting value for x, y, and heading.
         */
        pinpoint.resetPosAndIMU();
    }

    private class CameraCode {
        public AprilTagProcessor camera;

        public CameraCode(AprilTagProcessor camera) {
            this.camera = camera;
        }
        public List<AprilTagDetection> getCamera() {
            List<AprilTagDetection> currentDetections = camera.getDetections(); // changed from .getFreshDetections()
            return currentDetections;
        }
    }
}
