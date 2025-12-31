package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShootFireCommand extends CommandBase {

    ShooterSubsystem m_subsystem;
    Telemetry m_telemetry;
    boolean isFinished =false;

    ElapsedTime m_timer = new ElapsedTime();

    public ShootFireCommand(ShooterSubsystem subsystem, Telemetry telemetry) {
        m_subsystem = subsystem;
        m_telemetry = telemetry;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }
    // The subsystem the command runs on

    @Override
    public void initialize() {
        isFinished =false;
        m_subsystem.door.setPosition(84/300.0);
        m_timer.reset();

//        m_telemetry.addLine("IN");
//        m_subsystem.shooter.setVelocity(1200);
//        m_telemetry.update();
    }
    @Override
    public void execute() {
        if (m_timer.seconds() > .2) {
            m_subsystem.kicker.setPosition(100/300.0);
        }
        if (m_timer.seconds() > 0.8) {
            isFinished=true;
            m_subsystem.kicker.setPosition(0);
        }
    }

    @Override
    public boolean isFinished() {

        return isFinished;

    }

}
