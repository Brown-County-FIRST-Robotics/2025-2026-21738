package org.firstinspires.ftc.teamcode.auto;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.telemetry.SelectableOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Configurable
@Autonomous(name = "Auto Menu", group = "Pedro Pathing")
public class menu extends SelectableOpMode {
    public menu() {
        super("Select auto plan", s -> {
            s.folder("Red", l -> {
            });
            s.folder("Blue", a -> {
//                a.add("1A", auto1A::new);
                a.add("1B", blue1B::new);
                a.add("1C", blue1C::new);
                a.add("2A", blue2A::new);
                a.add("2B", blue2B::new);
                a.add("2C", blue2C::new);
            });
        });
    }
}