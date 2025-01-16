package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

/**
 * Represents a spherical 3D object.
 */
public class Sphere extends Object3D {
    private double radius;

    /**
     * Constructs a Sphere with specified position, radius, color, shininess, reflectivity, and refraction.
     *
     * @param position    The position of the sphere
     * @param radius      The radius of the sphere
     * @param color       The color of the sphere
     * @param shininess   The shininess of the sphere
     * @param reflectivity The reflectivity of the sphere
     * @param refraction  The refraction index of the sphere
     */
    public Sphere(Vector3D position, double radius, Color color, double shininess, double reflectivity, double refraction) {
        super(position, color, shininess, reflectivity, refraction);
        setRadius(radius);
    }

    /**
     * Gets the radius of the sphere.
     *
     * @return The radius of the sphere
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the sphere.
     *
     * @param radius The radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Intersection getIntersection(Ray ray) {
        Vector3D L = Vector3D.substract(getPosition(), ray.getOrigin());
        double tca = Vector3D.dotProduct(L, ray.getDirection());
        double L2 = Math.pow(Vector3D.magnitude(L), 2);
        double d2 = L2 - Math.pow(tca, 2);
        if (d2 >= 0) {
            double d = Math.sqrt(d2);
            double t0 = tca - Math.sqrt(Math.pow(getRadius(), 2) - Math.pow(d, 2));
            double t1 = tca + Math.sqrt(Math.pow(getRadius(), 2) - Math.pow(d, 2));

            double distance = Math.min(t0, t1);
            Vector3D position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
            Vector3D normal = Vector3D.normalize(Vector3D.substract(position, getPosition()));
            return new Intersection(position, distance, normal, this);
        }

        return null;
    }
}
