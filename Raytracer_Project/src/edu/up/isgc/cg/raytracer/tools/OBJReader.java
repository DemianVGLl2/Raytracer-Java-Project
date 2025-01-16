package edu.up.isgc.cg.raytracer.tools;

import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.objects.Model3D;
import edu.up.isgc.cg.raytracer.objects.Triangle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading OBJ files and constructing 3D models.
 */
public abstract class OBJReader {
    /**
     * Constructs a 3D model from an OBJ file.
     *
     * @param path        The path to the OBJ file.
     * @param origin      The origin of the model.
     * @param color       The color of the model.
     * @param shininess   The shininess of the model.
     * @param reflectivity The reflectivity of the model.
     * @param refraction  The refraction index of the model.
     * @return The constructed 3D model.
     */
    public static Model3D getModel3D(String path, Vector3D origin, Color color, double shininess, double reflectivity, double refraction) {
        return getModel3D(path, origin, color, shininess, reflectivity, refraction, 0, 0, 0,1);
    }

    /**
     * Constructs a 3D model from an OBJ file with a specified scale.
     *
     * @param path        The path to the OBJ file.
     * @param origin      The origin of the model.
     * @param color       The color of the model.
     * @param shininess   The shininess of the model.
     * @param reflectivity The reflectivity of the model.
     * @param refraction  The refraction index of the model.
     * @param scale       The scale factor applied to the model.
     * @return The constructed 3D model.
     */
    public static Model3D getModel3D(String path, Vector3D origin, Color color, double shininess, double reflectivity, double refraction, double scale) {
        return getModel3D(path, origin, color, shininess, reflectivity, refraction, 0, 0, 0,scale);
    }

    /**
     * Constructs a 3D model from an OBJ file with specified rotation angles.
     *
     * @param path        The path to the OBJ file.
     * @param origin      The origin of the model.
     * @param color       The color of the model.
     * @param shininess   The shininess of the model.
     * @param reflectivity The reflectivity of the model.
     * @param refraction  The refraction index of the model.
     * @param angleX      The rotation angle around the X-axis (in degrees).
     * @param angleY      The rotation angle around the Y-axis (in degrees).
     * @param angleZ      The rotation angle around the Z-axis (in degrees).
     * @return The constructed 3D model.
     */
    public static Model3D getModel3D(String path, Vector3D origin, Color color, double shininess, double reflectivity, double refraction, double angleX, double angleY, double angleZ) {
        return getModel3D(path, origin, color, shininess, reflectivity, refraction, angleX, angleY, angleZ,1);
    }

    /**
     * Constructs a 3D model from an OBJ file with specified rotation angles and scale.
     *
     * @param path        The path to the OBJ file.
     * @param origin      The origin of the model.
     * @param color       The color of the model.
     * @param shininess   The shininess of the model.
     * @param reflectivity The reflectivity of the model.
     * @param refraction  The refraction index of the model.
     * @param angleX      The rotation angle around the X-axis (in degrees).
     * @param angleY      The rotation angle around the Y-axis (in degrees).
     * @param angleZ      The rotation angle around the Z-axis (in degrees).
     * @param scale       The scale factor applied to the model.
     * @return The constructed 3D model.
     */
    public static Model3D getModel3D(String path, Vector3D origin, Color color, double shininess, double reflectivity, double refraction, double angleX, double angleY, double angleZ, double scale) {
        try {
            angleX = Math.toRadians(angleX);
            angleY = Math.toRadians(angleY);
            angleZ = Math.toRadians(angleZ);

            Vector3D[] rotX = new Vector3D[]{ new Vector3D( 1,0,0),new Vector3D(0, Math.cos(angleX), -Math.sin(angleX)), new Vector3D(0, Math.sin(angleX), Math.cos(angleX))};
            Vector3D[] rotY = new Vector3D[]{ new Vector3D(Math.cos(angleY), 0, Math.sin(angleY)), new Vector3D( 0,1,0),new Vector3D(-Math.sin(angleY), 0, Math.cos(angleY))};
            Vector3D[] rotZ = new Vector3D[]{ new Vector3D(Math.cos(angleZ),  -Math.sin(angleZ), 0), new Vector3D(Math.sin(angleZ), Math.cos(angleZ), 0), new Vector3D( 0,0,1)};

            BufferedReader reader = new BufferedReader(new FileReader(path));

            List<Triangle> triangles = new ArrayList<>();
            List<Vector3D> vertices = new ArrayList<>();
            List<Vector3D> normals = new ArrayList<>();
            String line;
            int defaultSmoothingGroup = -1;
            int smoothingGroup = defaultSmoothingGroup;
            Map<Integer, List<Triangle>> smoothingMap = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("v ") || line.startsWith("vn ")) {
                    String[] vertexComponents = line.split("(\\s)+");
                    if (vertexComponents.length >= 4) {
                        double x = Double.parseDouble(vertexComponents[1]);
                        double y = Double.parseDouble(vertexComponents[2]);
                        double z = Double.parseDouble(vertexComponents[3]);
                        Vector3D vec = new Vector3D(x, y, z);
                        double[] result = new double[]{0, 0, 0};

                        for (int i = 0; i < 3; i++) {
                            result[i] = Vector3D.dotProduct(rotX[i], vec);
                        }
                        vec = new Vector3D(result[0], result[1], result[2]);
                        for (int i = 0; i < 3; i++) {
                            result[i] = Vector3D.dotProduct(rotY[i], vec);
                        }
                        vec = new Vector3D(result[0], result[1], result[2]);
                        for (int i = 0; i < 3; i++) {
                            result[i] = Vector3D.dotProduct(rotZ[i], vec);
                        }
                        vec = new Vector3D(result[0], result[1], result[2]);

                        if (line.startsWith("v ")) {
                            vertices.add(Vector3D.scalarMultiplication(vec, scale));
                        } else {
                            normals.add(vec);
                        }
                    }
                } else if (line.startsWith("f ")) {
                    String[] faceComponents = line.split("(\\s)+");
                    List<Integer> faceVertex = new ArrayList<>();
                    List<Integer> faceNormals = new ArrayList<>();

                    for (int i = 1; i < faceComponents.length; i++) {
                        String[] infoVertex = faceComponents[i].split("/");
                        if (infoVertex.length >= 1) {
                            int vertexIndex = Integer.parseInt(infoVertex[0]);
                            faceVertex.add(vertexIndex);
                        }
                        if (infoVertex.length >= 3) {
                            int normalIndex = Integer.parseInt(infoVertex[2]);
                            faceNormals.add(normalIndex);
                        }
                    }

                    if (faceVertex.size() >= 3) {
                        Vector3D[] triangleVertices = new Vector3D[faceVertex.size()];
                        Vector3D[] triangleNormals = new Vector3D[faceNormals.size()];

                        for (int i = 0; i < faceVertex.size(); i++) {
                            triangleVertices[i] = vertices.get(faceVertex.get(i) - 1);
                        }

                        Vector3D[] arrangedTriangleVertices = null;
                        Vector3D[] arrangedTriangleNormals = null;
                        if(normals.size() > 0 && !faceNormals.isEmpty()) {
                            for (int i = 0; i < faceNormals.size(); i++) {
                                triangleNormals[i] = normals.get(faceNormals.get(i) - 1);
                            }
                            arrangedTriangleNormals = new Vector3D[]{triangleNormals[1], triangleNormals[0], triangleNormals[2]};
                        }
                        arrangedTriangleVertices = new Vector3D[]{triangleVertices[1], triangleVertices[0], triangleVertices[2]};

                        Triangle tempTriangle = new Triangle(arrangedTriangleVertices, arrangedTriangleNormals);
                        triangles.add(tempTriangle);

                        List<Triangle> trianglesInMap = smoothingMap.get(smoothingGroup);
                        if (trianglesInMap == null) {
                            trianglesInMap = new ArrayList<>();
                        }
                        trianglesInMap.add(tempTriangle);

                        if (faceVertex.size() == 4) {
                            arrangedTriangleVertices = new Vector3D[]{triangleVertices[2], triangleVertices[0], triangleVertices[3]};
                            if(arrangedTriangleNormals != null) {
                                arrangedTriangleNormals = new Vector3D[]{triangleNormals[2], triangleNormals[0], triangleNormals[3]};
                            }
                            tempTriangle = new Triangle(arrangedTriangleVertices, arrangedTriangleNormals);
                            triangles.add(tempTriangle);
                            trianglesInMap.add(tempTriangle);
                        }

                        if (smoothingGroup != defaultSmoothingGroup) {
                            smoothingMap.put(smoothingGroup, trianglesInMap);
                        }
                    }
                } else if (line.startsWith("s ")) {
                    String[] smoothingComponent = line.split("(\\s)+");
                    if (smoothingComponent.length > 1) {
                        if (smoothingComponent[1].equals("off")) {
                            smoothingGroup = defaultSmoothingGroup;
                        } else {
                            try {
                                smoothingGroup = Integer.parseInt(smoothingComponent[1]);
                            } catch (NumberFormatException nfe) {
                                smoothingGroup = defaultSmoothingGroup;
                            }
                        }
                    }
                }
            }
            reader.close();

            class NormalPair {
                Vector3D normal;
                int count;

                public NormalPair() {
                    normal = Vector3D.ZERO();
                    count = 0;
                }
            }

            //Smooth vertices normals
            for (Integer key : smoothingMap.keySet()) {
                Map<Vector3D, NormalPair> vertexMap = new HashMap<>();
                List<Triangle> triangleInMap = smoothingMap.get(key);
                for (Triangle triangle : triangleInMap) {
                    Vector3D[] triangleVertices = triangle.getVertices();
                    Vector3D[] triangleNormals = triangle.getNormals();
                    for (int i = 0; i < triangleVertices.length; i++) {
                        NormalPair normalsVertex = vertexMap.get(triangleVertices[i]);
                        if (normalsVertex == null) {
                            normalsVertex = new NormalPair();
                        }
                        if (triangleNormals.length > 0 && i < triangleNormals.length) {
                            normalsVertex.normal = Vector3D.add(normalsVertex.normal, triangleNormals[i]);
                            normalsVertex.count++;
                        }
                        vertexMap.put(triangleVertices[i], normalsVertex);
                    }
                }
                for (Triangle triangle : triangleInMap) {
                    Vector3D[] triangleVertices = triangle.getVertices();
                    Vector3D[] triangleNormals = triangle.getNormals();
                    for (int i = 0; i < triangleVertices.length; i++) {
                        NormalPair normalsVertex = vertexMap.get(triangleVertices[i]);
                        triangleNormals[i] = Vector3D.scalarMultiplication(normalsVertex.normal, 1.0 / (double) normalsVertex.count);
                    }
                    triangle.setNormals(triangleNormals[0], triangleNormals[1], triangleNormals[2]);
                }
            }

            return new Model3D(origin, triangles.toArray(new Triangle[triangles.size()]), color, shininess, reflectivity, refraction);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        return null;
    }
}
