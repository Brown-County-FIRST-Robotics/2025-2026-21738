package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name="TestTest", group="Iterative OpMode")
public class TestTestOpMode extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();




    private DcMotor arm = null;

    private Servo elbow = null;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        arm = hardwareMap.get(DcMotor.class, "arm");

        elbow = hardwareMap.get(Servo.class, "elbow");


        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips

        arm.setDirection(DcMotorSimple.Direction.FORWARD);


        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits START
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry


        telemetry.addData("Status", "Run Time: " + runtime.toString());

        double rotation = gamepad1.left_stick_y; //turning drive chassis
        double movement = -gamepad1.left_stick_x;//Forward/backward drive chassis
        double strafing = -gamepad1.right_stick_x;
        double uppies = gamepad2.left_stick_y;//vertical arm movement



        if (uppies > 0.05) {//vertical arm movement
            arm.setPower(.5);
        } else if (uppies < -0.05) {
            arm.setPower(-.5);
        } else {
            arm.setPower(0);
        }




        if (gamepad2.x) {
            elbow.setPosition(0.2);//closer to 0
        }
        if (gamepad2.y) {
            elbow.setPosition(0.65); //TODO: increase speed
        }
        telemetry.addData("Position", elbow.getPosition());//Shows the position of elbow on drive hub


    }


    //0.3689
//    .625
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }


    }


