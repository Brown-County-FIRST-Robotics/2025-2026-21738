package org.firstinspires.ftc.teamcode;

import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.CommandBase;

public class intakeCommand extends CommandBase {


    boolean isFinished;
    double speed;
    PathChain path;
    ShooterSubsystem s;
    public intakeCommand(ShooterSubsystem S, double power) {
        speed = power;
        s = S;
    }
    @Override
    public void execute() {
        s.intake.setPower(speed);
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
