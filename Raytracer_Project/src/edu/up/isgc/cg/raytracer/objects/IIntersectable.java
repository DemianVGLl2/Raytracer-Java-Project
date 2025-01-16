package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;

/**
 * Interface for objects that can be intersected by a ray.
 */
public interface IIntersectable {
    /**
     * Gets the intersection of the object with a specified ray.
     *
     * @param ray The ray to intersect with the object
     * @return The intersection of the ray with the object
     */
    Intersection getIntersection(Ray ray);
}