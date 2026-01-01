package org.firstinspires.ftc.teamcode.old_auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class gotoCommand extends CommandBase {

    Follower follower;
    Pose pose;
    double speed;

    boolean isFinished;

    PathChain path;
    public gotoCommand(Follower controller, Pose2D location, double maxPower, boolean isRed) {
        if (isRed) {
            pose = new Pose(144 - location.getX(DistanceUnit.INCH), location.getY(DistanceUnit.INCH), Math.toRadians(180 - location.getHeading(AngleUnit.DEGREES)));

        }else {
            pose = new Pose(location.getX(DistanceUnit.INCH), location.getY(DistanceUnit.INCH), location.getHeading(AngleUnit.RADIANS));
        }
        follower = controller;
        isFinished = false;
        speed = maxPower;
    }

    @Override
    public void initialize() {
        follower.update();
        follower.setMaxPower(speed);
        follower.update();
        Pose start = follower.getPose();
        path = follower.pathBuilder()
                .addPath(new BezierLine(start, pose))
                .setLinearHeadingInterpolation(start.getHeading(), pose.getHeading())
                .build();
        follower.followPath(path);
    }
    @Override
    public void execute() {
        follower.update();
        isFinished = !follower.isBusy();
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    public void end(boolean canceled) {

        follower.breakFollowing();
    }
}
