/* Copyright (c) 2021 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@TeleOp(name="parking VII", group="OpMode")
public class driver extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;
    Follower follower;
    PathChain path;
    double power = 0.6;
    Pose poseA;
    Pose poseB;
    Pose poseX;
    Pose poseY;
    @Override
    public void runOpMode() {

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(0, 0, Math.toRadians(0)));
        follower.update();
        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");

        // ########################################################################################
        // !!!            IMPORTANT Drive Information. Test your motor directions.            !!!!!
        // ########################################################################################
        // Most robots need the motors on one side to be reversed to drive forward.
        // The motor reversals shown here are for a "direct drive" robot (the wheels turn the same direction as the motor shaft)
        // If your robot has additional gear reductions or uses a right-angled drive, it's important to ensure
        // that your motors are turning in the correct direction.  So, start out with the reversals here, BUT
        // when you first test your robot, push the left joystick forward and observe the direction the wheels turn.
        // Reverse the direction (flip FORWARD <-> REVERSE ) of any wheel that runs backward
        // Keep testing until ALL the wheels move the robot forward when you push the left joystick forward.
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if (!follower.isBusy()) {
                if (gamepad1.dpadDownWasPressed() && power >= 0.05)
                    power -= 0.05;
                if (gamepad1.dpadUpWasPressed())
                    power += 0.05;
                double max;

                // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
                double axial = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
                double lateral = gamepad1.left_stick_x;
                double yaw = gamepad1.right_stick_x;

                // Combine the joystick requests for each axis-motion to determine each wheel's power.
                // Set up a variable for each drive wheel to save the power level for telemetry.
                double frontLeftPower = axial + lateral + yaw;
                double frontRightPower = axial - lateral - yaw;
                double backLeftPower = axial - lateral + yaw;
                double backRightPower = axial + lateral - yaw;

                // Normalize the values so no wheel power exceeds 100%
                // This ensures that the robot maintains the desired motion.
                max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
                max = Math.max(max, Math.abs(backLeftPower));
                max = Math.max(max, Math.abs(backRightPower));

                if (max > 1.0) {
                    frontLeftPower /= max;
                    frontRightPower /= max;
                    backLeftPower /= max;
                    backRightPower /= max;
                }

                // This is test code:
                //
                // Uncomment the following code to test your motor directions.
                // Each button should make the corresponding motor run FORWARD.
                //   1) First get all the motors to take to correct positions on the robot
                //      by adjusting your Robot Configuration if necessary.
                //   2) Then make sure they run in the correct direction by modifying the
                //      the setDirection() calls above.
                // Once the correct motors move in the correct direction re-comment this code.

                /*
                frontLeftPower  = gamepad1.x ? 1.0 : 0.0;  // X gamepad
                backLeftPower   = gamepad1.a ? 1.0 : 0.0;  // A gamepad
                frontRightPower = gamepad1.y ? 1.0 : 0.0;  // Y gamepad
                backRightPower  = gamepad1.b ? 1.0 : 0.0;  // B gamepad
                */

                // Send calculated power to wheels
                frontLeftDrive.setPower(frontLeftPower);
                frontRightDrive.setPower(frontRightPower);
                backLeftDrive.setPower(backLeftPower);
                backRightDrive.setPower(backRightPower);
            }
            if (!gamepad1.right_bumper) {
                if (gamepad1.aWasPressed() && poseA != null) {
                    Pose myPose = follower.getPose();
                    follower.setMaxPower(power);
                    follower.update();
                    path = follower.pathBuilder()
                            .addPath(new BezierLine(myPose, poseA))
                            .setLinearHeadingInterpolation(myPose.getHeading(), poseA.getHeading())
                            .build();

                    follower.followPath(path, false);
                }
                if (gamepad1.bWasPressed() && poseB != null) {
                    Pose myPose = follower.getPose();
                    follower.setMaxPower(power);
                    follower.update();
                    path = follower.pathBuilder()
                            .addPath(new BezierLine(myPose, poseB))
                            .setLinearHeadingInterpolation(myPose.getHeading(), poseB.getHeading())
                            .build();

                    follower.followPath(path, false);
                }
                if (gamepad1.xWasPressed() && poseX != null) {
                    Pose myPose = follower.getPose();
                    follower.setMaxPower(power);
                    follower.update();
                    path = follower.pathBuilder()
                            .addPath(new BezierLine(myPose, poseX))
                            .setLinearHeadingInterpolation(myPose.getHeading(), poseX.getHeading())
                            .build();

                    follower.followPath(path, false);
                }
                if (gamepad1.yWasPressed() && poseY != null) {
                    Pose myPose = follower.getPose();
                    follower.setMaxPower(power);
                    follower.update();
                    path = follower.pathBuilder()
                            .addPath(new BezierLine(myPose, poseY))
                            .setLinearHeadingInterpolation(myPose.getHeading(), poseY.getHeading())
                            .build();

                    follower.followPath(path, false);
                }
                
                if (gamepad1.aWasReleased() || gamepad1.bWasReleased() || gamepad1.xWasReleased() || gamepad1.yWasReleased()) {
                    follower.breakFollowing();
                }
            }else {
                // set pose mode
                if (gamepad1.aWasPressed()) {
                    poseA = follower.getPose();
                }
                if (gamepad1.bWasPressed()) {
                    poseB = follower.getPose();
                }
                if (gamepad1.xWasPressed()) {
                    poseX = follower.getPose();
                }
                if (gamepad1.yWasPressed()) {
                    poseY = follower.getPose();
                }
            }
            
            
            follower.update();
            telemetry.addData("auto power", power);
            telemetry.addData("X", follower.getPose().getX());
            telemetry.addData("Y", follower.getPose().getY());
            telemetry.addData("H", Math.toDegrees(follower.getPose().getHeading()));
            telemetry.update();
        }
    }}
