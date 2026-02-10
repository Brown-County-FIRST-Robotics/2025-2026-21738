package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@TeleOp(name="limelight")
public class limelightTestPullingPose extends LinearOpMode {
    private Limelight3A limelight;

    @Override
    public void runOpMode() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);

        limelight.start();
        waitForStart();
        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();
            if (result != null) {
                if (result.isValid()) {
                    Pose3D botPose = result.getBotpose();
                    telemetry.addData("x", botPose.getPosition().toUnit(DistanceUnit.INCH).x);
                    telemetry.addData("y", botPose.getPosition().toUnit(DistanceUnit.INCH).y);
                    telemetry.addData("h", botPose.getOrientation().getYaw(AngleUnit.DEGREES));
                    telemetry.update();
                }
            }
        }
        limelight.stop();
    }
}