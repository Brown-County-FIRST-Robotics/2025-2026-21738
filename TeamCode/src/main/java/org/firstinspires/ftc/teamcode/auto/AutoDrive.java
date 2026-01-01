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

@Autonomous(name="Drive 50%", group = "Autonomous")
public class AutoDrive extends CommandOpMode {
    DrivebaseSubsystem d;
    @Override
    public void initialize() {
        GamepadEx gamepadEx = new GamepadEx(gamepad1);
        DrivebaseSubsystem d = new DrivebaseSubsystem(gamepadEx, hardwareMap);
        d.teleop = false;
        d.frontLeft.setPower(0);
        d.frontRight.setPower(0);
        d.backLeft.setPower(0);
        d.backRight.setPower(0);
        waitForStart();
        d.frontLeft.setPower(0.5);
        d.frontRight.setPower(0.5);
        d.backLeft.setPower(0.5);
        d.backRight.setPower(0.5);
    }
}