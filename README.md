# Java Ray Tracer

This project is a fully functional ray tracer implemented in Java, built from scratch without external frameworks. It demonstrates advanced rendering techniques and supports creating photorealistic images by defining scenes programmatically.

---

## 🌟 Features

- **Blinn-Phong Shading**: Adds realistic shininess to objects.
- **Reflection and Refraction**: Simulates light bouncing and passing through transparent objects.
- **Transformations**: Supports object rotation and scaling.
- **OBJ Reader**: Integrated loader for 3D object files.
- **Customizable Scenes**: Define and render your own scenes directly in the code.

---

## 🚀 Getting Started

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher.
- **IDE/Text Editor**: IntelliJ IDEA, Eclipse, or any text editor.

### Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/DemianVGLl2/Raytracer-Java-Project.git
    cd Raytracer-Java-Project
    ```
2. Open the project in your preferred IDE.
3. Build the project.

---

## 📐 Creating a Scene

You can create and render a custom scene by editing the `RayTracer.java` file. Here's an example of a basic setup:

```java
public static void main(String[] args) {
    System.out.println(new Date());
    Scene scene01 = new Scene();
    scene01.setCamera(new Camera(new Vector3D(0, 0, -4), 80, 80, 800, 800, 2, 60));
    // Define your scene objects here

    raytrace(scene01);
    FileManager.saveImage(image, "image_output", "png");
}
