package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public  class DrivebaseSubsystem extends SubsystemBase {

    Telemetry m_telemetry;

    private ElapsedTime runtime = new ElapsedTime();

    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;
    Servo door = null;
    GamepadEx gamepadEx;
    public boolean teleop;

    GamepadEx gamepadEx2;
    ElapsedTime m_timer = new ElapsedTime();
    double Time = 0;
    boolean shaker;


    public DrivebaseSubsystem(GamepadEx gamepadEx, final HardwareMap hMap) {
        this.gamepadEx = gamepadEx;
        teleop = true;
        shaker = gamepadEx.gamepad.y;


        frontLeft = hMap.get(DcMotor.class, "frontLeft");
        frontRight = hMap.get(DcMotor.class, "frontRight");
        backLeft = hMap.get(DcMotor.class, "backLeft");
        backRight = hMap.get(DcMotor.class, "backRight");





        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);


    }


    @Override
    public void periodic() {
        // Setup a variable for each drive wheel to save power level for telemetry




        double axial   =  this.gamepadEx.getLeftY();
        double lateral =  this.gamepadEx.getLeftX();
        double yaw     =  this.gamepadEx.getRightX();
        boolean TheFitnessGramPacerTestIsAMultistageAerobicCapacityTestThatProgressivelyGetsMoreDifficultAsItContinuesThe20MeterPacerTestWillBeginIn30SecondsLineUpAtTheStartTheRunningSpeedStartsSlowlyButGetsFasterEachMinuteAfterYouHearThisSignalBeepASingleLapShouldBeCompletedEachTimeYouHearThisSoundDINGRememberToRunInAStraightLineAndRunAsLongAsPossibleTheSecondTimeYouFailCoCompleteALapBeforeTheSoundYourTestIsOverTheTestWillBeginOnTheWordStartOnYourMarkGetReadyStart = gamepadEx.gamepad.left_bumper;;
        boolean OkTheFitnessGramPacerTestIsAMultistageAerobicCapacityTestThatProgressivelyGetsMoreDifficultAsItContinuesThe20MeterPacerTestWillBeginIn30SecondsLineUpAtTheStartTheRunningSpeedStartsSlowlyButGetsFasterEachMinuteAfterYouHearThisSignalBeepASingleLapShouldBeCompletedEachTimeYouHearThisSoundDINGRememberToRunInAStraightLineAndRunAsLongAsPossibleTheSecondTimeYouFailCoCompleteALapBeforeTheSoundYourTestIsOverTheTestWillBeginOnTheWordStartOnYourMarkGetReadyStart = gamepadEx.gamepad.right_bumper;;
        double go;
        go=0;
        boolean GetReady = gamepadEx.gamepad.dpad_down;
        boolean GiveUp = gamepadEx.gamepad.b;



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

        if(GetReady){
            go = 1;
        }

        if(go==1) {

            if (TheFitnessGramPacerTestIsAMultistageAerobicCapacityTestThatProgressivelyGetsMoreDifficultAsItContinuesThe20MeterPacerTestWillBeginIn30SecondsLineUpAtTheStartTheRunningSpeedStartsSlowlyButGetsFasterEachMinuteAfterYouHearThisSignalBeepASingleLapShouldBeCompletedEachTimeYouHearThisSoundDINGRememberToRunInAStraightLineAndRunAsLongAsPossibleTheSecondTimeYouFailCoCompleteALapBeforeTheSoundYourTestIsOverTheTestWillBeginOnTheWordStartOnYourMarkGetReadyStart) {
                frontLeft.setPower(1000);
                backLeft.setPower(1000);
                backRight.setPower(1000);
                backLeft.setPower(1000);
                m_timer.reset();
            }
            if (OkTheFitnessGramPacerTestIsAMultistageAerobicCapacityTestThatProgressivelyGetsMoreDifficultAsItContinuesThe20MeterPacerTestWillBeginIn30SecondsLineUpAtTheStartTheRunningSpeedStartsSlowlyButGetsFasterEachMinuteAfterYouHearThisSignalBeepASingleLapShouldBeCompletedEachTimeYouHearThisSoundDINGRememberToRunInAStraightLineAndRunAsLongAsPossibleTheSecondTimeYouFailCoCompleteALapBeforeTheSoundYourTestIsOverTheTestWillBeginOnTheWordStartOnYourMarkGetReadyStart) {
                frontLeft.setPower(-1000);
                backLeft.setPower(-1000);
                backRight.setPower(-1000);
                backLeft.setPower(-1000);
                m_timer.reset();
            }

            if (GiveUp){
                frontLeft.setPower(frontLeftPower);
                frontRight.setPower(frontRightPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(backRightPower);
            }
            if (go == 1) {

                if (Time == 1000) {
                    go = 2;
                }
            }
        }




        if (teleop) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        }

        


    }







   private double signedSquare(double x) {
        return x * Math.abs(x);
    }

}



