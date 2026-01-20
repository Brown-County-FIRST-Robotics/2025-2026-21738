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

@Autonomous(name="Shoot Drive 50%", group = "Autonomous")
public class AutoDriveShoot extends CommandOpMode {
    DrivebaseSubsystem d;
    @Override
    public void initialize() {
        GamepadEx gamepadEx = new GamepadEx(gamepad1);
        DrivebaseSubsystem d = new DrivebaseSubsystem(gamepadEx, hardwareMap);
        ShooterSubsystem s = new ShooterSubsystem(gamepadEx, hardwareMap, telemetry);
        d.teleop = false;
        d.frontLeft.setPower(0);
        d.frontRight.setPower(0);
        d.backLeft.setPower(0);
        d.backRight.setPower(0);
        s.flap.setPosition(250.0/300);
        s.shooterSetSpeed = 1650;
        waitForStart();
        SequentialCommandGroup path = new SequentialCommandGroup(
                new launchCommand(s),
                new launchCommand(s),
                new launchCommand(s),
                new shake(d),
                new launchCommand(s),
                new shake(d),
                new launchCommand(s),
                new ShootPowerOffCommand(s, telemetry),
                new driveCommand(d, 0.5)
        );
        path.schedule();
    }
}