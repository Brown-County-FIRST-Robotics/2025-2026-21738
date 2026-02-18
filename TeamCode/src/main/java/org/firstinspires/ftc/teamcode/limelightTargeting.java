package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*//*/

import java.util.concurrent.Delayed;

import kotlinx.coroutines.Delay;


@TeleOp(name="main with limelight", group="OpMode")
public class limelightTargeting extends CommandOpMode {

    ShooterSubsystem s;
    DrivebaseSubsystem d;
    LimelightSubsystem l;
    ShooterSpeed sh;

    @Override
    public void initialize() {
        GamepadEx gamepadEx = new GamepadEx(gamepad1);
        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);
        s = new ShooterSubsystem(gamepadEx2, hardwareMap, telemetry);
        d = new DrivebaseSubsystem(gamepadEx, hardwareMap);
        l = new LimelightSubsystem(hardwareMap, telemetry);

        s.flap.setPosition(0);
        s.shooterSetSpeed=1200;

        Button shooterButton = new GamepadButton(
                gamepadEx2, GamepadKeys.Button.A);

        double speed;

        shooterButton.whileHeld(new launchCommand(s));
        shooterButton.whenReleased(new ShootPowerOffCommand(s, telemetry));

        Button shakeButton = new GamepadButton(
                gamepadEx2, GamepadKeys.Button.Y);
        shakeButton.whileHeld(new shake(d));



        Button Artifacts = new GamepadButton(
                gamepadEx, GamepadKeys.Button.Y);

        Button AprilTag = new GamepadButton(
                gamepadEx, GamepadKeys.Button.Y);

        Artifacts.whenHeld(new limelightArtifactTrackingCommand(d, l));
        AprilTag.whenHeld(new limelightApriltagTargetingCommand(d, l));
    }
}