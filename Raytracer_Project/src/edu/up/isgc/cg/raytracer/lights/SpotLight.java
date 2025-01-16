package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;
/**
 * Represents a spotlight source in the scene.
 */
public class SpotLight extends Light {
    private Vector3D direction;
    private double angle;

    /**
     * Constructs a spotlight source with the given parameters.
     *
     * @param position  The position of the light source.
     * @param direction The direction of the light source.
     * @param color     The color of the light source.
     * @param intensity The intensity of the light source.
     * @param angle     The angle of the spotlight.
     */
    public SpotLight(Vector3D position, Vector3D direction, Color color, double intensity, double angle) {
        super(position, color, intensity);
        setDirection(direction);
        setAngle(angle);
    }

    /**
     * Gets the direction of the spotlight.
     *
     * @return The direction of the spotlight.
     */
    public Vector3D getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the spotlight.
     *
     * @param direction The direction to set.
     */
    public void setDirection(Vector3D direction) {
        this.direction = Vector3D.normalize(direction);
    }

    /**
     * Gets the angle of the spotlight.
     *
     * @return The angle of the spotlight.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Sets the angle of the spotlight.
     *
     * @param angle The angle to set.
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Calculates and returns the dot product between the normal at the intersection and the direction
     * of the light source.
     *
     * @param intersection The intersection point.
     * @return The dot product between the normal and the light direction.
     */
    @Override
    public double getNDotL(Intersection intersection) {
        Vector3D lightDirection = Vector3D.normalize(Vector3D.substract(intersection.getPosition(), getPosition()));
        double angleBetween = Math.toDegrees(Math.acos(-Vector3D.dotProduct(getDirection(), lightDirection)));
        double outerAngle = getAngle()*1.5;

        if (angleBetween >= outerAngle) {
            double distance = Vector3D.magnitude(Vector3D.substract(intersection.getPosition(), getPosition()));
            double attenuation = 1.0 / (0.7 * distance);

            if (angleBetween <= getAngle()) {
                return Math.max(Vector3D.dotProduct(intersection.getNormal(), lightDirection) * attenuation, 0.0);
            } else {
                double lightFallOff = (angleBetween - outerAngle) / (getAngle() - outerAngle);
                return Math.max(Vector3D.dotProduct(intersection.getNormal(), lightDirection) * lightFallOff * attenuation, 0.0);
            }
        }
        return 0.0;
    }
}
