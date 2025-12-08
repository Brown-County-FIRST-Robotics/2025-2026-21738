package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;


@TeleOp(name="main", group="OpMode")
public class MainTeleopOpMode extends CommandOpMode {

    ShooterSubsystem s;
    @Override
    public void initialize() {
        GamepadEx gamepadEx = new GamepadEx(gamepad1);
        s = new ShooterSubsystem(gamepadEx, hardwareMap);
        Button exampleButton = new GamepadButton(
                gamepadEx, GamepadKeys.Button.A

        );
        // exampleButton.whenPressed(new Test(s, telemetry));
         exampleButton.whenPressed(new ServoFlap(s, telemetry));
    }

}