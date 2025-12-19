package org.firstinspires.ftc.teamcode;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "testing IVX", group = "Tests")
public class test extends OpMode {
    Follower follower;
    Pose pose1;
    Pose pose2;
    Pose pose3;
    Pose pose4;
    PathChain path;
    public void init() {
        follower = Constants.createFollower(hardwareMap);
    }

    @Override
    public void start() {
        Pose startPose = new Pose(0, 0, Math.toRadians(90));
        Pose pose1 = new Pose(0, 60, Math.toRadians(0));
        follower.setStartingPose(startPose);
        follower.setMaxPower(0.6);
        follower.update();
        path = follower.pathBuilder()
                .addPath(new BezierLine(startPose, pose1))
                .setLinearHeadingInterpolation(startPose.getHeading(), pose1.getHeading())
                .addPath(new BezierLine(pose1, startPose))
                .setLinearHeadingInterpolation(pose1.getHeading(), startPose.getHeading())
                .build();

        follower.followPath(path);
        /*path = follower.pathBuilder()
                .addPath(new BezierLine(pose4, pose2))
                .setLinearHeadingInterpolation(pose4.getHeading(), pose2.getHeading())
                .addPath(new BezierLine(pose2, pose3))
                .setLinearHeadingInterpolation(pose2.getHeading(), pose3.getHeading())
                .addPath(new BezierLine(pose3, pose4))
                .setLinearHeadingInterpolation(pose3.getHeading(), pose4.getHeading())
                .addPath(new BezierLine(pose4, pose1))
                .setLinearHeadingInterpolation(pose4.getHeading(), pose1.getHeading())
                .build();

         */
    }

    @Override
    public void loop() {
        follower.update();
        telemetry.addData("pose", follower.getPose());
        telemetry.update();
        if (!follower.isBusy()) {
             follower.followPath(path);
        }
    }
}
