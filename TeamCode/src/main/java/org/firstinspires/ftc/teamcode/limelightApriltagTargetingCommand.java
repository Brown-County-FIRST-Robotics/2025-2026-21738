package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.controller.PIDController;

public class limelightApriltagTargetingCommand extends CommandBase {
    HardwareMap hMap;
    boolean isFinished;

    Servo servo;
    Limelight3A limelight;
    PIDController pid;
    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;
    public limelightApriltagTargetingCommand(HardwareMap hmap) {
        hMap = hmap;
        isFinished = false;
    }
    @Override
    public void initialize() {
        super.initialize();
        limelight = hMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);

        frontLeft = hMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hMap.get(DcMotorEx.class, "frontRight");
        backLeft = hMap.get(DcMotorEx.class, "backLeft");
        backRight = hMap.get(DcMotorEx.class, "backRight");
        servo = hMap.get(Servo.class, "limeservo");
        servo.setPosition(30.0/300);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        pid = new PIDController(0.04, 0, 0.2);
        pid.setSetPoint(0);
        pid.setTolerance(2.5);

        limelight.start();
    }
    @Override
    public void execute() {
        super.execute();

        LLResult result = limelight.getLatestResult();
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
        double backLeftPower = -right;
        double backRightPower = right;

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

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }
    @Override
    public boolean isFinished() {
        return isFinished;
    }

    public void end(boolean canceled) {
        limelight.stop();
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
