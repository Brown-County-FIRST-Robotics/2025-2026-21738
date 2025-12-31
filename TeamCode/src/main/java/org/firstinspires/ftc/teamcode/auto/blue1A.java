package org.firstinspires.ftc.teamcode.auto;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.ShootPowerOffCommand;
import org.firstinspires.ftc.teamcode.ShootPowerUpCommand;
import org.firstinspires.ftc.teamcode.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.intakeCommand;
import org.firstinspires.ftc.teamcode.launchCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.shake;


@Autonomous(name="1A Blue, D")
public class blue1A extends CommandOpMode {
    Follower follower;
    boolean red = false;
    @Override
    public void initialize() {
        follower = Constants.createFollower(hardwareMap);
        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);
        GamepadEx gamepadEx = new GamepadEx(gamepad1);
        ShooterSubsystem s = new ShooterSubsystem(gamepadEx2, hardwareMap);
        DrivebaseSubsystem d = new DrivebaseSubsystem(gamepadEx, hardwareMap);
        d.frontLeft.setPower(0);
        d.frontRight.setPower(0);
        d.backLeft.setPower(0);
        d.backRight.setPower(0);
        follower.update();
        follower.setStartingPose(new Pose(27.6, 130.3, Math.toRadians(144))); //old h = 216
        follower.update();
        s.flap.setPosition(0);
        s.shooterSetSpeed = 1200;
        s.teleop = false;
        d.teleop = false;
        // real path here
        waitForStart();
        SequentialCommandGroup path = new SequentialCommandGroup(
                new launchCommand(s),
                new shake(d),
                new launchCommand(s),
                new shake(d),
                new launchCommand(s),
                new shake(d),
                new launchCommand(s),
                new ShootPowerOffCommand(s, telemetry),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 106, AngleUnit.DEGREES, -135), 1, red),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 45.6, AngleUnit.DEGREES, 180), 1, red), // slow down for better accuracy
                new intakeCommand(s, -1),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 19, 45.6, AngleUnit.DEGREES, 180), 0.35, red),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 45.6, AngleUnit.DEGREES, 135), 1, red),    // 45 inches is wrong it should be some thing else like 35 but 45 works
                new intakeCommand(s, 0),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 50.5, 120, AngleUnit.DEGREES, 160), 1, red),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 31.6, 128.3, AngleUnit.DEGREES, 147), 1, red),
                new shake(d),
                new launchCommand(s),
                new shake(d),
                new launchCommand(s),
                new shake(d),
                new launchCommand(s),
                new shake(d),
                new launchCommand(s),
                new ShootPowerOffCommand(s, telemetry)
        );
        path.schedule();
    }
}