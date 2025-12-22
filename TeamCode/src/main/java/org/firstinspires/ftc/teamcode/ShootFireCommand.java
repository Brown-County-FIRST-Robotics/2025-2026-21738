package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShootFireCommand extends CommandBase {

    Telemetry m_telemetry;
    boolean isFinished = false;

    ElapsedTime m_timer = new ElapsedTime();

    public ShootFireCommand(Telemetry telemetry) {
        m_telemetry = telemetry;
    }
    // The subsystem the command runs on

    @Override
    public void initialize() {
        isFinished =false;
        m_timer.reset();
        m_telemetry.addLine("launcher on");
        m_telemetry.update();
    }
    @Override
    public void execute() {
        if (m_timer.seconds() > 5) {
            m_telemetry.update();
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {

        return isFinished;

    }

}
