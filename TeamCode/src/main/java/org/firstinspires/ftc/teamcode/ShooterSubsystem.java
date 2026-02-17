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

import org.firstinspires.ftc.robotcore.external.Telemetry;


public  class ShooterSubsystem extends SubsystemBase {

    Telemetry m_telemetry;

    private ElapsedTime runtime = new ElapsedTime();

    public DcMotor intake = null;
    DcMotorEx shooter = null;



    Servo kicker = null;

    public Servo flap = null;
    public Servo LimeServo = null;
    Servo door = null;

    public boolean teleop;
    GamepadEx gamepadEx;

    public double shooterSetSpeed;
    double deathCommand;


    public double intakePower;

    public ShooterSubsystem(GamepadEx gamepadEx, final HardwareMap hMap, Telemetry telemetry) {
        this.gamepadEx = gamepadEx;
        teleop = true;
        m_telemetry = telemetry;
        shooterSetSpeed = 1000;
        deathCommand = 10;




        intake = hMap.get(DcMotor.class, "intake");
        shooter = hMap.get(DcMotorEx.class, "shooter");
        LimeServo = hMap.get(Servo.class, "limeservo");

        kicker = hMap.get(Servo.class, "kicker");
        flap = hMap.get(Servo.class, "flap");
        door = hMap.get(Servo.class, "door");


        intake.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(300, 0, 0, 15.7025));


        this.door.setPosition(0);
        this.kicker.setPosition(0);
        this.shooter.setVelocity(0);




    }




    @Override
    public void periodic() {





        double swallow = gamepadEx.gamepad.right_trigger;
        double out = gamepadEx.gamepad.left_trigger;
        boolean Up = gamepadEx.gamepad.dpad_up;
        boolean Down = gamepadEx.gamepad.dpad_down;
        boolean Left = gamepadEx.gamepad.dpad_left;
        boolean Right = gamepadEx.gamepad.dpad_right;
        boolean Gate = gamepadEx.gamepad.x;
        boolean StopTheAnnoyingSound = gamepadEx.gamepad.left_bumper;

        boolean OkFine = gamepadEx.gamepad.right_bumper;
        double Flap = flap.getPosition();




        float shooter = gamepadEx.gamepad.left_stick_x;


        if(Up){
            flap.setPosition(250.0/300);
            shooterSetSpeed=1600;


        }

        if(Down){
            flap.setPosition(0);
            shooterSetSpeed=1200;


        }
        if (Left){
            flap.setPosition(150.0/300);
            shooterSetSpeed=1350;


        }
        if(Gate){
            door.setPosition(84/300.0);

        }


        double flapPos = flap.getPosition();
        double step = 5 / 300.0;





        m_telemetry.addLine();
        m_telemetry.addData("flap", flapPos);
        m_telemetry.addData("spped",shooterSetSpeed);
        m_telemetry.update();


        flapPos = Math.max(0.0, Math.min(1.0, flapPos));

        flap.setPosition(flapPos);

        if(StopTheAnnoyingSound){
            shooterSetSpeed=0;

        }
        if(OkFine){
            shooterSetSpeed=0;

        }
        if (teleop) {
            intakePower = -1 * signedSquare(swallow) + 1 * signedSquare(out);;
        }

        intake.setPower(intakePower);
    }







    //takes a value and multiplies it by its absolute value, then returns that (square function but keeps the sign)
    private double signedSquare(double x) {
        return x * Math.abs(x);
    }

}



