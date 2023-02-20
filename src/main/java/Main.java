import Engine.*;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window = new Window(800, 800, "Hello World");
    ArrayList<Object2d> objects = new ArrayList<>();
    ArrayList<Object2d> objectsRectangle = new ArrayList<>();
    ArrayList<Object2d> objectsCircle = new ArrayList<>();

    public void run() {

        init();
        loop();

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        window.init();
        GL.createCapabilities();

        // code here
        objectsRectangle.add(new Rectangle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-1.0f, -1.0f, 0.0f),
                                new Vector3f(1.0f, -1.0f, 0.0f),
                                new Vector3f(-1.0f, -0.6f, 0.0f),
                                new Vector3f(1.0f, -0.6f, 0.0f)
                        )
                ),
                new Vector4f(0.0f, 1.0f, 0.0f, 1.0f),
                Arrays.asList(0, 1, 2, 1, 2, 3)
        ));

        objectsRectangle.add(new Rectangle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.82f, -0.3f, 0.0f),
                                new Vector3f(0.82f, -0.3f, 0.0f),
                                new Vector3f(-0.5f, 0.15f, 0.0f),
                                new Vector3f(0.5f, 0.15f, 0.0f)
                        )
                ),
                new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0, 1, 2, 2, 1, 3)
        ));

        objectsRectangle.add(new Rectangle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.7f, -0.7f, 0.0f),
                                new Vector3f(0.7f, -0.7f, 0.0f),
                                new Vector3f(-0.7f, -0.2f, 0.0f),
                                new Vector3f(0.7f, -0.3f, 0.0f),
                                new Vector3f(-0.5f, 0.1f, 0.0f),
                                new Vector3f(-0.3f, -0.3f, 0.0f)
                        )
                ),
                new Vector4f(1.0f, 0.6f, 0.0f, 1.0f),
                Arrays.asList(4, 2, 5, 2, 5, 0, 0, 1, 5, 1, 3, 5)
        ));

        objectsRectangle.add(new Rectangle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(0.3f, 0.1f, 0.0f),
                                new Vector3f(0.4f, 0.1f, 0.0f),
                                new Vector3f(0.3f, 0.3f, 0.0f),
                                new Vector3f(0.4f, 0.3f, 0.0f)
                        )
                ),
                new Vector4f(1.0f, 0.6f, 0.0f, 1.0f),
                Arrays.asList(0, 1, 2, 2, 1, 3)
        ));

        objectsRectangle.add(new Rectangle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(
                        List.of(
                                new Vector3f(0.27f, 0.3f, 0.0f),
                                new Vector3f(0.43f, 0.3f, 0.0f),
                                new Vector3f(0.27f, 0.35f, 0.0f),
                                new Vector3f(0.43f, 0.35f, 0.0f)
                        )
                ),
                new Vector4f(0.6f, 0.3f, 0.0f, 1.0f),
                Arrays.asList(0, 1, 2, 2, 1, 3)
        ));

        objectsCircle.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
                new Vector3f(-0.75f, 0.75f, 0.0f),
                0.1
        ));

        objectsCircle.add(new Circle(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
                new Vector3f(-0.7f, 0.75f, 0.0f),
                0.1
        ));

//        objects.add(new Object2d(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(0.0f, 0.5f, 0.0f),
//                                new Vector3f(-0.5f, -0.5f, 0.0f),
//                                new Vector3f(0.5f, -0.5f, 0.0f),
//                        )
//                ),
//                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f)
//        ));

//        objects.add(new Object2d(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/sceneWithVerticesColor.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/sceneWithVerticesColor.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(0.0f, 0.5f, 0.0f),
//                                new Vector3f(-0.5f, -0.5f, 0.0f),
//                                new Vector3f(0.5f, -0.5f, 0.0f)
//                        )
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(1.0f, 0.0f, 0.0f),
//                                new Vector3f(0.0f, 1.0f, 0.0f),
//                                new Vector3f(0.0f, 0.0f, 1.0f)
//                        )
//                )
//        ));
    }

    public void loop() {
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
            GL.createCapabilities();

            // code here
            for (Object2d object : objects) {
                object.drawWithVerticesColor();
            }

            for (Object2d object : objectsRectangle) {
                object.draw();
            }

            for (Object2d object : objectsCircle) {
                object.draw();
            }

            // Restore state
            glDisableVertexAttribArray(0);
            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
