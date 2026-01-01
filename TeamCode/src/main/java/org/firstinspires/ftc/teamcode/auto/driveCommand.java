package org.firstinspires.ftc.teamcode.auto;

import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.DrivebaseSubsystem;

public class driveCommand extends CommandBase {

    DrivebaseSubsystem d;
    double speed;

    PathChain path;
    public driveCommand(DrivebaseSubsystem D, double Speed) {
        speed = Speed;
        d = D;
    }

    @Override
    public void initialize() {
        d.frontLeft.setPower(speed);
        d.frontRight.setPower(speed);
        d.backLeft.setPower(speed);
        d.backRight.setPower(speed);
    }
    public void end(boolean canceled) {
        d.frontLeft.setPower(0);
        d.frontRight.setPower(0);
        d.backLeft.setPower(0);
        d.backRight.setPower(0);
    }
}
