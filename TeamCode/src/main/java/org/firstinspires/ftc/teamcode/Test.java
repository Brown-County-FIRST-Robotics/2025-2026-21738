package org.firstinspires.ftc.teamcode;

import com.seattlesolvers.solverslib.command.CommandBase;

public class Test extends CommandBase {

    TestSubsystem m_subsystem;

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
