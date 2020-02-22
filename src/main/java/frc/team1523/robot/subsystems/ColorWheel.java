package frc.team1523.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheel extends SubsystemBase {
    Spark extend = new Spark(0);
    Spark spinyWheel = new Spark(1);

    public void setExtendSpeed(double speed) {
        extend.set(speed);
    }

    public void setSpinySpeed(double speed) {
        spinyWheel.set(speed);
    }

}
