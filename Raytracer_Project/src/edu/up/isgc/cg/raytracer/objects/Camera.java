package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

/**
 * Represents a camera in a 3D scene.
 */
public class Camera extends Object3D {
    private double[] fieldOfView = new double[2];
    private double defaultZ = 15.0;
    private int[] resolution = new int[2];
    private double[] nearFarPlanes = new double[2];

    /**
     * Constructs a Camera with specified position, field of view, resolution, and near and far planes.
     *
     * @param position  The position of the camera
     * @param fovH      The horizontal field of view
     * @param fovV      The vertical field of view
     * @param width     The width resolution of the camera
     * @param height    The height resolution of the camera
     * @param nearPlane The near plane distance
     * @param farPlane  The far plane distance
     */
    public Camera(Vector3D position, double fovH, double fovV, int width, int height, double nearPlane, double farPlane) {
        super(position, Color.BLACK, 0.0, 0.0, 0.0);
        setFOV(fovH, fovV);
        setResolution(width, height);
        setNearFarPlanes(new double[]{nearPlane, farPlane});
    }

    /**
     * Gets the field of view of the camera.
     *
     * @return The field of view as an array [horizontal, vertical]
     */
    public double[] getFieldOfView() {
        return fieldOfView;
    }

    /**
     * Sets the field of view of the camera.
     *
     * @param fieldOfView The field of view to set
     */
    private void setFieldOfView(double[] fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    /**
     * Gets the horizontal field of view of the camera.
     *
     * @return The horizontal field of view
     */
    public double getFOVHorizontal() {
        return fieldOfView[0];
    }

    /**
     * Gets the vertical field of view of the camera.
     *
     * @return The vertical field of view
     */
    public double getFOVVertical() {
        return fieldOfView[1];
    }

    /**
     * Sets the horizontal field of view of the camera.
     *
     * @param fovH The horizontal field of view to set
     */
    public void setFOVHorizontal(double fovH) {
        fieldOfView[0] = fovH;
    }

    /**
     * Sets the vertical field of view of the camera.
     *
     * @param fovV The vertical field of view to set
     */
    public void setFOVVertical(double fovV) {
        fieldOfView[1] = fovV;
    }

    /**
     * Sets the field of view of the camera.
     *
     * @param fovH The horizontal field of view to set
     * @param fovV The vertical field of view to set
     */
    public void setFOV(double fovH, double fovV) {
        setFOVHorizontal(fovH);
        setFOVVertical(fovV);
    }

    /**
     * Gets the default Z coordinate for the camera.
     *
     * @return The default Z coordinate
     */
    public double getDefaultZ() {
        return defaultZ;
    }

    /**
     * Sets the default Z coordinate for the camera.
     *
     * @param defaultZ The default Z coordinate to set
     */
    public void setDefaultZ(double defaultZ) {
        this.defaultZ = defaultZ;
    }

    /**
     * Gets the resolution of the camera.
     *
     * @return The resolution as an array [width, height]
     */
    public int[] getResolution() {
        return resolution;
    }

    /**
     * Sets the width resolution of the camera.
     *
     * @param width The width resolution to set
     */
    public void setResolutionWidth(int width) {
        resolution[0] = width;
    }

    /**
     * Sets the height resolution of the camera.
     *
     * @param height The height resolution to set
     */
    public void setResolutionHeight(int height) {
        resolution[1] = height;
    }

    /**
     * Sets the resolution of the camera.
     *
     * @param width  The width resolution to set
     * @param height The height resolution to set
     */
    public void setResolution(int width, int height) {
        setResolutionWidth(width);
        setResolutionHeight(height);
    }

    /**
     * Gets the width resolution of the camera.
     *
     * @return The width resolution
     */
    public int getResolutionWidth() {
        return resolution[0];
    }

    /**
     * Gets the height resolution of the camera.
     *
     * @return The height resolution
     */
    public int getResolutionHeight() {
        return resolution[1];
    }

    /**
     * Sets the resolution of the camera.
     *
     * @param resolution The resolution to set
     */
    private void setResolution(int[] resolution) {
        this.resolution = resolution;
    }

    /**
     * Gets the near and far planes of the camera.
     *
     * @return The near and far planes as an array [near, far]
     */
    public double[] getNearFarPlanes() {
        return nearFarPlanes;
    }

    /**
     * Sets the near and far planes of the camera.
     *
     * @param nearFarPlanes The near and far planes to set
     */
    private void setNearFarPlanes(double[] nearFarPlanes) {
        this.nearFarPlanes = nearFarPlanes;
    }

    /**
     * Calculates the positions for rays to be traced from the camera.
     *
     * @return A 2D array of positions for rays
     */
    public Vector3D[][] calculatePositionsToRay() {
        double angleMaxX = getFOVHorizontal() / 2.0;
        double radiusMaxX = getDefaultZ() / Math.cos(Math.toRadians(angleMaxX));

        double maxX = Math.sin(Math.toRadians(angleMaxX)) * radiusMaxX;
        double minX = -maxX;

        double angleMaxY = getFOVVertical() / 2.0;
        double radiusMaxY = getDefaultZ() / Math.cos(Math.toRadians(angleMaxY));

        double maxY = Math.sin(Math.toRadians(angleMaxY)) * radiusMaxY;
        double minY = -maxY;

        Vector3D[][] positions = new Vector3D[getResolutionWidth()][getResolutionHeight()];
        double posZ = defaultZ;

        double stepX = (maxX - minX) / getResolutionWidth();
        double stepY = (maxY - minY) / getResolutionHeight();
        for (int x = 0; x < positions.length; x++) {
            for (int y = 0; y < positions[x].length; y++) {
                double posX = minX + (stepX * x);
                double posY = maxY - (stepY * y);
                positions[x][y] = new Vector3D(posX, posY, posZ);
            }
        }
        return positions;
    }

    @Override
    public Intersection getIntersection(Ray ray) {
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }
}