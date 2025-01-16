package edu.up.isgc.cg.raytracer.tools;

/**
 * Utility class for clamping a value within a specified range.
 */
public class Clamp {
    /**
     * Clamps a value within a specified range.
     *
     * @param value The value to clamp.
     * @param min   The minimum value of the range.
     * @param max   The maximum value of the range.
     * @return The clamped value.
     */
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
