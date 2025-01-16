package edu.up.isgc.cg.raytracer;

/**
 * Represents a 3D vector with x, y, and z components.
 */
public class Vector3D {
    private static final Vector3D ZERO = new Vector3D(0.0, 0.0, 0.0);
    private double x, y, z;

    /**
     * Constructs a Vector3D with the given x, y, and z components.
     *
     * @param x The x component
     * @param y The y component
     * @param z The z component
     */
    public Vector3D(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * Gets the x component of the vector.
     *
     * @return The x component
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x component of the vector.
     *
     * @param x The x component to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y component of the vector.
     *
     * @return The y component
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y component of the vector.
     *
     * @param y The y component to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the z component of the vector.
     *
     * @return The z component
     */
    public double getZ() {
        return z;
    }

    /**
     * Sets the z component of the vector.
     *
     * @param z The z component to set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Returns a clone of this vector.
     *
     * @return A new Vector3D that is a clone of this vector
     */
    public Vector3D clone(){
        return new Vector3D(getX(), getY(), getZ());
    }

    /**
     * Returns a zero vector.
     *
     * @return A zero vector
     */
    public static Vector3D ZERO(){
        return ZERO.clone();
    }

    @Override
    public String toString(){
        return "Vector3D{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", z=" + getZ() +
                "}";
    }

    /**
     * Calculates the dot product of two vectors.
     *
     * @param vectorA The first vector
     * @param vectorB The second vector
     * @return The dot product of the two vectors
     */
    public static double dotProduct(Vector3D vectorA, Vector3D vectorB){
        return (vectorA.getX() * vectorB.getX()) + (vectorA.getY() * vectorB.getY()) + (vectorA.getZ() * vectorB.getZ());
    }

    /**
     * Calculates the cross product of two vectors.
     *
     * @param vectorA The first vector
     * @param vectorB The second vector
     * @return A new Vector3D that is the cross product of the two vectors
     */
    public static Vector3D crossProduct(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D((vectorA.getY() * vectorB.getZ()) - (vectorA.getZ() * vectorB.getY()),
                (vectorA.getZ() * vectorB.getX()) - (vectorA.getX() * vectorB.getZ()),
                (vectorA.getX() * vectorB.getY()) - (vectorA.getY() * vectorB.getX()));
    }

    /**
     * Calculates the magnitude (length) of a vector.
     *
     * @param vectorA The vector to calculate the magnitude for
     * @return The magnitude of the vector
     */
    public static double magnitude (Vector3D vectorA){
        return Math.sqrt(dotProduct(vectorA, vectorA));
    }

    /**
     * Adds two vectors together.
     *
     * @param vectorA The first vector
     * @param vectorB The second vector
     * @return A new Vector3D that is the sum of the two vectors
     */
    public static Vector3D add(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY(), vectorA.getZ() + vectorB.getZ());
    }

    /**
     * Subtracts one vector from another.
     *
     * @param vectorA The vector to subtract from
     * @param vectorB The vector to subtract
     * @return A new Vector3D that is the difference of the two vectors
     */
    public static Vector3D substract(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D(vectorA.getX() - vectorB.getX(), vectorA.getY() - vectorB.getY(), vectorA.getZ() - vectorB.getZ());
    }

    /**
     * Normalizes a vector to have a magnitude of 1.
     *
     * @param vectorA The vector to normalize
     * @return A new Vector3D that is the normalized vector
     */
    public static Vector3D normalize(Vector3D vectorA){
        double mag = Vector3D.magnitude(vectorA);
        return new Vector3D(vectorA.getX() / mag, vectorA.getY() / mag, vectorA.getZ() / mag);
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param vectorA The vector to multiply
     * @param scalar The scalar to multiply by
     * @return A new Vector3D that is the result of the multiplication
     */
    public static Vector3D scalarMultiplication(Vector3D vectorA, double scalar){
        return new Vector3D(vectorA.getX() * scalar, vectorA.getY() * scalar, vectorA.getZ() * scalar);
    }
}
