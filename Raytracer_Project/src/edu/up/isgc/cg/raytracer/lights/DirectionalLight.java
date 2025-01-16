package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;
/**
 * Represents a directional light source in the scene.
 */
public class DirectionalLight extends Light {
    private Vector3D direction;

    /**
     * Constructs a directional light source with the given parameters.
     *
     * @param direction The direction of the light source.
     * @param color     The color of the light source.
     * @param intensity The intensity of the light source.
     */
    public DirectionalLight(Vector3D direction, Color color, double intensity) {
        super(Vector3D.ZERO(), color, intensity);
        setDirection(direction);
    }

    /**
     * Gets the direction of the light source.
     *
     * @return The direction of the light source.
     */
    public Vector3D getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the light source.
     *
     * @param direction The direction to set.
     */
    public void setDirection(Vector3D direction) {
        this.direction = Vector3D.normalize(direction);
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
        return Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.scalarMultiplication(getDirection(),-1.0)), 0.0);
    }
}
