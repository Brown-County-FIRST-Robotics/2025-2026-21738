package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.controller.PIDController;

public class limelightArtifactTrackingCommand extends CommandBase {
    HardwareMap hMap;
    boolean isFinished;

    Servo servo;
    Limelight3A limelight;
    PIDController pid;
    DrivebaseSubsystem d;
    LimelightSubsystem l;
    public limelightArtifactTrackingCommand(DrivebaseSubsystem D, LimelightSubsystem L) {
        isFinished = false;
        d = D;
        l = L;
    }
    @Override
    public void initialize() {
        super.initialize();
        l.auto();

        pid = new PIDController(0.045, 0, 0.10);
        pid.setSetPoint(0);
        pid.setTolerance(2.5);
    }
    @Override
    public void execute() {
        super.execute();

        LLResult result = l.data;
        double right = 0;

        if (result != null) {
            if (result.isValid()) {
                right = pid.calculate(result.getTx());
            }
        }else {
            isFinished = true;
        }

        if (pid.atSetPoint()) {
            isFinished = true;
        }

        double frontLeftPower = -right;
        double frontRightPower = right;
        double backLeftPower = right;
        double backRightPower = -right;

        double max;
        max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower /= max;
            frontRightPower /= max;
            backLeftPower /= max;
            backRightPower /= max;
        }

        d.frontLeft.setPower(frontLeftPower);
        d.frontRight.setPower(frontRightPower);
        d.backLeft.setPower(backLeftPower);
        d.backRight.setPower(backRightPower);
    }
    @Override
    public boolean isFinished() {
        return isFinished;
    }

    public void end(boolean canceled) {
        d.frontLeft.setPower(0);
        d.frontRight.setPower(0);
        d.backLeft.setPower(0);
        d.backRight.setPower(0);
    }
}
