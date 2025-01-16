package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

import javax.management.ValueExp;

/**
 * Represents a triangular 3D object.
 */
public class Triangle implements IIntersectable {
    public static final double EPSILON = 0.0000000000001;
    private Vector3D[] vertices;
    private Vector3D[] normals;

    /**
     * Constructs a Triangle with specified vertices.
     *
     * @param v0 The first vertex
     * @param v1 The second vertex
     * @param v2 The third vertex
     */
    public Triangle(Vector3D v0, Vector3D v1, Vector3D v2) {
        setVertices(v0, v1, v2);
        setNormals(null);
    }

    /**
     * Constructs a Triangle with specified vertices and normals.
     *
     * @param vertices The vertices of the triangle
     * @param normals  The normals of the triangle
     */
    public Triangle(Vector3D[] vertices, Vector3D[] normals) {
        if (vertices.length == 3) {
            setVertices(vertices[0], vertices[1], vertices[2]);
        } else {
            setVertices(Vector3D.ZERO(), Vector3D.ZERO(), Vector3D.ZERO());
        }
        setNormals(normals);
    }

    /**
     * Gets the vertices of the triangle.
     *
     * @return The vertices of the triangle
     */
    public Vector3D[] getVertices() {
        return vertices;
    }

    /**
     * Sets the vertices of the triangle.
     *
     * @param vertices The vertices to set
     */
    private void setVertices(Vector3D[] vertices) {
        this.vertices = vertices;
    }

    /**
     * Sets the vertices of the triangle.
     *
     * @param v0 The first vertex
     * @param v1 The second vertex
     * @param v2 The third vertex
     */
    public void setVertices(Vector3D v0, Vector3D v1, Vector3D v2) {
        setVertices(new Vector3D[]{v0, v1, v2});
    }

    /**
     * Gets the normal of the triangle.
     *
     * @return The normal of the triangle
     */
    public Vector3D getNormal() {
        Vector3D normal = Vector3D.ZERO();
        Vector3D[] normals = this.normals;

        if (normals == null) {
            Vector3D[] vertices = getVertices();
            Vector3D v = Vector3D.substract(vertices[1], vertices[0]);
            Vector3D w = Vector3D.substract(vertices[0], vertices[2]);
            normal = Vector3D.normalize(Vector3D.crossProduct(v, w));
        } else {
            for (int i = 0; i < normals.length; i++) {
                normal.setX(normal.getX() + normals[i].getX());
                normal.setY(normal.getY() + normals[i].getY());
                normal.setZ(normal.getZ() + normals[i].getZ());
            }
        }
        return normal;
    }

    /**
     * Gets the normals of the triangle.
     *
     * @return The normals of the triangle
     */
    public Vector3D[] getNormals() {
        if (normals == null) {
            Vector3D normal = getNormal();
            setNormals(new Vector3D[]{normal, normal, normal});
        }

        return normals;
    }

    /**
     * Sets the normals of the triangle.
     *
     * @param normals The normals to set
     */
    private void setNormals(Vector3D[] normals) {
        this.normals = normals;
    }

    /**
     * Sets the normals of the triangle.
     *
     * @param n0 The first normal
     * @param n1 The second normal
     * @param n2 The third normal
     */
    public void setNormals(Vector3D n0, Vector3D n1, Vector3D n2) {
        setNormals(new Vector3D[]{n0, n1, n2});
    }

    @Override
    public Intersection getIntersection(Ray ray) {
        Intersection intersection = new Intersection(null, -1, null, null);

        Vector3D[] vert = getVertices();
        Vector3D v2v0 = Vector3D.substract(vert[2], vert[0]);
        Vector3D v1v0 = Vector3D.substract(vert[1], vert[0]);
        Vector3D vectorP = Vector3D.crossProduct(ray.getDirection(), v1v0);
        double det = Vector3D.dotProduct(v2v0, vectorP);
        double invDet = 1.0 / det;
        Vector3D vectorT = Vector3D.substract(ray.getOrigin(), vert[0]);
        double u = invDet * Vector3D.dotProduct(vectorT, vectorP);

        if (!(u < 0 || u > 1)) {
            Vector3D vectorQ = Vector3D.crossProduct(vectorT, v2v0);
            double v = invDet * Vector3D.dotProduct(ray.getDirection(), vectorQ);
            if (!(v < 0 || (u + v) > (1.0 + EPSILON))) {
                double t = invDet * Vector3D.dotProduct(vectorQ, v1v0);
                intersection.setDistance(t);
            }
        }

        return intersection;
    }
}
