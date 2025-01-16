package edu.up.isgc.cg.raytracer;

import edu.up.isgc.cg.raytracer.lights.DirectionalLight;
import edu.up.isgc.cg.raytracer.lights.Light;
import edu.up.isgc.cg.raytracer.lights.PointLight;
import edu.up.isgc.cg.raytracer.lights.SpotLight;
import edu.up.isgc.cg.raytracer.objects.*;
import edu.up.isgc.cg.raytracer.tools.OBJReader;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static edu.up.isgc.cg.raytracer.tools.Clamp.clamp;

/**
 * Raytracer class responsible for rendering a 3D scene using ray tracing technique.
 * It handles setting up the scene, camera, lights, objects, and performing the ray tracing operation.
 * The final rendered image is saved as a PNG file.
 *
 * @autor Jafet
 * @autor Demián
 */

public class Raytracer {
    static BufferedImage image;
    static int threads = 16;

    public static void main(String[] args) {
        Date start = new Date();
        System.out.println("Start time: " + start);
        Scene scene03 = new Scene();
        scene03.setCamera(new Camera(new Vector3D(0,1,-6),100,60,
                4096, 2160,0.6,50.0));

        scene03.addLight(new DirectionalLight(new Vector3D(0.0,-1.0,0.0), Color.WHITE, 0.5));
        scene03.addLight(new PointLight(new Vector3D(4.0,7.0,4.0), Color.WHITE, 2.5));
        scene03.addLight(new PointLight(new Vector3D(-4.0,7.0,4.0), Color.WHITE, 2.5));
        scene03.addLight(new PointLight(new Vector3D(4.0,7.0,-2.0), Color.WHITE, 2.5));
        scene03.addLight(new PointLight(new Vector3D(-4.0,7.0,-2.0), Color.WHITE, 2.5));
        scene03.addLight(new PointLight(new Vector3D(0.0,3.5,-2.0), Color.WHITE, 2.5));
        scene03.addLight(new PointLight(new Vector3D(0.0,3.5,4.0), Color.WHITE, 2.5));
        scene03.addLight(new PointLight(new Vector3D(4.0,-1.0,4.0), Color.WHITE, 2.5));
        scene03.addLight(new PointLight(new Vector3D(-4.0,-1.0,4.0), Color.WHITE, 2.5));
        scene03.addLight(new PointLight(new Vector3D(4.0,-1.0,-2.0), Color.WHITE, 2.5));
        scene03.addLight(new PointLight(new Vector3D(-4.0,-1.0,-2.0), Color.WHITE, 2.5));

        scene03.addObject(OBJReader.getModel3D("SmallTeapot.obj", new Vector3D(-1.0,-0.25,-2.0), Color.GRAY, 16.0, 0.0, 0.05, 45.0, 30.0, 0.0, 0.45));
        scene03.addObject(OBJReader.getModel3D("Cube.obj", new Vector3D(1.0,0.0,1.0), Color.GRAY, 16.0, 0.0, 1.3, 20.0, 0.0, 30.0, 1.0));
        scene03.addObject(OBJReader.getModel3D("Cone.obj", new Vector3D(-1.0,2.0,2.5), Color.GRAY, 16.0, 0.0, 0.0, 45.0, 90.0, 180.0, 0.75));
        scene03.addObject(OBJReader.getModel3D("Ring.obj", new Vector3D(-2.0,-0.25,-2.0), Color.GRAY, 16.0, 0.3, 0.0, 0.0, 30.0, 70.0, 0.8));
        scene03.addObject(new Sphere(new Vector3D(2.5,-0.25,0.5), 0.7, Color.GRAY, 16.0, 0.0, 0.0));
        scene03.addObject(OBJReader.getModel3D("SmallTeapot.obj", new Vector3D(3.0,4.0,4.0), Color.GRAY, 16.0, 0.0, 0.0, 0.0, 0.0, 45.0, 1.0));
        scene03.addObject(OBJReader.getModel3D("Cube.obj", new Vector3D(-1.5,3.0,-0.5), Color.GRAY, 16.0, 0.6, 0.0, 45.0, 30.0, 0.0, 0.5));
        scene03.addObject(OBJReader.getModel3D("Cone.obj", new Vector3D(0.0,-0.25,-2.0), Color.GRAY, 16.0, 0.0, 0.0, 0.0, 0.0, 30.0, 0.25));
        scene03.addObject(OBJReader.getModel3D("Ring.obj", new Vector3D(3.0,-0.25,3.0), Color.GRAY, 16.0, 0.3, 0.0, 0.0, 0.0, 0.0, 1.0));
        scene03.addObject(new Sphere(new Vector3D(1.5,3.0,-0.5), 0.5, Color.GRAY, 16.0, 0.0, 2.3));
        scene03.addObject(OBJReader.getModel3D("C:\\Users\\CIAC\\Downloads\\Attempt_Scene_03\\JafetToyHorse.obj", new Vector3D(-2.0,1.5,1.0), Color.GRAY, 16.0, 0.0, 0.0, -45.0, 30.0, 15.0, 0.25));

        scene03.addObject(OBJReader.getModel3D("Wall.obj", new Vector3D(0.0,0.0,6.0), Color.GRAY, 16.0, 0.5, 0.0,0.0, 0.0, 0.0));
        scene03.addObject(OBJReader.getModel3D("Wall.obj", new Vector3D(-6.0,0.0,0.0), Color.GRAY, 16.0, 0.5, 0.0,0.0, -90.0, 0.0));
        scene03.addObject(OBJReader.getModel3D("Wall.obj", new Vector3D(6.0,0.0,0.0), Color.GRAY, 16.0, 0.5, 0.0,0.0, 90.0, 0.0));
        scene03.addObject(OBJReader.getModel3D("Wall.obj", new Vector3D(0.0,-1.5,2.0), Color.GRAY, 16.0, 0.5, 0.0,90.0, 0.0, 0.0));

        raytrace(scene03);
        File outputImage = new File("image.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Date end = new Date();
        System.out.println("End time: " + end);
        long timeDifference = (end.getTime() - start.getTime()) / 1000;
        long minutes = timeDifference / 60;
        long hours = timeDifference / (60 * 60);

        if (hours > 0) {
            System.out.printf("Time taken: %d hours %d minutes %d seconds%n", hours, minutes-(hours * 60), timeDifference - (hours * 60 * 60) - (minutes * 60));
        } else if (minutes > 0) {
            System.out.printf("Time taken: %d minutes %d seconds%n", minutes, timeDifference-(minutes * 60));
        } else {
            System.out.printf("Time taken: %d seconds%n", timeDifference);
        }
    }

    /**
     * Raytraces the given scene and generates the final image.
     *
     * @param scene Scene to raytrace
     */
    public static void raytrace(Scene scene) {
        Camera mainCamera = scene.getCamera();
        double[] nearFarPlanes = mainCamera.getNearFarPlanes();
        image = new BufferedImage(mainCamera.getResolutionWidth(), mainCamera.getResolutionHeight(), BufferedImage.TYPE_INT_RGB);
        List<Object3D> objects = scene.getObjects();
        List<Light> lights = scene.getLights();
        Vector3D[][] posRaytrace = mainCamera.calculatePositionsToRay();
        Vector3D pos = mainCamera.getPosition();
        double cameraZ = pos.getZ();

        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < posRaytrace.length; i++) {
            for (int j = 0; j < posRaytrace[i].length; j++) {
                Runnable runnable = draw(i, j, posRaytrace, pos, mainCamera, objects, nearFarPlanes, cameraZ, lights);
                executorService.execute(runnable);
            }
        }
        System.out.println("Raytrace enter: " + new Date());
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.HOURS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (!executorService.isTerminated()) {
                System.err.println("Cancel non-finished");
            }
        }
        executorService.shutdownNow();
    }

    /**
     * Multiplies the given color by a scalar factor.
     *
     * @param color Color to multiply
     * @param factor Scalar factor of multiplication to apply to the color
     * @return New color resulting from the multiplication
     */
    private static Color multiplyColor(Color color, double factor) {
        return new Color(
                (int) clamp(color.getRed() * factor, 0, 255),
                (int) clamp(color.getGreen() * factor, 0, 255),
                (int) clamp(color.getBlue() * factor, 0, 255)
        );
    }

    /**
     * Adds two colors together.
     *
     * @param c1 First color of the addition
     * @param c2 Second color of the addition
     * @return New color resulting from the addition of both colors
     */
    private static Color addColor(Color c1, Color c2) {
        return new Color(
                (int) clamp(c1.getRed() + c2.getRed(), 0, 255),
                (int) clamp(c1.getGreen() + c2.getGreen(), 0, 255),
                (int) clamp(c1.getBlue() + c2.getBlue(), 0, 255)
        );
    }

    /**
     * Casts a ray through a list of objects and returns the closest intersection.
     *
     * @param ray The ray to cast
     * @param objects The list of objects to check for intersections
     * @param caster The object that is casting the ray (can be null)
     * @param clippingPlanes The near and far clipping planes
     * @return The closest intersection, or null if no intersection is found
     */
    public static Intersection raycast(Ray ray, List<Object3D> objects, Object3D caster, double[] clippingPlanes) {
        Intersection closestIntersection = null;

        for (int i = 0; i < objects.size(); i++) {
            Object3D currObj = objects.get(i);
            if (caster == null || !currObj.equals(caster)) {
                Intersection intersection = currObj.getIntersection(ray);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    double intersectionZ = intersection.getPosition().getZ();

                    if (distance >= 0 &&
                            (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                            (clippingPlanes == null || (intersectionZ >= clippingPlanes[0] && intersectionZ <= clippingPlanes[1]))) {
                        closestIntersection = intersection;
                    }
                }
            } else {
                if (!caster.equals(currObj)) {
                    Intersection intersection = currObj.getIntersection(ray);
                    if (intersection != null) {
                        double distance = intersection.getDistance();
                        if (distance >= 0 && (closestIntersection == null || distance < closestIntersection.getDistance())) {
                            closestIntersection = intersection;
                        }
                    }
                }
            }
        }
        return closestIntersection;
    }

    /**
     * Creates a Runnable task that performs drawing at a specific pixel.
     *
     * @param i X-coordinate of the pixel
     * @param j Y-coordinate of the pixel
     * @param posRaytrace Positions to raytrace
     * @param pos Position vector
     * @param mainCamera The main camera
     * @param objects List of objects in the scene
     * @param nearFarPlanes Near and far clipping planes
     * @param cameraZ Z-coordinate of the camera
     * @param lights List of lights in the scene
     * @return A Runnable task for drawing the specified pixel
     */
    public static Runnable draw(int i, int j, Vector3D[][] posRaytrace, Vector3D pos, Camera mainCamera, List<Object3D> objects,
                                double[] nearFarPlanes, double cameraZ, List<Light> lights) {
        Runnable aRunnable = new Runnable() {
            @Override
            public void run() {
                Color color = decideColor(i, j, posRaytrace, pos, mainCamera, objects, nearFarPlanes, cameraZ, lights);
                setRGB(i, j, color);
            }
        };
        return aRunnable;
    }

    /**
     * Decides the color of a specific pixel based on the ray tracing algorithm.
     *
     * @param i X-coordinate of the pixel
     * @param j Y-coordinate of the pixel
     * @param posRaytrace Positions to raytrace
     * @param pos Position vector
     * @param mainCamera The main camera
     * @param objects List of objects in the scene
     * @param nearFarPlanes Near and far clipping planes
     * @param cameraZ Z-coordinate of the camera
     * @param lights List of lights in the scene
     * @return The color of the pixel
     */
    public static Color decideColor(int i, int j, Vector3D[][] posRaytrace, Vector3D pos, Camera mainCamera, List<Object3D> objects,
                                    double[] nearFarPlanes, double cameraZ, List<Light> lights) {
        double x = posRaytrace[i][j].getX() + pos.getX();
        double y = posRaytrace[i][j].getY() + pos.getY();
        double z = posRaytrace[i][j].getZ() + pos.getZ();

        Ray ray = new Ray(mainCamera.getPosition(), new Vector3D(x, y, z));
        Intersection closestIntersection = raycast(ray, objects, null,
                new double[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});
        Color pixelColor = Color.BLACK;
        int cont = 0;
        double reflection = 1;
        boolean refraction = false;
        double eta = 1.0;

        if (closestIntersection != null) {
            do {
                if (cont != 0){
                    refraction = false;
                    Vector3D nextRayDirection = null;
                    Vector3D incident = Vector3D.normalize(Vector3D.substract(closestIntersection.getPosition(), ray.getOrigin()));

                    if (closestIntersection.getObject().getRefractionIndex() != 0) {
                        pixelColor = multiplyColor(pixelColor, 0.5);

                        double cosI = Vector3D.dotProduct(Vector3D.scalarMultiplication(incident, -1), closestIntersection.getNormal());
                        double etaI = eta;
                        double etaT = closestIntersection.getObject().getRefractionIndex();

                        if (cosI < 0) {
                            cosI = -cosI;
                            closestIntersection.setNormal(Vector3D.scalarMultiplication(closestIntersection.getNormal(), -1));
                            double temp = etaI;
                            etaI = etaT;
                            etaT = temp;
                        }

                        double etaRatio = etaI / etaT;
                        double sinT2 = Math.pow(etaRatio, 2) * (1.0 - Math.pow(cosI, 2));

                        if (sinT2 <= 1.0) {
                            double cosT = Math.sqrt(1.0 - sinT2);
                            nextRayDirection = Vector3D.add(Vector3D.scalarMultiplication(incident, etaRatio), Vector3D.scalarMultiplication(closestIntersection.getNormal(), etaRatio * cosI - cosT));
                        }

                        eta = etaT;
                    }

                    if (nextRayDirection == null) {
                        pixelColor = multiplyColor(pixelColor, 1 - closestIntersection.getObject().getReflectivenessIndex());

                        nextRayDirection = Vector3D.substract(incident,
                                Vector3D.scalarMultiplication(closestIntersection.getNormal(), 2 * Vector3D.dotProduct(incident, closestIntersection.getNormal())));
                    }

                    ray = new Ray(closestIntersection.getPosition(), Vector3D.normalize(nextRayDirection));
                    closestIntersection = raycast(ray, objects, closestIntersection.getObject(), new double[]{0.0, nearFarPlanes[1]});
                }
                if (closestIntersection != null) {
                    cont += 1;
                    Color objColor = closestIntersection.getObject().getColor();
                    for (Light light : lights) {
                        Ray rayToLight;
                        Intersection intersectionBeforeLight = null;
                        if (light.getClass().equals(PointLight.class)) {
                            rayToLight = new Ray(closestIntersection.getPosition(),
                                    Vector3D.normalize(
                                            Vector3D.substract(light.getPosition(), closestIntersection.getPosition())));
                            intersectionBeforeLight = raycast(rayToLight, objects, closestIntersection.getObject(),
                                    new double[]{0, Vector3D.magnitude(Vector3D.substract(light.getPosition(), closestIntersection.getPosition()))});
                        } else if (light.getClass().equals(DirectionalLight.class)) {
                            rayToLight = new Ray(closestIntersection.getPosition(), Vector3D.scalarMultiplication(((DirectionalLight) light).getDirection(), -1));
                            intersectionBeforeLight = raycast(rayToLight, objects, closestIntersection.getObject(), null);
                        }  else if (light.getClass().equals(SpotLight.class)) {
                            rayToLight = new Ray(closestIntersection.getPosition(), Vector3D.scalarMultiplication(((SpotLight) light).getDirection(), -1));
                            intersectionBeforeLight = raycast(rayToLight, objects, closestIntersection.getObject(), null);
                        }

                        if (intersectionBeforeLight == null) {
                            double nDotL = light.getNDotL(closestIntersection);
                            Color lightColor = light.getColor();
                            double intensity = light.getIntensity() * nDotL;
                            if (light.getClass().equals(PointLight.class)) {
                                double distance = Vector3D.magnitude(Vector3D.substract(closestIntersection.getPosition(), light.getPosition()));
                                double diffuse = intensity / Math.pow(distance, 2);
                                Vector3D L_V = Vector3D.add(ray.getDirection(),
                                        Vector3D.normalize(Vector3D.substract(closestIntersection.getPosition(), light.getPosition())));
                                Vector3D H = Vector3D.scalarMultiplication(L_V, 1 / Vector3D.magnitude(L_V));

                                double specular = Math.pow(Vector3D.dotProduct(closestIntersection.getNormal(), H), closestIntersection.getObject().getShininess());
                                intensity *= diffuse + specular;
                            } else if (light.getClass().equals(DirectionalLight.class)) {
                                double distance = Vector3D.magnitude(Vector3D.scalarMultiplication(((DirectionalLight) light).getDirection(), -1));
                                double diffuse = intensity / Math.pow(distance, 1);
                                Vector3D L_V = Vector3D.add(ray.getDirection(),
                                        Vector3D.normalize(Vector3D.substract(closestIntersection.getPosition(), Vector3D.scalarMultiplication(((DirectionalLight) light).getDirection(), -1))));
                                Vector3D H = Vector3D.scalarMultiplication(L_V, 1 / Vector3D.magnitude(L_V));

                                double specular = Math.pow(Vector3D.dotProduct(closestIntersection.getNormal(), H), closestIntersection.getObject().getShininess());
                                intensity *= diffuse + specular;
                            } else if (light.getClass().equals(SpotLight.class)) {
                                double distance = Vector3D.magnitude(Vector3D.scalarMultiplication(((SpotLight) light).getDirection(), -1));
                                double diffuse = intensity / Math.pow(distance, 1);
                                Vector3D L_V = Vector3D.add(ray.getDirection(),
                                        Vector3D.normalize(Vector3D.substract(closestIntersection.getPosition(), Vector3D.scalarMultiplication(((SpotLight) light).getDirection(), -1))));
                                Vector3D H = Vector3D.scalarMultiplication(L_V, 1 / Vector3D.magnitude(L_V));

                                double specular = Math.pow(Vector3D.dotProduct(closestIntersection.getNormal(), H), closestIntersection.getObject().getShininess());
                                intensity *= diffuse + specular;
                            }

                            double[] lightColors = new double[]{lightColor.getRed() / 255.0, lightColor.getGreen() / 255.0, lightColor.getBlue() / 255.0};
                            double[] objColors = new double[]{objColor.getRed() / 255.0, objColor.getGreen() / 255.0, objColor.getBlue() / 255.0};

                            for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                                objColors[colorIndex] *= intensity * lightColors[colorIndex] * reflection;
                            }
                            Color diffuse = new Color(
                                    (float) clamp(objColors[0], 0.0, 1.0),
                                    (float) clamp(objColors[1], 0.0, 1.0),
                                    (float) clamp(objColors[2], 0.0, 1.0));
                            pixelColor = addColor(pixelColor, diffuse);
                        }
                    }
                    if (closestIntersection.getObject().getRefractionIndex() != 0) {
                        refraction = true;
                    } else {
                        reflection = reflection * (closestIntersection.getObject().getReflectivenessIndex());
                    }
                }
            } while (cont < 4 && closestIntersection != null && (reflection > 0 || refraction));

        }
        return pixelColor;
    }

    /**
     * Sets the RGB color of a specific pixel.
     *
     * @param x The x-coordinate of the pixel
     * @param y The y-coordinate of the pixel
     * @param color The color to set
     */
    public static synchronized void setRGB(int x, int y, Color color) {
        image.setRGB(x,y,color.getRGB());
    }
}