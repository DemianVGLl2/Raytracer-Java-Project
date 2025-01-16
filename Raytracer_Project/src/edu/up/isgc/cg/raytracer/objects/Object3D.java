package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

/**
 * Abstract class representing a 3D object that can be intersected by rays.
 */
public abstract class Object3D implements IIntersectable {
    private Color color;
    private Vector3D position;
    private double shininess;
    private double reflectivenessIndex;
    private double refractionIndex;

    /**
     * Constructs a 3D object with specified position, color, shininess, reflectiveness, and refraction indices.
     *
     * @param position             The position of the object
     * @param color                The color of the object
     * @param shininess            The shininess of the object
     * @param reflectivenessIndex  The reflectiveness index of the object
     * @param refractionIndex      The refraction index of the object
     */
    public Object3D(Vector3D position, Color color, double shininess, double reflectivenessIndex, double refractionIndex) {
        setPosition(position);
        setColor(color);
        setShininess(shininess);
        setReflectivenessIndex(reflectivenessIndex);
        setRefractionIndex(refractionIndex);
    }

    /**
     * Gets the color of the object.
     *
     * @return The color of the object
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the object.
     *
     * @param color The color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the position of the object.
     *
     * @return The position of the object
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * Sets the position of the object.
     *
     * @param position The position to set
     */
    public void setPosition(Vector3D position) {
        this.position = position;
    }

    /**
     * Gets the shininess of the object.
     *
     * @return The shininess of the object
     */
    public double getShininess() {
        return shininess;
    }

    /**
     * Sets the shininess of the object.
     *
     * @param shininess The shininess to set
     */
    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    /**
     * Gets the reflectiveness index of the object.
     *
     * @return The reflectiveness index of the object
     */
    public double getReflectivenessIndex() {
        return reflectivenessIndex;
    }

    /**
     * Sets the reflectiveness index of the object.
     *
     * @param reflectivenessIndex The reflectiveness index to set
     */
    public void setReflectivenessIndex(double reflectivenessIndex) {
        this.reflectivenessIndex = reflectivenessIndex;
        if (getRefractionIndex() != 0) this.reflectivenessIndex = 0;
    }

    /**
     * Gets the refraction index of the object.
     *
     * @return The refraction index of the object
     */
    public double getRefractionIndex() {
        return refractionIndex;
    }

    /**
     * Sets the refraction index of the object.
     *
     * @param refractionIndex The refraction index to set
     */
    public void setRefractionIndex(double refractionIndex) {
        this.refractionIndex = refractionIndex;
        if (getReflectivenessIndex() != 0) this.refractionIndex = 0;
    }
}