//package org.firstinspires.ftc.teamcode.auto;
//
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.Pose;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.seattlesolvers.solverslib.command.CommandOpMode;
//import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
//import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
//
//
//public abstract class auto_abstract extends CommandOpMode {
//    public abstract static class sidedauto extends auto_abstract {
//        int side;
//        public sidedauto(){
//            super();
//            side=1;
//        }
//    }
//    public abstract SequentialCommandGroup makepath();
//
//    @Autonomous(name = "auto1a blue")
//    public static class auto1A extends sidedauto {
//        @Override
//        public SequentialCommandGroup makepath() {
//            follower.setStartingPose(new Pose(27.6, 130.3, Math.toRadians(144))); //old h = 216
//            follower.update();
//            return new SequentialCommandGroup(
//                    new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 106, AngleUnit.DEGREES, -135), 0.70, telemetry),
//                    new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 35.6, AngleUnit.DEGREES, 180), 0.70, telemetry), // slow down for better accuracy
//                    new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 21, 35.6, AngleUnit.DEGREES, 180), 0.70, telemetry),
//                    new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 35.6, AngleUnit.DEGREES, 135), 0.70, telemetry),
//                    new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 50.5, 120, AngleUnit.DEGREES, 135), 0.70, telemetry)
//            );
//        }
//    }
//    @Autonomous(name = "auto1b blue")
//    public static class auto1B extends auto_abstract {
//        @Override
//        public SequentialCommandGroup makepath() {
//            follower.setStartingPose(new Pose(27.6, 130.3, Math.toRadians(144)));
//            follower.update();
//            // real path here
//            return new SequentialCommandGroup(
//                    new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 106, AngleUnit.DEGREES, -135), 0.70, telemetry),
//                    new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 60, AngleUnit.DEGREES, 180), 0.70, telemetry), // slow down for better accuracy
//                    new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 24, 60, AngleUnit.DEGREES, 180), 0.70, telemetry),
//                    new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 45, 60, AngleUnit.DEGREES, 135), 0.70, telemetry),
//                    new gotoCommand(follower, new Pose2D(DistanceUnit.INCH, 50.5, 120, AngleUnit.DEGREES, 135), 0.70, telemetry)
//            );
//        }
//    }
//
//
//
//    Follower follower;
//    @Override
//    public void initialize() {
//        telemetry.addLine("init pressed");
//        telemetry.update();
//        follower = Constants.createFollower(hardwareMap);
//        follower.update();
//        SequentialCommandGroup path = makepath();
//        // real path here
//        path.schedule();
//    }
//}
