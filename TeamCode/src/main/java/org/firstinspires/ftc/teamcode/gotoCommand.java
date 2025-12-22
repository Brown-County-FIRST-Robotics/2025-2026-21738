package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class gotoCommand extends CommandBase {

    Follower follower;
    Pose pose;
    double speed;

    boolean isFinished;
    boolean stay;

    PathChain path;
    public gotoCommand(Follower controller, Pose2D location, double maxPower, boolean holdEnd) {
        pose = new Pose(location.getX(DistanceUnit.INCH) + 72, location.getY(DistanceUnit.INCH) + 72, location.getHeading(AngleUnit.RADIANS));
        follower = controller;
        isFinished = false;
        speed = maxPower;
        stay = holdEnd;
    }
    public gotoCommand(Follower controller, Pose2D location, double maxPower) {
        pose = new Pose(location.getX(DistanceUnit.INCH) + 72, location.getY(DistanceUnit.INCH) + 72, location.getHeading(AngleUnit.RADIANS));
        follower = controller;
        isFinished = false;
        speed = maxPower;
        stay = false;
    }
    public gotoCommand(Follower controller, Pose2D location, boolean holdEnd) {
        pose = new Pose(location.getX(DistanceUnit.INCH) + 72, location.getY(DistanceUnit.INCH) + 72, location.getHeading(AngleUnit.RADIANS));
        follower = controller;
        isFinished = false;
        speed = 1.0;
        stay = holdEnd;
    }
    public gotoCommand(Follower controller, Pose2D location) {
        pose = new Pose(location.getX(DistanceUnit.INCH) + 72, location.getY(DistanceUnit.INCH) + 72, location.getHeading(AngleUnit.RADIANS));
        follower = controller;
        isFinished = false;
        speed = 1.0;
        stay = false;
    }
    // The subsystem the command runs on

    @Override
    public void initialize() {
        follower.update();
        follower.setMaxPower(speed);
        Pose start = follower.getPose();
        path = follower.pathBuilder()
                .addPath(new BezierLine(start, pose))
                .setLinearHeadingInterpolation(start.getHeading(), pose.getHeading())
                .build();
        follower.followPath(path, stay);
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
