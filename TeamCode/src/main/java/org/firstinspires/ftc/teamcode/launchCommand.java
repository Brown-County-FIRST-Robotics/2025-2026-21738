package org.firstinspires.ftc.teamcode;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

/**
 * A complex auto command that drives forward,
 * releases a stone, and then drives backward.
 */
public class launchCommand extends SequentialCommandGroup {
    public launchCommand(ShooterSubsystem s) {
        addCommands(
                new ShootPowerUpCommand(s, null),
                new ShootFireCommand(s, null),
                new ShootFinshCommand(s, null)
        );
    }

}