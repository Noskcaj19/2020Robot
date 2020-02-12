/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team1523.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.team1523.robot.Constants.LimelightConstants.*;

public class Limelight extends SubsystemBase {
    // Create a network table for the limelight
    private final NetworkTable m_limelightTable;
    // Create variables for the different values given from the limelight
    private double xOffset; // Positive values mean that target is to the right of the camera; negative
    // values mean target is to the left. Measured in degrees
    private double yOffset; // Positive values mean that target is above the camera; negative values mean
    // target is below. Measured in degrees
    private double targetArea; // Returns a value of the percentage of the image the target takes
    private double targetValue; // Sends 1 if a target is detected, 0 if none are present

    public Limelight() {
        // Gets the network table for the limelight
        m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

        // Reset the default settings and pipelines to the Limelight
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
    }

    /**
     * Returns a value of the offset on the x-axis of the camera to the target in
     * degrees. Negative values mean the target is to the left of the camera
     */
    public double getXOffset() {
        return xOffset;
    }

    /**
     * Returns true if a target is detected
     */
    public boolean isTargetDetected() {
        return (targetValue > 0.0);
    }

    /**
     * Returns true if the target is within a range of the center crosshair of the
     * camera
     */
    public boolean isTargetCentered() {
        return ((xOffset > -1.5) && (xOffset < 1.5) && (xOffset != 0.0));
    }

    /**
     * Calculates the total angle by adding the mounting angle with the y-axis
     * offset angle of the limelight in degrees
     */
    public double limelightAngle() {
        return (kLimelightAngle + yOffset);
    }

    /**
     * Return the distance from the limelight to the target in inches (floor
     * distance)
     */
    public double limelightDistance() {
        return (kPortHeight - kLimelightHeight) / Math.tan(Math.toRadians(kLimelightAngle + yOffset) + kLimelightOffset);
    }

    /**
     * Returns the value of the pipeline from the network table
     *
     * @return pipelineValue
     */
    public double getPipeline() {
        NetworkTableEntry pipeline = m_limelightTable.getEntry("Pipeline");
        return pipeline.getDouble(0.0);
    }

    /**
     * Chooses which pipeline to use on the limelight and prevents invalid values
     * from being sent
     *
     * @param pipeline Which pipeline to use on the limelight (0-9)
     */
    public void setPipeline(int pipeline) {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
        m_limelightTable.getEntry("Pipeline").setValue(pipeline);
        if (pipeline < 0) {
            pipeline = 0;
        } else if (pipeline > 9) {
            pipeline = 9;
        }
    }

    public void updateLimelight() {
        // Updates the values of the limelight on the network table
        xOffset = m_limelightTable.getEntry("tx").getDouble(0.0);
        yOffset = m_limelightTable.getEntry("ty").getDouble(0.0);
        targetArea = m_limelightTable.getEntry("ta").getDouble(0.0);
        targetValue = m_limelightTable.getEntry("tv").getDouble(0.0);

    }

    public void log() {
        // Updates the SmartDashboard with limelight values
        Shuffleboard.getTab("Limelight data").add("LimelightXOffset", xOffset);
        Shuffleboard.getTab("Limelight data").add("LimelightYOffset", yOffset);

        Shuffleboard.getTab("Limelight data").add("LimelightAreaPercentage", targetArea);
        Shuffleboard.getTab("Limelight data").add("Target Centered", isTargetCentered());
        Shuffleboard.getTab("Limelight data").add("Target Detected", isTargetDetected());
        Shuffleboard.getTab("Limelight data").add("Distance (INCHES)", limelightDistance());
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        updateLimelight();
        log();
    }
}