package org.firstinspires.ftc.teamcode;

import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class intakeCommand extends CommandBase {


    boolean isFinished;
    double speed;
    ElapsedTime time;
    ShooterSubsystem s;
    Telemetry t;
    public intakeCommand(ShooterSubsystem S, double power) {
        speed = power;
        s = S;
    }
    @Override
    public void initialize() {
        super.initialize();
        s.intakePower = speed;
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
