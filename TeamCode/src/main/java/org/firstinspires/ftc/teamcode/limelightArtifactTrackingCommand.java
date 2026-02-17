package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandBase;

public class limelightArtifactTrackingCommand extends CommandBase {
    HardwareMap hmap;
    boolean isFinished;
    public limelightArtifactTrackingCommand(HardwareMap hMap) {
        hmap = hMap;
    }
    @Override
    public void initialize() {
        super.initialize();
        // start up
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
