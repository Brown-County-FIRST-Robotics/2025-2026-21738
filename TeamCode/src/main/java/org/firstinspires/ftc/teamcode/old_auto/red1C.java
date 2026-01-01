package org.firstinspires.ftc.teamcode.old_auto;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Autonomous(name="1C Red, D")
@Disabled
public class red1C extends CommandOpMode {
    Follower follower;
    boolean red = true;
    @Override
    public void initialize() {
        follower = Constants.createFollower(hardwareMap);
        follower.update();
        follower.setStartingPose(new Pose(144 - 27.6, 130.3, Math.toRadians(180 - 144)));
        follower.update();
        // real path here
        SequentialCommandGroup path = new SequentialCommandGroup(
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 106, AngleUnit.DEGREES, -135), 0.70, red),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 85, AngleUnit.DEGREES, 180), 0.70, red), // slow down for better accuracy
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 23.5, 85, AngleUnit.DEGREES, 180), 0.70, red),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 85, AngleUnit.DEGREES, 135), 0.70, red),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 50.5, 120, AngleUnit.DEGREES, 135), 0.70, red)
        );
        path.schedule();
    }
}