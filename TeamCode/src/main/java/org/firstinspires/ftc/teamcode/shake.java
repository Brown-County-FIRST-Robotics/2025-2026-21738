package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

public class shake extends CommandBase {


    boolean isFinished;
    DrivebaseSubsystem d;
    ElapsedTime t;
    public shake(DrivebaseSubsystem D) {
        d = D;
    }

    @Override
    public void initialize() {
        super.initialize();
        isFinished = false;
        t = new ElapsedTime();
        d.frontLeft.setPower(-1);
        d.frontRight.setPower(-1);
        d.backLeft.setPower(-1);
        d.backRight.setPower(-1);
        t.reset();
    }

    @Override
    public void execute() {
        if (t.seconds() >= 0.1) {
            d.frontLeft.setPower(1);
            d.frontRight.setPower(1);
            d.backLeft.setPower(1);
            d.backRight.setPower(1);
        }
        if (t.seconds() >= 0.2) {
            d.frontLeft.setPower(0);
            d.frontRight.setPower(0);
            d.backLeft.setPower(0);
            d.backRight.setPower(0);
            isFinished = true;
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
