package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.Delayed;

import kotlinx.coroutines.Delay;


@TeleOp(name="main", group="OpMode")
public class MainTeleopOpMode extends CommandOpMode {

    ShooterSubsystem s;
    DrivebaseSubsystem d;
    ShooterSpeed sh;

    @Override
    public void initialize() {
        GamepadEx gamepadEx = new GamepadEx(gamepad1);
        GamepadEx gamepadEx2 = new GamepadEx(gamepad2);
        s = new ShooterSubsystem(gamepadEx2, hardwareMap, telemetry);

        s.flap.setPosition(0);
        s.shooterSetSpeed=1200;
        d = new DrivebaseSubsystem(gamepadEx, hardwareMap);
        Button shooterButton = new GamepadButton(
                gamepadEx2, GamepadKeys.Button.A);


        double speed;

        shooterButton.whileHeld(new launchCommand(s));
        shooterButton.whenReleased(new ShootPowerOffCommand(s, telemetry));

        Button shakeButton = new GamepadButton(
                gamepadEx2, GamepadKeys.Button.Y);
        shakeButton.whileHeld(new shake(d));

    }

}