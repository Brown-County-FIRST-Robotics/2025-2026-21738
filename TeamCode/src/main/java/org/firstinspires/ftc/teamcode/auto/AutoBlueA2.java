package org.firstinspires.ftc.teamcode.auto;
//  ./gradlew reloadFastLoad run this command

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
import org.firstinspires.ftc.teamcode.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.auto.gotoCommand;
import org.firstinspires.ftc.teamcode.intakeCommand;
import org.firstinspires.ftc.teamcode.launchCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.shake;


@Autonomous(name="Blue A2 1/20/2026", group="Autonomous")
public class AutoBlueA2 extends CommandOpMode {
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
        follower.setStartingPose(org.firstinspires.ftc.teamcode.auto.gotoCommand.scale(new Pose(57, 9, Math.toRadians(90)), red));
        follower.update();
        s.flap.setPosition(250.0/300);
        s.shooterSetSpeed = 1650;
        s.teleop = false;
        d.teleop = false;
        // real path here
        waitForStart();
        SequentialCommandGroup path = new SequentialCommandGroup(
                new org.firstinspires.ftc.teamcode.auto.gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 59.5, 14, AngleUnit.DEGREES, 115), 1, red),
                new launchCommand(s),
                new launchCommand(s),
                new shake(d),
                new launchCommand(s),
                new launchCommand(s),
                new ShootPowerOffCommand(s, telemetry),
                new org.firstinspires.ftc.teamcode.auto.gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 42, AngleUnit.DEGREES, 180), 1, red),
                new intakeCommand(s, -1),
                new org.firstinspires.ftc.teamcode.auto.gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 23, 42, AngleUnit.DEGREES, 180), 0.2, red),
                new org.firstinspires.ftc.teamcode.auto.gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 59.5, 14, AngleUnit.DEGREES, 115), 1, red),
                new intakeCommand(s, 0),
                new launchCommand(s),
                new launchCommand(s),
                new shake(d),
                new launchCommand(s),
                new launchCommand(s),
                new ShootPowerOffCommand(s, telemetry),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 37, 15, AngleUnit.DEGREES, 115), 1, red)
        );
        path.schedule();
    }
}