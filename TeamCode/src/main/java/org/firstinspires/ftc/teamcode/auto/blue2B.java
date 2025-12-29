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


@Autonomous(name="2B Blue, D")
public class blue2B extends CommandOpMode {
    Follower follower;
    boolean red = false;
    @Override
    public void initialize() {
        follower = Constants.createFollower(hardwareMap);
        follower.update();
        follower.setStartingPose(new Pose(60.3, 12.1, Math.toRadians(110)));
        follower.update();
        // real path here
        SequentialCommandGroup path = new SequentialCommandGroup(
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 44, 61, AngleUnit.DEGREES, 180), 0.70, red),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 25, 61, AngleUnit.DEGREES, 180), 0.70, red), // slow down for better accuracy
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 44, 61, AngleUnit.DEGREES, 180), 0.70, red),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 50.5, 120, AngleUnit.DEGREES, 135), 0.70, red)
        );
        path.schedule();
    }
}