package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterSpeed extends CommandBase {

    ShooterSubsystem m_subsystem;
    Telemetry m_telemetry;
    ShooterSpeed m_ShooterSpeed;
   // shooterSetSpeed m_ShooterSetSpeed;

    boolean isFinished =false;
    // GamepadEx driverOp = new GamepadEx(gamepad1);
    //GamepadEx toolOp = new GamepadEx(gamepad2);





    public ShooterSpeed(ShooterSubsystem subsystem,/* shooterSetSpeed ShooterSetSpeed,*/ Telemetry telemetry, ShooterSpeed shooterSpeed, GamepadEx gamepadEx, HardwareMap hardwareMap) {
        m_subsystem = subsystem;
        m_telemetry = telemetry;
        m_ShooterSpeed = shooterSpeed;
       // m_ShooterSetSpeed = shooterSetSpeed;


        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }
    // The subsystem the command runs on

    @Override
    public void initialize() {
        isFinished =false;

    }
    @Override
    public void execute() {
        m_subsystem.shooterSetSpeed = 0;
            //   m_subsystem.kicker.setPosition(85/300.0);

//            m_subsystem.shooter.setPower(0);
            isFinished=true;

        }

    @Override
    public boolean isFinished() {


        return isFinished;

    }
}


