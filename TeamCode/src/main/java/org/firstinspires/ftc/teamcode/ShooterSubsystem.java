package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

/**
 * A gripper mechanism that grabs a stone from the quarry.
 * Centered around the Skystone game for FTC that was done in the 2019
 * to 2020 season.
 */
public  class ShooterSubsystem extends SubsystemBase {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public DcMotor intake = null;
    DcMotorEx shooter = null;

    Servo kicker = null;
    Servo flap = null;
    Servo door = null;
    GamepadEx gamepadEx;


    public ShooterSubsystem(GamepadEx gamepadEx, final HardwareMap hMap) {
        this.gamepadEx = gamepadEx;




        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        intake = hMap.get(DcMotor.class, "intake");
        shooter = hMap.get(DcMotorEx.class, "shooter");

        kicker = hMap.get(Servo.class, "kicker");
        flap = hMap.get(Servo.class, "flap");
        door = hMap.get(Servo.class, "door");


        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        intake.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(300, 0, 0, 15.7025));


        this.door.setPosition(0);
        this.kicker.setPosition(0);
        this.shooter.setVelocity(0);

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
     */


    /*
     * Code to run ONCE when the driver hits START
     */

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */
    @Override
    public void periodic() {
        // Setup a variable for each drive wheel to save power level for telemetry



        double swallow = gamepadEx.gamepad.right_trigger;


        float shooter = gamepadEx.gamepad.left_stick_x;


        intake.setPower(-0.75 * signedSquare(swallow));



    }







    //takes a value and multiplies it by its absolute value, then returns that (square function but keeps the sign)
    private double signedSquare(double x) {
        return x * Math.abs(x);
    }

}



