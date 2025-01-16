package edu.up.isgc.cg.raytracer;

import edu.up.isgc.cg.raytracer.objects.Object3D;

/**
 * Represents an intersection of a ray with an object.
 */
public class Intersection {

    private double distance;
    private Vector3D position;
    private Vector3D normal;
    private Object3D object;

    /**
     * Constructs an Intersection with the given parameters.
     *
     * @param position The position of the intersection
     * @param distance The distance from the ray origin to the intersection
     * @param normal The normal vector at the intersection
     * @param object The object that was intersected
     */
    public Intersection(Vector3D position, double distance, Vector3D normal, Object3D object) {
        setPosition(position);
        setDistance(distance);
        setNormal(normal);
        setObject(object);
    }

    /**
     * Gets the distance from the ray origin to the intersection.
     *
     * @return The distance to the intersection
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the distance from the ray origin to the intersection.
     *
     * @param distance The distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Gets the position of the intersection.
     *
     * @return The position of the intersection
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * Sets the position of the intersection.
     *
     * @param position The position to set
     */
    public void setPosition(Vector3D position) {
        this.position = position;
    }

    /**
     * Gets the normal vector at the intersection.
     *
     * @return The normal vector at the intersection
     */
    public Vector3D getNormal() {
        return normal;
    }

    /**
     * Sets the normal vector at the intersection.
     *
     * @param normal The normal vector to set
     */
    public void setNormal(Vector3D normal) {
        this.normal = normal;
    }

    /**
     * Gets the object that was intersected.
     *
     * @return The intersected object
     */
    public Object3D getObject() {
        return object;
    }

    /**
     * Sets the object that was intersected.
     *
     * @param object The object to set
     */
    public void setObject(Object3D object) {
        this.object = object;
    }
}