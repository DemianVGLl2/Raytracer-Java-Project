package edu.up.isgc.cg.raytracer;

/**
 * Represents a ray with an origin and a direction.
 */
public class Ray {
    private Vector3D origin;
    private Vector3D direction;

    /**
     * Constructs a Ray with the given origin and direction.
     *
     * @param origin The origin of the ray
     * @param direction The direction of the ray
     */
    public Ray(Vector3D origin, Vector3D direction) {
        setOrigin(origin);
        setDirection(direction);
    }

    /**
     * Gets the origin of the ray.
     *
     * @return The origin of the ray
     */
    public Vector3D getOrigin() {
        return origin;
    }
    /**
     * Sets the origin of the ray.
     *
     * @param origin The origin to set
     */
    public void setOrigin(Vector3D origin) {
        this.origin = origin;
    }

    /**
     * Gets the normalized direction of the ray.
     *
     * @return The normalized direction of the ray
     */
    public Vector3D getDirection() {
        return Vector3D.normalize(direction);
    }

    /**
     * Sets the direction of the ray.
     *
     * @param direction The direction to set
     */
    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }
}
