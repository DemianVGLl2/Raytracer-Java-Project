package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.tools.Barycentric;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a 3D model composed of multiple triangles.
 */
public class Model3D extends Object3D {
    private List<Triangle> triangles;

    /**
     * Constructs a 3D model with specified position, triangles, color, shininess, reflectivity, and refraction.
     *
     * @param position    The position of the model
     * @param triangles   The triangles composing the model
     * @param color       The color of the model
     * @param shininess   The shininess of the model
     * @param reflectivity The reflectivity of the model
     * @param refraction  The refraction index of the model
     */
    public Model3D(Vector3D position, Triangle[] triangles, Color color, double shininess, double reflectivity, double refraction) {
        super(position, color, shininess, reflectivity, refraction);
        setTriangles(triangles);
    }

    /**
     * Gets the triangles composing the model.
     *
     * @return The list of triangles
     */
    public List<Triangle> getTriangles() {
        return triangles;
    }

    /**
     * Sets the triangles composing the model.
     *
     * @param triangles The triangles to set
     */
    public void setTriangles(Triangle[] triangles) {
        Vector3D position = getPosition();
        Set<Vector3D> uniqueVertices = new HashSet<>();
        for (Triangle triangle : triangles) {
            uniqueVertices.addAll(Arrays.asList(triangle.getVertices()));
        }

        for (Vector3D vertex : uniqueVertices) {
            vertex.setX(vertex.getX() + position.getX());
            vertex.setY(vertex.getY() + position.getY());
            vertex.setZ(vertex.getZ() + position.getZ());
        }
        this.triangles = Arrays.asList(triangles);
    }

    @Override
    public Intersection getIntersection(Ray ray) {
        double distance = -1;
        Vector3D position = Vector3D.ZERO();
        Vector3D normal = Vector3D.ZERO();

        for (Triangle triangle : getTriangles()) {
            Intersection intersection = triangle.getIntersection(ray);
            double intersectionDistance = intersection.getDistance();
            if (intersectionDistance > 0 &&
                    (intersectionDistance < distance || distance < 0)) {
                distance = intersectionDistance;
                position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
                normal = Vector3D.ZERO();
                double[] uvw = Barycentric.CalculateBarycentricCoordinates(position, triangle);
                Vector3D[] normals = triangle.getNormals();
                for (int i = 0; i < uvw.length; i++) {
                    normal = Vector3D.add(normal, Vector3D.scalarMultiplication(normals[i], uvw[i]));
                }
            }
        }

        if (distance == -1) {
            return null;
        }

        return new Intersection(position, distance, normal, this);
    }
}