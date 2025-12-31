package org.firstinspires.ftc.teamcode.auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class gotoCommand extends CommandBase {

    Follower follower;
    Pose pose;
    double speed;

    boolean isFinished;

    static double scale = 1.0/1.17;

    PathChain path;
    public gotoCommand(Follower controller, Pose2D location, double maxPower, boolean isRed) {
        if (isRed) {
            pose = new Pose(144 - location.getX(DistanceUnit.INCH), location.getY(DistanceUnit.INCH), Math.toRadians(180 - location.getHeading(AngleUnit.DEGREES)));
        }else {
            pose = new Pose(location.getX(DistanceUnit.INCH), location.getY(DistanceUnit.INCH), location.getHeading(AngleUnit.RADIANS));
        }
        pose = gotoCommand.scale(pose, false);
        follower = controller;
        isFinished = false;
        speed = maxPower;
    }

    public static Pose scale(Pose location, boolean isRed) {
        if (isRed) {
            location = new Pose(144 - location.getX(), location.getY(), Math.toRadians(180 - Math.toDegrees(location.getHeading())));
        }else {
            location = new Pose(location.getX(), location.getY(), location.getHeading());
        }
        return new Pose( scale * location.getX(), scale * location.getY(), location.getHeading());
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
