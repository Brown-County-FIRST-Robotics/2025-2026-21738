package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShootPowerOffCommand extends CommandBase {

    ShooterSubsystem m_subsystem;
    Telemetry m_telemetry;
    boolean isFinished =false;

    ElapsedTime m_timer = new ElapsedTime();

    public ShootPowerOffCommand(ShooterSubsystem subsystem, Telemetry telemetry) {
        m_subsystem = subsystem;
        m_telemetry = telemetry;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }
    // The subsystem the command runs on

    @Override
    public void initialize() {
        isFinished = false;
        m_subsystem.door.setPosition(0);
        m_subsystem.kicker.setPosition(0);
        m_subsystem.shooter.setVelocity(1000);
        m_timer.reset();

    }
    @Override
    public void execute() {
        if (m_timer.seconds() > 0.3) {
            isFinished=true;
        }
    }

    @Override
    public boolean isFinished() {

        return isFinished;

    }

}
