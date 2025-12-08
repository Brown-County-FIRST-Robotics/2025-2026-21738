package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShootFinshCommand extends CommandBase {

    ShooterSubsystem m_subsystem;
    Telemetry m_telemetry;
    boolean isFinished =false;

    ElapsedTime m_timer = new ElapsedTime();
   // GamepadEx driverOp = new GamepadEx(gamepad1);
    //GamepadEx toolOp = new GamepadEx(gamepad2);





    public ShootFinshCommand(ShooterSubsystem subsystem, Telemetry telemetry) {
        m_subsystem = subsystem;
        m_telemetry = telemetry;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }
    // The subsystem the command runs on

    @Override
    public void initialize() {
        isFinished =false;
        m_subsystem.kicker.setPosition(85/300.0);
        m_timer.reset();

//        m_telemetry.addLine("IN");
//        m_subsystem.shooter.setVelocity(1200);
//        m_telemetry.update();
    }
    @Override
    public void execute() {
        if (m_timer.seconds() > 1.0) {
            m_subsystem.kicker.setPosition(0);
            m_subsystem.shooter.setPower(0);
            isFinished=true;
        }
    }




    @Override
    public boolean isFinished() {

        return isFinished;

    }

}
