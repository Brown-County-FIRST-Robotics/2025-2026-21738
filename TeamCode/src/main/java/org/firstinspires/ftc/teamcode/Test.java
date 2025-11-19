package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

public class Test extends CommandBase {

    TestSubsystem m_subsystem;
   // GamepadEx driverOp = new GamepadEx(gamepad1);
    //GamepadEx toolOp = new GamepadEx(gamepad2);




    public Test(TestSubsystem subsystem) {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }
    // The subsystem the command runs on

    @Override
    public void initialize() {

        m_subsystem.laungture.setPower(1);

    }
    @Override
    public void execute() {
        if (m_subsystem.laungture.getVelocity() > 1200) {
            m_subsystem.kicker.setPosition(.75);
        }
    }

    @Override
    public boolean isFinished() {
        return m_subsystem.laungture.getVelocity() > 1200;

    }

}
