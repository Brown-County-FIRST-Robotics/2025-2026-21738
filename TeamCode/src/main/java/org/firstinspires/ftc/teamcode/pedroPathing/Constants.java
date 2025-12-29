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
            .translationalPIDFCoefficients(new PIDFCoefficients(0.045, 0.00003, 0.0005, 0.03))
            .headingPIDFCoefficients(new PIDFCoefficients(0.85, 0.00001, 0.0003, 0.03))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.008, 0.0, 0.00001,0.6,0.01));

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("frontRight")
            .rightRearMotorName("backRight")
            .leftRearMotorName("backLeft")
            .leftFrontMotorName("frontLeft")
            .xVelocity(52.1544)
            .yVelocity(47.8435)
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);

    public static OTOSConstants localizerConstants = new OTOSConstants()
            .hardwareMapName("sensor_otos")
            .linearScalar(1.072)
            .angularScalar(0.992)
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




// old config for main robot
//package org.firstinspires.ftc.teamcode.pedroPathing;
//
//import com.pedropathing.control.FilteredPIDFCoefficients;
//import com.pedropathing.control.PIDFCoefficients;
//import com.pedropathing.follower.Follower;
//import com.pedropathing.follower.FollowerConstants;
//import com.pedropathing.ftc.FollowerBuilder;
//import com.pedropathing.ftc.drivetrains.MecanumConstants;
//import com.pedropathing.ftc.localization.constants.OTOSConstants;
//import com.pedropathing.paths.PathConstraints;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//
//
//public class Constants {
//    public static FollowerConstants followerConstants = new FollowerConstants()
//            .forwardZeroPowerAcceleration(-29.4668)
//            .lateralZeroPowerAcceleration(-55.1623)
//            .translationalPIDFCoefficients(new PIDFCoefficients(0.15, 0.0001, 0.02, 0.03))
//            .headingPIDFCoefficients(new PIDFCoefficients(1.5, 0.01, 0.001, 0.02))
//            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.001, 0.0, 0.0, 0.06, .2))
//            .mass(12.5);
//
//    public static MecanumConstants driveConstants = new MecanumConstants()
//            .maxPower(1)
//            .rightFrontMotorName("frontRight")
//            .rightRearMotorName("backRight")
//            .leftRearMotorName("backLeft")
//            .leftFrontMotorName("frontLeft")
//            .xVelocity(50.7084)
//            .yVelocity(40.3685)
//            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
//            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
//            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
//            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);
//
//    public static OTOSConstants localizerConstants = new OTOSConstants()
//            .hardwareMapName("sensor_otos")
//            .linearScalar(1.072)
//            .angularScalar(0.992)
//            .linearUnit(DistanceUnit.INCH)
//            .angleUnit(AngleUnit.RADIANS);
//
//    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);
//
//    public static Follower createFollower(HardwareMap hardwareMap) {
//        return new FollowerBuilder(followerConstants, hardwareMap)
//                .pathConstraints(pathConstraints)
//                .OTOSLocalizer(localizerConstants)
//                .mecanumDrivetrain(driveConstants)
//                .build();
//    }
//}
//
