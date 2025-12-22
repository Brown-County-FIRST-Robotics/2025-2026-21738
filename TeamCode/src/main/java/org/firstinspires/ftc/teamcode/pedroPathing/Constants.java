package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.OTOSConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
             .mass(7.8)
            .forwardZeroPowerAcceleration(-33.8723)
            .lateralZeroPowerAcceleration(-33.2565)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.025, 0.00003, 0.0004, 0.03))
            .headingPIDFCoefficients(new PIDFCoefficients(0.9, 0.00001, 0.0002, 0.03))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.008, 0.0, 0.00001,0.6,0.01));

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("frontRightDrive")
            .rightRearMotorName("backRightDrive")
            .leftRearMotorName("backLeftDrive")
            .leftFrontMotorName("frontLeftDrive")
            .xVelocity(52.1544)
            .yVelocity(47.8435)
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);

    public static OTOSConstants localizerConstants = new OTOSConstants()
            .hardwareMapName("otos")
            .linearScalar(0.995)
            .angularScalar(1)
            .linearUnit(DistanceUnit.INCH)
            .angleUnit(AngleUnit.RADIANS);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .OTOSLocalizer(localizerConstants)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}
