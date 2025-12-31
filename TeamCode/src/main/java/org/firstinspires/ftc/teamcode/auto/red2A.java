package org.firstinspires.ftc.teamcode.auto;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Autonomous(name="2A Red, D")
public class red2A extends CommandOpMode {
    Follower follower;
    boolean red = true;
    @Override
    public void initialize() {
        follower = Constants.createFollower(hardwareMap);
        follower.update();
        follower.setStartingPose(new Pose(144 - 60.3, 12.1, Math.toRadians(180 - 110)));
        follower.update();
        // real path here
        SequentialCommandGroup path = new SequentialCommandGroup(
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 50.5, 37, AngleUnit.DEGREES, 180), 0.70, red),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 21, 37, AngleUnit.DEGREES, 180), 0.70, red), // slow down for better accuracy
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 50.5, 37, AngleUnit.DEGREES, 180), 0.70, red),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 50.5, 120, AngleUnit.DEGREES, 135), 0.70, red)
        );
        path.schedule();
    }
}