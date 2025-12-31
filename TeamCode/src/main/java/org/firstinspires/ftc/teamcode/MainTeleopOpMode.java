package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

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
        s = new ShooterSubsystem(gamepadEx2, hardwareMap);
        d = new DrivebaseSubsystem(gamepadEx, hardwareMap);
        Button shooterButton = new GamepadButton(
                gamepadEx2, GamepadKeys.Button.A



        );

        double speed;
        // exampleButton.whenPressed(new Test(s, telemetry));
        // shooterButton.whileHeld(new SequentialCommandGroup(new ShootPowerUpCommand(s, telemetry), new ShootFireCommand(s, telemetry), new ShootFinshCommand(s, telemetry)));
        shooterButton.whileHeld(new launchCommand(s));
        shooterButton.whenReleased(new ShootPowerOffCommand(s, telemetry));

        //   exampleButton.whenPressed(new ServoFlap(s, telemetry));
    }

}