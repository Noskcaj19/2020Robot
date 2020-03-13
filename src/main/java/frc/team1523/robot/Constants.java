package frc.team1523.robot;

// Define constants here as `static final`s
public final class Constants {
    public static final class IntakeConstants {
        public static final int kWristRange = 175;
    }

    public static final class LiftConstants {
        public static final int kLiftMax = 2125;
    }

    public static final class ShooterConstants {
        public static final double kP = .012;
        public static final double kI = 0.001320;
        public static final double kD = .00112;

        public static final double kShooterFreeRPS = 5600;

        public static final double kSVolts = 0.147;
        public static final double kVVoltSecondsPerRotation =
                // Should have value 12V at free speed...
                12.0 / kShooterFreeRPS;

        // The minimum speed of the flywheel before we start loading balls
    }

    public static final class LimelightConstants {
        //measurements in inches
        public static final int kPortHeight = 24;
        public static final int kLimelightHeight = 28;
        public static final int kLimelightAngle = 0;
        public static final int kLimelightOffset = 0;
    }

    public static final class DriveConstants {
        public static final double kMaxSpeed = 4.0; // Meters per second
        public static final double kMaxAngularSpeed = 2 * Math.PI; // One rotation per second


        public static final double kGearRatio = 10.71;
        // Meters (6 inch/2)
        public static final double kWheelRadius = 0.1524 / 2;
        public static final double kEncoderResolution = 2048;
        public static final double kDistancePerTick = (2 * Math.PI * kWheelRadius / kEncoderResolution) / kGearRatio;
        // Meters
        public static final double kTrackWidth = .6477 * 2;
    }
}
