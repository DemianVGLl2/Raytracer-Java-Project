package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.objects.Object3D;

import java.awt.*;

/**
 * Abstract class representing a light source in the scene.
 */
public abstract class Light extends Object3D {
    private double intensity;

    /**
     * Constructs a light source with the given parameters.
     *
     * @param position  The position of the light source.
     * @param color     The color of the light source.
     * @param intensity The intensity of the light source.
     */
    public Light(Vector3D position, Color color, double intensity) {
        super(position, color, 0.0, 0.0, 0.0);
        setIntensity(intensity);
    }

    /**
     * Gets the intensity of the light source.
     *
     * @return The intensity of the light source.
     */
    public double getIntensity() {
        return intensity;
    }

    /**
     * Sets the intensity of the light source.
     *
     * @param intensity The intensity to set.
     */
    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    /**
     * Calculates and returns the dot product between the normal at the intersection and the direction
     * of the light source.
     *
     * @param intersection The intersection point.
     * @return The dot product between the normal and the light direction.
     */
    public abstract double getNDotL(Intersection intersection);

    /**
     * Overrides the Object3D method to return a default intersection for lights.
     *
     * @param ray The ray to intersect with.
     * @return A default Intersection object indicating no intersection.
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }
}
