package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;


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
                    telemetry.addData("x", botPose.getPosition().x);
                    telemetry.addData("y", botPose.getPosition().y);
                    telemetry.addData("h", botPose.getOrientation().getYaw());
                    telemetry.update();
                }
            }
        }
        limelight.stop();
    }
}