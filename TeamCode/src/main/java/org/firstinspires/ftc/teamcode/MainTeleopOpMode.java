package org.firstinspires.ftc.teamcode;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.button.Button;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import java.util.concurrent.Delayed;

import kotlinx.coroutines.Delay;


@Autonomous(name="project IV")
public class MainTeleopOpMode extends CommandOpMode {

    Follower follower;
    @Override
    public void initialize() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(72, 72, Math.toRadians(0))); // center of field
        follower.update();
        double power = 1.0;
        SequentialCommandGroup path = new SequentialCommandGroup(
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 48, 0, AngleUnit.DEGREES, 0), power, false),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 48, -20, AngleUnit.DEGREES, 0), power, false),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 0, -20, AngleUnit.DEGREES, 0), power, false),
                new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0), power, false),
        );
        path.schedule(false);
    }

}