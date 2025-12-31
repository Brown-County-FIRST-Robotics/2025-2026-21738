package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

/**
 * A gripper mechanism that grabs a stone from the quarry.
 * Centered around the Skystone game for FTC that was done in the 2019
 * to 2020 season.
 */
public  class ShakerSubsystem extends SubsystemBase {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

   /* private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    Servo door = null;
    GamepadEx gamepadEx;

    GamepadEx gamepadEx2;
    ElapsedTime m_timer = new ElapsedTime();
    double Time = 0;
    boolean shaker;


    public ShakerSubsystem(GamepadEx gamepadEx, final HardwareMap hMap) {
        this.gamepadEx = gamepadEx;
        shaker = gamepadEx.gamepad.y;




        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontLeft = hMap.get(DcMotor.class, "frontLeft");
        frontRight = hMap.get(DcMotor.class, "frontRight");
        backLeft = hMap.get(DcMotor.class, "backLeft");
        backRight = hMap.get(DcMotor.class, "backRight");



        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips



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
   /* @Override
    public void periodic() {
        // Setup a variable for each drive wheel to save power level for telemetry




        double axial   =  0;
        double lateral =  0;
        double yaw     =  0;


        double frontLeftPower  = axial + lateral + yaw;
        double frontRightPower = axial - lateral - yaw;
        double backLeftPower   = axial - lateral + yaw;
        double backRightPower  = axial + lateral - yaw;

        double max;
        max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower  /= max;
            frontRightPower /= max;
            backLeftPower   /= max;
            backRightPower  /= max;

        }

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        if(shaker){
            Time = 1;
        }


        if (shaker && Time == 1) {
            m_timer.reset();
            Time = 2;
        }


        if (Time == 2) {
            frontLeft.setPower(1);
            frontRight.setPower(1);
            backLeft.setPower(1);
            backRight.setPower(1);

            if (m_timer.seconds() >= .2) {
                m_timer.reset();
                Time = 3;
            }
        }


        else if (Time == 3) {
            frontLeft.setPower(-1);
            frontRight.setPower(-1);
            backLeft.setPower(-1);
            backRight.setPower(-1);

            if (m_timer.seconds() >= .2) {
                Time = 4;
            }
        }


        else if (Time == 4) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);

            Time = .5;
        }













    }







    //takes a value and multiplies it by its absolute value, then returns that (square function but keeps the sign)
    private double signedSquare(double x) {
        return x * Math.abs(x);*/
    }





