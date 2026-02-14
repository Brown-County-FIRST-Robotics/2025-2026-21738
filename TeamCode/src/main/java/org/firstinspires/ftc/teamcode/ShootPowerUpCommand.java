package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShootPowerUpCommand extends CommandBase {

    ShooterSubsystem m_subsystem;

    ElapsedTime m_timer = new ElapsedTime();
    Telemetry m_telemetry;

    boolean isFinished =false;
   // GamepadEx driverOp = new GamepadEx(gamepad1);
    //GamepadEx toolOp = new GamepadEx(gamepad2);




    public ShootPowerUpCommand(ShooterSubsystem subsystem, Telemetry telemetry) {
        m_subsystem = subsystem;
        m_telemetry = telemetry;


        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }


    @Override
    public void initialize() {
        isFinished =false;
        m_subsystem.kicker.setPosition(0);
        m_subsystem.shooter.setVelocity(m_subsystem.shooterSetSpeed);
        m_subsystem.intake.setPower(m_subsystem.intakePower);

        m_timer.reset();

    }
    @Override
    public void execute() {
        if (m_subsystem.shooter.getVelocity() >= m_subsystem.shooterSetSpeed) {
         //   m_subsystem.kicker.setPosition(85/300.0);
            m_subsystem.intake.setPower(m_subsystem.intakePower);



//            m_subsystem.shooter.setPower(0);
            isFinished=true;
        }
    }

    @Override
    public boolean isFinished() {

        return isFinished;

    }

}
