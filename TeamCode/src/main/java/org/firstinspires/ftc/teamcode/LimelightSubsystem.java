package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public  class LimelightSubsystem extends SubsystemBase {

    Telemetry m_telemetry;

    public Servo limeLightServo;
    public Limelight3A limeLight;

    public LLResult data;


    public LimelightSubsystem(HardwareMap hMap, Telemetry telemetry) {
        m_telemetry = telemetry;
        limeLight = hMap.get(Limelight3A.class, "limelight");
        limeLightServo = hMap.get(Servo.class, "limeservo");

        limeLight.start();
    }

    @Override
    public void periodic() {
        data = limeLight.getLatestResult();
    }

    public void teleop() {
        limeLightServo.setPosition(30.0/300);
        limeLight.pipelineSwitch(0);
    }
    public void auto() {
        limeLightServo.setPosition(0.0/300);
        limeLight.pipelineSwitch(1);
    }
}

