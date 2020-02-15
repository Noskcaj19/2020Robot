package frc.team1523.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Shooter extends SubsystemBase {
    private final CANSparkMax shooterMotor = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);

    public void setMotorSpeed(double speed){
        shooterMotor.set(speed * 0.5);
    }


}