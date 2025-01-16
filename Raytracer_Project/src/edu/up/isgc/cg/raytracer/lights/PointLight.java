package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

/**
 * Represents a point light source in the scene.
 */
public class PointLight extends Light {

    /**
     * Constructs a point light source with the given parameters.
     *
     * @param position  The position of the light source.
     * @param color     The color of the light source.
     * @param intensity The intensity of the light source.
     */
    public PointLight(Vector3D position, Color color, double intensity) {
        super(position, color, intensity);
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
        return Math.max(
                Vector3D.dotProduct(intersection.getNormal(),
                        Vector3D.normalize(Vector3D.substract(getPosition(), intersection.getPosition()))), 0.0);
    }
}
