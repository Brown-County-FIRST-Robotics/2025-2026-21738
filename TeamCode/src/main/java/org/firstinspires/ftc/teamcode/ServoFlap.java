package org.firstinspires.ftc.teamcode;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ServoFlap extends CommandBase {

    ShooterSubsystem m_subsystem;
    Telemetry m_telemetry;
    boolean isFinished =false;
    // GamepadEx driverOp = new GamepadEx(gamepad1);
    //GamepadEx toolOp = new GamepadEx(gamepad2);





    public ServoFlap(ShooterSubsystem subsystem, Telemetry telemetry) {
        m_subsystem = subsystem;
        m_telemetry = telemetry;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }
    // The subsystem the command runs on

    @Override
    public void initialize() {
        isFinished =false;
       // m_subsystem.flap.setPosition(0);
        m_telemetry.addData("flap", m_subsystem.flap.getPosition());
        m_telemetry.update();
    }
    @Override
    public void execute() {

        }

    @Override
    public boolean isFinished() {

        return isFinished;

    }
}






