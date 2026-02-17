package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.BuildConstents;

@TeleOp(name="Info")
public class Info extends CommandOpMode {
    public void initialize() {
        telemetry.addData("Branch", BuildConstents.GIT_BRANCH);
        telemetry.addData("Hash", BuildConstents.GIT_SHA.substring(0, 8));
        telemetry.addData("Date commited", BuildConstents.GIT_DATE);
        telemetry.addData("Date built", BuildConstents.BUILD_DATE);
        telemetry.addData("Dirty", BuildConstents.DIRTY);
        telemetry.update();
    }
}