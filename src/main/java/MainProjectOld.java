import Engine.*;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL20.*;

public class MainProjectOld {
    // note from author: left right from the camera perspective
    private final Window window = new Window(1080, 1080, "Kevin The Minion");
    ArrayList<ObjectProject> objects = new ArrayList<>();
    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    boolean running = false;
    boolean running2 = false;
    boolean running3 = false;
    boolean running4 = false;
    boolean up = false;
    float animation = 0;
    int repetition = 0;

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
        camera.setPosition(0.0f, 0.0f, 3.0f);
        camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(0.0f));

        // code here
        // main body
        objects.add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.984f, 0.875f, 0.16f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.3f, 0.3f, 0.3f
        ));
        objects.get(0).createCylinder();
        objects.get(0).translate(0, 0.2f, 0);

        // rounding bottom
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.039f, 0.457f, 0.734f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.3f, 0.2f, 0.3f
        ));
        objects.get(0).getChildObject().get(0).createEllipsoid();
        objects.get(0).getChildObject().get(0).translate(0, -0.27f, 0);

        // rounding top
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.984f, 0.875f, 0.16f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.3f, 0.3f, 0.3f
        ));
        objects.get(0).getChildObject().get(1).createEllipsoid();
        objects.get(0).getChildObject().get(1).translate(0, 0.54f, 0);

        // kacamata kanan
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.75f, 0.75f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f
        ));
        objects.get(0).getChildObject().get(2).createCylinder();
        objects.get(0).getChildObject().get(2).rotate(-(float) Math.PI / 2, 1, 0, 0);
        objects.get(0).getChildObject().get(2).translate(0.08f, 0.6f, 0.2f);

        // kacamata kiri
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.75f, 0.75f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.1f
        ));
        objects.get(0).getChildObject().get(3).createCylinder();
        objects.get(0).getChildObject().get(3).rotate(-(float) Math.PI / 2, 1, 0, 0);
        objects.get(0).getChildObject().get(3).translate(-0.08f, 0.6f, 0.2f);

        // mata kanan
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.08f, 0.08f, 0.08f
        ));
        objects.get(0).getChildObject().get(4).createEllipsoid();
        objects.get(0).getChildObject().get(4).translate(0.08f, 0.6f, 0.25f);

        // mata kiri
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.08f, 0.08f, 0.08f
        ));
        objects.get(0).getChildObject().get(5).createEllipsoid();
        objects.get(0).getChildObject().get(5).translate(-0.08f, 0.6f, 0.25f);

        // pupil kanan
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.03f, 0.03f, 0.03f
        ));
        objects.get(0).getChildObject().get(6).createEllipsoid();
        objects.get(0).getChildObject().get(6).translate(0.08f, 0.6f, 0.32f);

        // pupil kiri
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.03f, 0.03f, 0.03f
        ));
        objects.get(0).getChildObject().get(7).createEllipsoid();
        objects.get(0).getChildObject().get(7).translate(-0.08f, 0.6f, 0.32f);

        // celana
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.039f, 0.457f, 0.734f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.3f, 0.1f, 0.3f
        ));
        objects.get(0).getChildObject().get(8).createCylinder();
        objects.get(0).getChildObject().get(8).translate(0, -0.2f, 0);

        // lengan kanan
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.984f, 0.875f, 0.16f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.03f, 0.2f, 0.03f
        ));
        objects.get(0).getChildObject().get(9).createCylinder();
        objects.get(0).getChildObject().get(9).rotate(-(float) Math.PI / 4, 1, 0, 0);
        objects.get(0).getChildObject().get(9).translate(0.0f, 0, 0.4f);
        objects.get(0).getChildObject().get(9).rotate((float) Math.PI / 2, 0, 1, 0);

        // tangan kanan
        objects.get(0).getChildObject().get(9).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.04f, 0.04f, 0.04f
        ));
        objects.get(0).getChildObject().get(9).getChildObject().get(0).createEllipsoid();
        objects.get(0).getChildObject().get(9).getChildObject().get(0).translate(0.0f, -0.14f, 0.54f);
        objects.get(0).getChildObject().get(9).getChildObject().get(0).rotate((float) Math.PI / 2, 0, 1, 0);

        // jari
        objects.get(0).getChildObject().get(9).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.3f
        ));
        objects.get(0).getChildObject().get(9).getChildObject().get(1).createEllipticParaboloid();
        objects.get(0).getChildObject().get(9).getChildObject().get(1).rotate((float) Math.PI / 2, 0, 0, 1);
        objects.get(0).getChildObject().get(9).getChildObject().get(1).translate(0.64f, -0.14f, 0f);

        objects.get(0).getChildObject().get(9).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.3f
        ));
        objects.get(0).getChildObject().get(9).getChildObject().get(2).createEllipticParaboloid();
        objects.get(0).getChildObject().get(9).getChildObject().get(2).rotate((float) Math.PI / 3, 0, 0, 1);
        objects.get(0).getChildObject().get(9).getChildObject().get(2).translate(0.64f, -0.2f, 0f);

        objects.get(0).getChildObject().get(9).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.3f
        ));
        objects.get(0).getChildObject().get(9).getChildObject().get(3).createEllipticParaboloid();
        objects.get(0).getChildObject().get(9).getChildObject().get(3).rotate((float) Math.PI / 5, 0, 0, 1);
        objects.get(0).getChildObject().get(9).getChildObject().get(3).translate(0.6f, -0.23f, 0f);

        // lengan kiri
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.984f, 0.875f, 0.16f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.03f, 0.2f, 0.03f
        ));
        objects.get(0).getChildObject().get(10).createCylinder();
        objects.get(0).getChildObject().get(10).rotate(-(float) Math.PI / 4, 1, 0, 0);
        objects.get(0).getChildObject().get(10).translate(0.0f, 0, 0.4f);
        objects.get(0).getChildObject().get(10).rotate(-(float) Math.PI / 2, 0, 1, 0);

        // tangan kiri
        objects.get(0).getChildObject().get(10).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.04f, 0.04f, 0.04f
        ));
        objects.get(0).getChildObject().get(10).getChildObject().get(0).createEllipsoid();
        objects.get(0).getChildObject().get(10).getChildObject().get(0).translate(0.0f, -0.14f, -0.54f);
        objects.get(0).getChildObject().get(10).getChildObject().get(0).rotate((float) Math.PI / 2, 0, 1, 0);

        // jari
        objects.get(0).getChildObject().get(10).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.3f
        ));
        objects.get(0).getChildObject().get(10).getChildObject().get(1).createEllipticParaboloid();
        objects.get(0).getChildObject().get(10).getChildObject().get(1).rotate(-(float) Math.PI / 2, 0, 0, 1);
        objects.get(0).getChildObject().get(10).getChildObject().get(1).translate(-0.64f, -0.14f, 0f);

        objects.get(0).getChildObject().get(10).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.3f
        ));
        objects.get(0).getChildObject().get(10).getChildObject().get(2).createEllipticParaboloid();
        objects.get(0).getChildObject().get(10).getChildObject().get(2).rotate(-(float) Math.PI / 3, 0, 0, 1);
        objects.get(0).getChildObject().get(10).getChildObject().get(2).translate(-0.64f, -0.2f, 0f);

        objects.get(0).getChildObject().get(10).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.3f
        ));
        objects.get(0).getChildObject().get(10).getChildObject().get(3).createEllipticParaboloid();
        objects.get(0).getChildObject().get(10).getChildObject().get(3).rotate(-(float) Math.PI / 5, 0, 0, 1);
        objects.get(0).getChildObject().get(10).getChildObject().get(3).translate(-0.6f, -0.23f, 0f);

        // kaki kanan
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.039f, 0.457f, 0.734f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.05f, 0.05f, 0.05f
        ));
        objects.get(0).getChildObject().get(11).createCylinder();
        objects.get(0).getChildObject().get(11).translate(0.1f, -0.5f, 0.02f);

        // sepatu kanan
        objects.get(0).getChildObject().get(11).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.12f
        ));
        objects.get(0).getChildObject().get(11).getChildObject().get(0).createHalfEllipsoid();
        objects.get(0).getChildObject().get(11).getChildObject().get(0).rotate((float) Math.PI / 2, 0, 0, 1);
        objects.get(0).getChildObject().get(11).getChildObject().get(0).translate(0.1f, -0.6f, 0.07f);

        // kaki kiri
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.039f, 0.457f, 0.734f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.05f, 0.05f, 0.05f
        ));
        objects.get(0).getChildObject().get(12).createCylinder();
        objects.get(0).getChildObject().get(12).translate(-0.1f, -0.5f, 0.02f);

        // sepatu kiri
        objects.get(0).getChildObject().get(12).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f, 0.1f, 0.12f
        ));
        objects.get(0).getChildObject().get(12).getChildObject().get(0).createHalfEllipsoid();
        objects.get(0).getChildObject().get(12).getChildObject().get(0).rotate((float) Math.PI / 2, 0, 0, 1);
        objects.get(0).getChildObject().get(12).getChildObject().get(0).translate(-0.1f, -0.6f, 0.07f);

        // mulut
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.7f, 0.2f, 0.2f, 1.0f)
        ));

        objects.get(0).getChildObject().get(13).addVerticesForCurve(new Vector3f(-0.5f, -0.2f, -0.1f));
        objects.get(0).getChildObject().get(13).addVerticesForCurve(new Vector3f(-0.25f, -0.5f, 0));
        objects.get(0).getChildObject().get(13).addVerticesForCurve(new Vector3f(0.25f, -0.5f, 0));
        objects.get(0).getChildObject().get(13).addVerticesForCurve(new Vector3f(0.5f, -0.15f, -0.1f));
        objects.get(0).getChildObject().get(13).createCurve();
        objects.get(0).getChildObject().get(13).scale(0.2f, 0.2f, 0.2f);
        objects.get(0).getChildObject().get(13).translate(0.0f, 0.35f, 0.31f);
        objects.get(0).getChildObject().get(13).setThickness(4.3f);

        // tali kacamata
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        ));

        objects.get(0).getChildObject().get(14).addVerticesForCurve(new Vector3f(-0.15f, 0, 0.05f));
        objects.get(0).getChildObject().get(14).addVerticesForCurve(new Vector3f(-0.43f, 0, -0.2f));
        objects.get(0).getChildObject().get(14).addVerticesForCurve(new Vector3f(-0.4f, 0, -0.7f));
        objects.get(0).getChildObject().get(14).addVerticesForCurve(new Vector3f(0.4f, 0, -0.7f));
        objects.get(0).getChildObject().get(14).addVerticesForCurve(new Vector3f(0.43f, 0, -0.2f));
        objects.get(0).getChildObject().get(14).addVerticesForCurve(new Vector3f(0.15f, 0, 0.05f));
        objects.get(0).getChildObject().get(14).createCurve();
        objects.get(0).getChildObject().get(14).scale(1.05f, 1.05f, 1.05f);
        objects.get(0).getChildObject().get(14).translate(0.0f, 0.6f, 0.22f);
        objects.get(0).getChildObject().get(14).setThickness(30f);

        // rambut
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        ));

        objects.get(0).getChildObject().get(15).addVerticesForCurve(new Vector3f(0f, 0, 0f));
        objects.get(0).getChildObject().get(15).addVerticesForCurve(new Vector3f(0f, 0.2f, 0f));
        objects.get(0).getChildObject().get(15).addVerticesForCurve(new Vector3f(0.08f, 0.2f, 0f));
        objects.get(0).getChildObject().get(15).createCurve();
        objects.get(0).getChildObject().get(15).scale(0.5f, 0.5f, 0.5f);
        objects.get(0).getChildObject().get(15).translate(0.0f, 0.84f, 0f);
        objects.get(0).getChildObject().get(15).setThickness(2f);

        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        ));

        objects.get(0).getChildObject().get(16).addVerticesForCurve(new Vector3f(0f, 0, 0f));
        objects.get(0).getChildObject().get(16).addVerticesForCurve(new Vector3f(0f, 0.2f, 0f));
        objects.get(0).getChildObject().get(16).addVerticesForCurve(new Vector3f(-0.08f, 0.2f, 0f));
        objects.get(0).getChildObject().get(16).createCurve();
        objects.get(0).getChildObject().get(16).scale(0.5f, 0.5f, 0.5f);
        objects.get(0).getChildObject().get(16).translate(0.0f, 0.84f, 0f);
        objects.get(0).getChildObject().get(16).setThickness(2f);

        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        ));

        objects.get(0).getChildObject().get(17).addVerticesForCurve(new Vector3f(0f, 0, 0f));
        objects.get(0).getChildObject().get(17).addVerticesForCurve(new Vector3f(0f, 0.2f, 0f));
        objects.get(0).getChildObject().get(17).addVerticesForCurve(new Vector3f(0, 0.2f, -0.08f));
        objects.get(0).getChildObject().get(17).createCurve();
        objects.get(0).getChildObject().get(17).scale(0.5f, 0.5f, 0.5f);
        objects.get(0).getChildObject().get(17).translate(0.0f, 0.84f, 0f);
        objects.get(0).getChildObject().get(17).setThickness(2f);

        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        ));

        objects.get(0).getChildObject().get(18).addVerticesForCurve(new Vector3f(0f, 0, 0f));
        objects.get(0).getChildObject().get(18).addVerticesForCurve(new Vector3f(0f, 0.2f, 0f));
        objects.get(0).getChildObject().get(18).addVerticesForCurve(new Vector3f(-0.08f, 0.2f, -0.08f));
        objects.get(0).getChildObject().get(18).createCurve();
        objects.get(0).getChildObject().get(18).scale(0.5f, 0.5f, 0.5f);
        objects.get(0).getChildObject().get(18).translate(0.0f, 0.84f, 0f);
        objects.get(0).getChildObject().get(18).setThickness(2f);

        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)
        ));

        objects.get(0).getChildObject().get(19).addVerticesForCurve(new Vector3f(0f, 0, 0f));
        objects.get(0).getChildObject().get(19).addVerticesForCurve(new Vector3f(0f, 0.25f, 0f));
        objects.get(0).getChildObject().get(19).createCurve();
        objects.get(0).getChildObject().get(19).scale(0.5f, 0.5f, 0.5f);
        objects.get(0).getChildObject().get(19).translate(0.0f, 0.84f, 0f);
        objects.get(0).getChildObject().get(19).setThickness(2f);

        // tali baju
        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.039f, 0.457f, 0.734f, 1.0f)
        ));

        objects.get(0).getChildObject().get(20).addVerticesForCurve(new Vector3f(-0.5f, 0f, -0.17f));
        objects.get(0).getChildObject().get(20).addVerticesForCurve(new Vector3f(-0.5f, 0.75f, -0.05f));
        objects.get(0).getChildObject().get(20).addVerticesForCurve(new Vector3f(0, 1.2f, 0.13f));
        objects.get(0).getChildObject().get(20).addVerticesForCurve(new Vector3f(0.5f, 0.75f, -0.05f));
        objects.get(0).getChildObject().get(20).addVerticesForCurve(new Vector3f(0.5f, 0f, -0.17f));
        objects.get(0).getChildObject().get(20).createCurve();
        objects.get(0).getChildObject().get(20).scale(0.4f, 0.4f, 0.4f);
        objects.get(0).getChildObject().get(20).translate(0.0f, -0.11f, 0.3f);
        objects.get(0).getChildObject().get(20).rotate((float) Math.PI / 2, 0, 1, 0);
        objects.get(0).getChildObject().get(20).setThickness(7f);

        objects.get(0).getChildObject().add(new ObjectProject(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.039f, 0.457f, 0.734f, 1.0f)
        ));

        objects.get(0).getChildObject().get(21).addVerticesForCurve(new Vector3f(-0.5f, 0f, -0.17f));
        objects.get(0).getChildObject().get(21).addVerticesForCurve(new Vector3f(-0.5f, 0.75f, -0.05f));
        objects.get(0).getChildObject().get(21).addVerticesForCurve(new Vector3f(0, 1.2f, 0.13f));
        objects.get(0).getChildObject().get(21).addVerticesForCurve(new Vector3f(0.5f, 0.75f, -0.05f));
        objects.get(0).getChildObject().get(21).addVerticesForCurve(new Vector3f(0.5f, 0f, -0.17f));
        objects.get(0).getChildObject().get(21).createCurve();
        objects.get(0).getChildObject().get(21).scale(0.4f, 0.4f, 0.4f);
        objects.get(0).getChildObject().get(21).translate(0.0f, -0.11f, 0.3f);
        objects.get(0).getChildObject().get(21).rotate(-(float) Math.PI / 2, 0, 1, 0);
        objects.get(0).getChildObject().get(21).setThickness(7f);
    }

    public void input() {
        if (window.isKeyPressed(GLFW_KEY_F) && !running && !running2 && !running3 && !running4) { // wave animation left
            running = true;
        }
        if (window.isKeyPressed(GLFW_KEY_G) && !running && !running2 && !running3 && !running4) { // jumping animation
            running2 = true;

            // raise hand
            ObjectProject temp1 = objects.get(0).getChildObject().get(9);
            ObjectProject temp2 = objects.get(0).getChildObject().get(10);
            temp1.currentPosition = temp1.updateCenterPoint();
            temp2.currentPosition = temp2.updateCenterPoint();
            temp1.translateWithChild(-temp1.currentPosition.x, -temp1.currentPosition.y, -temp1.currentPosition.z);
            temp2.translateWithChild(-temp2.currentPosition.x, -temp2.currentPosition.y, -temp2.currentPosition.z);
            temp1.rotateWithChild((float) Math.PI, 1, 0, 0);
            temp2.rotateWithChild((float) Math.PI, 1, 0, 0);
            temp1.translateWithChild(temp1.currentPosition.x, temp1.currentPosition.y, temp1.currentPosition.z);
            temp2.translateWithChild(temp2.currentPosition.x, temp2.currentPosition.y, temp2.currentPosition.z);
            temp1.translateWithChild(-0.03f, (float) Math.PI / 1.5f / 8, 0);
            temp2.translateWithChild(0.03f, (float) Math.PI / 1.5f / 8, 0);

            // tilt shoes
            ObjectProject temp3 = objects.get(0).getChildObject().get(11).getChildObject().get(0);
            ObjectProject temp4 = objects.get(0).getChildObject().get(12).getChildObject().get(0);
            temp3.currentPosition = temp3.updateCenterPoint();
            temp4.currentPosition = temp4.updateCenterPoint();
            temp3.translate(-temp3.currentPosition.x, -temp3.currentPosition.y, -temp3.currentPosition.z);
            temp4.translate(-temp4.currentPosition.x, -temp4.currentPosition.y, -temp4.currentPosition.z);
            temp3.rotate((float) Math.PI / 4, 1, 0, 0);
            temp4.rotate((float) Math.PI / 4, 1, 0, 0);
            temp3.translate(temp3.currentPosition.x, temp3.currentPosition.y, temp3.currentPosition.z);
            temp4.translate(temp4.currentPosition.x, temp4.currentPosition.y, temp4.currentPosition.z);
        }
        if (window.isKeyPressed(GLFW_KEY_H) && !running && !running2 && !running3 && !running4) { // wave animation right
            running3 = true;
        }
        if (window.isKeyPressed(GLFW_KEY_J) && !running && !running2 && !running3 && !running4) { // wave animation right
            running4 = true;
        }
        if (window.isKeyPressed(GLFW_KEY_W)) {
            camera.moveForward(0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            camera.moveBackwards(0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            camera.moveUp(0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            camera.moveDown(0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            camera.addRotation(-0.01f, 0f);
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.addRotation(0.01f, 0f);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.addRotation(0f, -0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.addRotation(0f, 0.01f);
        }
    }

    public void loop() {
        while (window.isOpen()) {
            window.update();
            glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
            GL.createCapabilities();

            input();

            // code here
            for (ObjectProject object : objects) {
                object.drawWithChild(camera, projection);
            }

            // wave animation left
            if (!up && running && !running2 && !running3 && !running4) { // hands up
                // left hand
                ObjectProject temp1 = objects.get(0).getChildObject().get(10);
                temp1.currentPosition = temp1.updateCenterPoint();
                temp1.translateWithChild(-temp1.currentPosition.x, -temp1.currentPosition.y, -temp1.currentPosition.z);
                temp1.rotateWithChild(-0.08f, 0, 0, 1);
                temp1.translateWithChild(temp1.currentPosition.x, temp1.currentPosition.y, temp1.currentPosition.z);
                temp1.translateWithChild(0.001f, 0.01f, 0);

                animation += 0.08f;
            }
            if (up && running && !running2 && !running3 && !running4) { // reset hand
                // left hand
                ObjectProject temp1 = objects.get(0).getChildObject().get(10);
                temp1.currentPosition = temp1.updateCenterPoint();
                temp1.translateWithChild(-temp1.currentPosition.x, -temp1.currentPosition.y, -temp1.currentPosition.z);
                temp1.rotateWithChild(0.08f, 0, 0, 1);
                temp1.translateWithChild(temp1.currentPosition.x, temp1.currentPosition.y, temp1.currentPosition.z);
                temp1.translateWithChild(-0.001f, -0.01f, 0);

                animation += 0.08f;
            }
            if (animation >= Math.PI / 1.4 && running && !running2 && !running3 && !running4) { // finishing and reset
                animation = 0;
                up = !up;
                repetition += 1;
                if (repetition >= 6) {
                    running = false;
                    repetition = 0;
                }
            }

            // jump animation
            if (!up && !running && running2 && !running3 && !running4) { // going up
                ObjectProject temp = objects.get(0);
                temp.currentPosition = temp.updateCenterPoint();
                temp.translateWithChild(0, 0.03f, 0);
                animation += 0.03f;
            }

            if (up && !running && running2 && !running3 && !running4) { // going down
                ObjectProject temp = objects.get(0);
                temp.currentPosition = temp.updateCenterPoint();
                temp.translateWithChild(0, -0.03f, 0);
                animation += 0.03f;
            }

            if (animation >= 1 && !running && running2 && !running3 && !running4) { // finishing and reset
                animation = 0;
                up = !up;
                repetition += 1;
                if (repetition >= 2) {
                    running2 = false;
                    repetition = 0;

                    // reset hand
                    ObjectProject temp1 = objects.get(0).getChildObject().get(9);
                    ObjectProject temp2 = objects.get(0).getChildObject().get(10);
                    temp1.currentPosition = temp1.updateCenterPoint();
                    temp2.currentPosition = temp2.updateCenterPoint();
                    temp1.translateWithChild(-temp1.currentPosition.x, -temp1.currentPosition.y, -temp1.currentPosition.z);
                    temp2.translateWithChild(-temp2.currentPosition.x, -temp2.currentPosition.y, -temp2.currentPosition.z);
                    temp1.rotateWithChild(-(float) Math.PI, 1, 0, 0);
                    temp2.rotateWithChild(-(float) Math.PI, 1, 0, 0);
                    temp1.translateWithChild(temp1.currentPosition.x, temp1.currentPosition.y, temp1.currentPosition.z);
                    temp2.translateWithChild(temp2.currentPosition.x, temp2.currentPosition.y, temp2.currentPosition.z);
                    temp1.translateWithChild(0.03f, -(float) Math.PI / 1.5f / 8, 0);
                    temp2.translateWithChild(-0.03f, -(float) Math.PI / 1.5f / 8, 0);

                    // reset shoes
                    ObjectProject temp3 = objects.get(0).getChildObject().get(11).getChildObject().get(0);
                    ObjectProject temp4 = objects.get(0).getChildObject().get(12).getChildObject().get(0);
                    temp3.currentPosition = temp3.updateCenterPoint();
                    temp4.currentPosition = temp4.updateCenterPoint();
                    temp3.translate(-temp3.currentPosition.x, -temp3.currentPosition.y, -temp3.currentPosition.z);
                    temp4.translate(-temp4.currentPosition.x, -temp4.currentPosition.y, -temp4.currentPosition.z);
                    temp3.rotate(-(float) Math.PI / 4, 1, 0, 0);
                    temp4.rotate(-(float) Math.PI / 4, 1, 0, 0);
                    temp3.translate(temp3.currentPosition.x, temp3.currentPosition.y, temp3.currentPosition.z);
                    temp4.translate(temp4.currentPosition.x, temp4.currentPosition.y, temp4.currentPosition.z);
                }
            }

            // wave animation right
            if (!up && !running && !running2 && running3 && !running4) { // hands up
                // right hand
                ObjectProject temp2 = objects.get(0).getChildObject().get(9);
                temp2.currentPosition = temp2.updateCenterPoint();
                temp2.translateWithChild(-temp2.currentPosition.x, -temp2.currentPosition.y, -temp2.currentPosition.z);
                temp2.rotateWithChild(0.08f, 0, 0, 1);
                temp2.translateWithChild(temp2.currentPosition.x, temp2.currentPosition.y, temp2.currentPosition.z);
                temp2.translateWithChild(-0.001f, 0.01f, 0);

                animation += 0.08f;
            }
            if (up && !running && !running2 && running3 && !running4) { // reset hand
                // right hand
                ObjectProject temp2 = objects.get(0).getChildObject().get(9);
                temp2.currentPosition = temp2.updateCenterPoint();
                temp2.translateWithChild(-temp2.currentPosition.x, -temp2.currentPosition.y, -temp2.currentPosition.z);
                temp2.rotateWithChild(-0.08f, 0, 0, 1);
                temp2.translateWithChild(temp2.currentPosition.x, temp2.currentPosition.y, temp2.currentPosition.z);
                temp2.translateWithChild(0.001f, -0.01f, 0);

                animation += 0.08f;
            }
            if (animation >= Math.PI / 1.4 && !running && !running2 && running3 && !running4) { // finishing and reset
                animation = 0;
                up = !up;
                repetition += 1;
                if (repetition >= 6) {
                    running3 = false;
                    repetition = 0;
                }
            }

            // wave animation left and right
            if (!up && !running && !running2 && !running3 && running4) { // hands up
                // left hand
                ObjectProject temp1 = objects.get(0).getChildObject().get(10);
                temp1.currentPosition = temp1.updateCenterPoint();
                temp1.translateWithChild(-temp1.currentPosition.x, -temp1.currentPosition.y, -temp1.currentPosition.z);
                temp1.rotateWithChild(-0.08f, 0, 0, 1);
                temp1.translateWithChild(temp1.currentPosition.x, temp1.currentPosition.y, temp1.currentPosition.z);
                temp1.translateWithChild(0.001f, 0.01f, 0);

                // right hand
                ObjectProject temp2 = objects.get(0).getChildObject().get(9);
                temp2.currentPosition = temp2.updateCenterPoint();
                temp2.translateWithChild(-temp2.currentPosition.x, -temp2.currentPosition.y, -temp2.currentPosition.z);
                temp2.rotateWithChild(0.08f, 0, 0, 1);
                temp2.translateWithChild(temp2.currentPosition.x, temp2.currentPosition.y, temp2.currentPosition.z);
                temp2.translateWithChild(-0.001f, 0.01f, 0);

                animation += 0.08f;
            }
            if (up && !running && !running2 && !running3 && running4) { // reset hand
                // left hand
                ObjectProject temp1 = objects.get(0).getChildObject().get(10);
                temp1.currentPosition = temp1.updateCenterPoint();
                temp1.translateWithChild(-temp1.currentPosition.x, -temp1.currentPosition.y, -temp1.currentPosition.z);
                temp1.rotateWithChild(0.08f, 0, 0, 1);
                temp1.translateWithChild(temp1.currentPosition.x, temp1.currentPosition.y, temp1.currentPosition.z);
                temp1.translateWithChild(-0.001f, -0.01f, 0);

                // right hand
                ObjectProject temp2 = objects.get(0).getChildObject().get(9);
                temp2.currentPosition = temp2.updateCenterPoint();
                temp2.translateWithChild(-temp2.currentPosition.x, -temp2.currentPosition.y, -temp2.currentPosition.z);
                temp2.rotateWithChild(-0.08f, 0, 0, 1);
                temp2.translateWithChild(temp2.currentPosition.x, temp2.currentPosition.y, temp2.currentPosition.z);
                temp2.translateWithChild(0.001f, -0.01f, 0);

                animation += 0.08f;
            }
            if (animation >= Math.PI / 1.4 && !running && !running2 && !running3 && running4) { // finishing and reset
                animation = 0;
                up = !up;
                repetition += 1;
                if (repetition >= 6) {
                    running4 = false;
                    repetition = 0;
                }
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
        new MainProjectOld().run();
    }
}
