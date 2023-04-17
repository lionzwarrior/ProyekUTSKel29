import Engine.*;
import org.joml.*;
import org.lwjgl.opengl.GL;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window = new Window(1080, 1080, "Hello World");
//    ArrayList<Object2d> objects = new ArrayList<>();
//    ArrayList<Object2d> objectsRectangle = new ArrayList<>();
//    ArrayList<Object2d> objectsCircle = new ArrayList<>();
//    ArrayList<Object2d> objectsEclipse = new ArrayList<>();
//    ArrayList<Object2d> objectsStar = new ArrayList<>();
//    ArrayList<SquareWithRumusCircle> objectsSquare = new ArrayList<>();
//    ArrayList<Object2d> objectsTriangle = new ArrayList<>();
//    ArrayList<Object2d> objectsPointsControl = new ArrayList<>();
    ArrayList<Object2d> objectsSphere = new ArrayList<>();
    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());

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
        camera.setPosition(0.0f, 0.0f, 2.0f);
        camera.setRotation((float)Math.toRadians(0.0f), (float)Math.toRadians(0.0f));

        // code here
//        objectsRectangle.add(new Rectangle(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(-1.0f, -1.0f, 0.0f),
//                                new Vector3f(1.0f, -1.0f, 0.0f),
//                                new Vector3f(-1.0f, -0.6f, 0.0f),
//                                new Vector3f(1.0f, -0.6f, 0.0f)
//                        )
//                ),
//                new Vector4f(0.0f, 1.0f, 0.0f, 1.0f),
//                Arrays.asList(0, 1, 2, 1, 2, 3)
//        ));
//
//        objectsRectangle.add(new Rectangle(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(-0.81f, -0.3f, 0.0f),
//                                new Vector3f(0.81f, -0.3f, 0.0f),
//                                new Vector3f(-0.5f, 0.15f, 0.0f),
//                                new Vector3f(0.5f, 0.15f, 0.0f)
//                        )
//                ),
//                new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
//                Arrays.asList(0, 1, 2, 2, 1, 3)
//        ));
//
//        objectsRectangle.add(new Rectangle(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(-0.7f, -0.7f, 0.0f),
//                                new Vector3f(0.7f, -0.7f, 0.0f),
//                                new Vector3f(-0.7f, -0.2f, 0.0f),
//                                new Vector3f(0.7f, -0.3f, 0.0f),
//                                new Vector3f(-0.5f, 0.1f, 0.0f),
//                                new Vector3f(-0.3f, -0.3f, 0.0f)
//                        )
//                ),
//                new Vector4f(1.0f, 0.6f, 0.0f, 1.0f),
//                Arrays.asList(4, 2, 5, 2, 5, 0, 0, 1, 5, 1, 3, 5)
//        ));
//
//        objectsRectangle.add(new Rectangle(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(0.3f, 0.1f, 0.0f),
//                                new Vector3f(0.4f, 0.1f, 0.0f),
//                                new Vector3f(0.3f, 0.3f, 0.0f),
//                                new Vector3f(0.4f, 0.3f, 0.0f)
//                        )
//                ),
//                new Vector4f(1.0f, 0.6f, 0.0f, 1.0f),
//                Arrays.asList(0, 1, 2, 2, 1, 3)
//        ));
//
//        objectsRectangle.add(new Rectangle(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(0.27f, 0.3f, 0.0f),
//                                new Vector3f(0.43f, 0.3f, 0.0f),
//                                new Vector3f(0.27f, 0.35f, 0.0f),
//                                new Vector3f(0.43f, 0.35f, 0.0f)
//                        )
//                ),
//                new Vector4f(0.6f, 0.3f, 0.0f, 1.0f),
//                Arrays.asList(0, 1, 2, 2, 1, 3)
//        ));
//
//        objectsCircle.add(new Circle(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
//                new Vector3f(-0.75f, 0.75f, 0.0f),
//                0.1
//        ));
//
//        objectsCircle.add(new Circle(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
//                new Vector3f(-0.7f, 0.75f, 0.0f),
//                0.1
//        ));
//
//        objectsEclipse.add(new Eclipse(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.37f, 0.37f, 0.37f, 1.0f),
//                new Vector3f(0.35f, 0.42f, 0.0f),
//                0.05, 0.07
//        ));
//
//        objectsEclipse.add(new Eclipse(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.37f, 0.37f, 0.37f, 1.0f),
//                new Vector3f(0.4f, 0.47f, 0.0f),
//                0.05, 0.09
//        ));
//
//        objectsEclipse.add(new Eclipse(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.37f, 0.37f, 0.37f, 1.0f),
//                new Vector3f(0.5f, 0.52f, 0.0f),
//                0.05, 0.14
//        ));
//
//        objectsStar.add(new Star(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
//                new Vector3f(0.0f, 0.75f, 0.0f),
//                0.02
//        ));
//
//        objectsStar.add(new Star(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
//                new Vector3f(0.8f, 0.7f, 0.0f),
//                0.04
//        ));
//
//        objectsStar.add(new Star(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
//                new Vector3f(-0.4f, 0.5f, 0.0f),
//                0.04
//        ));

//        objectsSquare.add(new SquareWithRumusCircle(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
//                new Vector3f(0.0f, 0.0f, 0.0f),
//                0.2
//        ));

//        objectsTriangle.add(new TriangleWithRumusCircle(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
//                new Vector3f(0.0f, 0.0f, 0.0f),
//                0.2
//        ));

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

//        objectsPointsControl.add(new Object2d(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.0f, 1.0f, 1.0f, 1.0f)
//        ));
//
//        objectsPointsControl.add(new Object2d(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.0f, 1.0f, 0.0f, 1.0f)
//        ));

//        objects.add(new Object2d(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(
//                        List.of(
//                                new Vector3f(0.0f, 0.0f, 0.0f),
//                                new Vector3f(0.0f, -0.5f, 0.0f),
//                                new Vector3f(0.5f, -0.5f, 0.0f),
//                                new Vector3f(0.0f, 0.5f, 0.0f)
//                        )
//                ),
//                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f)
//        ));

//        objectsSphere.add(new Sphere(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
//                new Vector3f(0.0f, 0.0f, 0.0f),
//                0.1, 0.1, 0.1f
//        ));
//        objectsSphere.get(0).scale(0.5f, 0.5f, 0.5f);
//
//        objectsSphere.add(new Sphere(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.5f, 0.35f, 0.05f, 1.0f),
//                new Vector3f(0.0f, 0.0f, 0.0f),
//                0.1, 0.1, 0.1f
//        ));
//        objectsSphere.get(1).scale(0.1f, 0.1f, 0.1f);
//        objectsSphere.get(1).translate(0.25f, 0.25f, 0.25f);
//
//        objectsSphere.add(new Sphere(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.0f, 1.0f, 0.0f, 1.0f),
//                new Vector3f(0.0f, 0.0f, 0.0f),
//                0.1, 0.1, 0.1f
//        ));
//        objectsSphere.get(2).scale(0.13f, 0.13f, 0.13f);
//        objectsSphere.get(2).translate(0.35f, 0.35f, 0.35f);
//
//        objectsSphere.add(new Sphere(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
//                new Vector3f(0.0f, 0.0f, 0.0f),
//                0.1, 0.1, 0.1f
//        ));
//        objectsSphere.get(3).scale(0.16f, 0.16f, 0.16f);
//        objectsSphere.get(3).translate(0.48f, 0.48f, 0.48f);
//
//        objectsSphere.add(new Sphere(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
//                new Vector3f(0.0f, 0.0f, 0.0f),
//                0.1, 0.1, 0.1f
//        ));
//        objectsSphere.get(4).scale(0.16f, 0.16f, 0.16f);
//        objectsSphere.get(4).translate(0.7f, 0.7f, 0.7f);
//
//        objectsSphere.add(new Sphere(
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
//                new Vector3f(0.0f, 0.0f, 0.0f),
//                0.1, 0.1, 0.1f
//        ));
//        objectsSphere.get(5).scale(0.05f, 0.05f, 0.05f);
//        objectsSphere.get(5).translate(0.8f, 0.8f, 0.8f);
//
//        for(Object2d object: objectsSphere){
//            object.currentPosition = object.model.transformPosition(new Vector3f(0f, 0f, 0f));
//        }

        objectsSphere.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1, 0.1, 0.1f
        ));

        objectsSphere.get(0).scale(0.25f, 0.25f, 0.25f);

        objectsSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1, 0.1, 0.1f
        ));
        objectsSphere.get(0).getChildObject().get(0).scale(0.15f, 0.15f, 0.15f);
        objectsSphere.get(0).getChildObject().get(0).translate(0.25f, 0.0f, 0.0f);

        objectsSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1, 0.1, 0.1f
        ));
        objectsSphere.get(0).getChildObject().get(1).scale(0.15f, 0.15f, 0.15f);
        objectsSphere.get(0).getChildObject().get(1).translate(0.45f, 0.0f, 0.0f);

        objectsSphere.get(0).getChildObject().add(new Sphere(Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 1.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1, 0.1, 0.1f
        ));
        objectsSphere.get(0).getChildObject().get(2).scale(0.15f, 0.15f, 0.15f);
        objectsSphere.get(0).getChildObject().get(2).translate(0.65f, 0.0f, 0.0f);

        objectsSphere.get(0).currentPosition = objectsSphere.get(0).updateCenterPoint();
        for(Object2d child: objectsSphere.get(0).getChildObject()){
            child.currentPosition = child.updateCenterPoint();
        }
    }

    public void input() {
        if (window.isKeyPressed(GLFW_KEY_F)) {
//            for(Object2d object: objectsSphere){
//                object.rotate((float)Math.toRadians(1), 0, 0, 1);
//                object.currentPosition = object.model.transformPosition(new Vector3f(0f, 0f, 0f));
//            }
//            for(Object2d object: objectsSphere){
//                object.rotateWithChild((float)Math.toRadians(1), 0, 0, 1);
//            }
//
//            objectsSphere.get(0).currentPosition = objectsSphere.get(0).updateCenterPoint();
//            for(Object2d child: objectsSphere.get(0).getChildObject()){
//                child.currentPosition = child.updateCenterPoint();
//            }

        }

        if (window.isKeyPressed(GLFW_KEY_G)) {
//            for(Object2d object: objectsSphere){
//                object.translate(-object.currentPosition.x, -object.currentPosition.y, -object.currentPosition.z);
//                object.rotate((float)Math.toRadians(1), 0, 0, 1);
//                object.translate(object.currentPosition.x, object.currentPosition.y, object.currentPosition.z);
//            }
//
//            for(Object2d child: objectsSphere.get(0).getChildObject()){
//                child.translate(-child.currentPosition.x, -child.currentPosition.y, -child.currentPosition.z);
//                child.rotate((float)Math.toRadians(1), 0, 0, 1);
//                child.translate(child.currentPosition.x, child.currentPosition.y, child.currentPosition.z);
//            }
        }

        if (window.isKeyPressed(GLFW_KEY_H)) {
//            objectsSphere.get(5).translate(-objectsSphere.get(4).currentPosition.x, -objectsSphere.get(4).currentPosition.y, -objectsSphere.get(4).currentPosition.z);
//            objectsSphere.get(5).rotate((float)Math.toRadians(1), 0, 0, 1);
//            objectsSphere.get(5).translate(objectsSphere.get(4).currentPosition.x, objectsSphere.get(4).currentPosition.y, objectsSphere.get(4).currentPosition.z);
//            objectsSphere.get(5).currentPosition = objectsSphere.get(5).model.transformPosition(new Vector3f(0f, 0f, 0f));
//
//            for(Object2d child: objectsSphere.get(0).getChildObject()){
//                child.translate(-objectsSphere.get(0).getChildObject().get(0).currentPosition.x, -objectsSphere.get(0).getChildObject().get(0).currentPosition.y, -objectsSphere.get(0).getChildObject().get(0).currentPosition.z);
//                child.rotate((float) Math.toRadians(1), 0, 0, 1);
//                child.translate(objectsSphere.get(0).getChildObject().get(0).currentPosition.x, objectsSphere.get(0).getChildObject().get(0).currentPosition.y, objectsSphere.get(0).getChildObject().get(0).currentPosition.z);
//                child.currentPosition = child.updateCenterPoint();
//            }
        }

//        if (window.getMouseInput().isLeftButtonPressed()) {
//            Vector2f pos = window.getMouseInput().getCurrentPos();
////            System.out.println("x : "+pos.x+" y : "+pos.y);
//
//            pos.x = (pos.x - (window.getWidth()) / 2.0f) / (window.getWidth() / 2.0f);
//            pos.y = -(pos.y - (window.getHeight()) / 2.0f) / (window.getHeight() / 2.0f);
////            System.out.println("x : " + pos.x+" y : "+pos.y);
//            if ((!(pos.x > 1 || pos.x < -0.97) && !(pos.y > 0.97 || pos.y < -1))) {
//                System.out.println("x : " + pos.x + " y : " + pos.y);
//                for (SquareWithRumusCircle object : objectsSquare){
//                    if (Math.sqrt(Math.pow(pos.x - object.centerpoint.x, 2) + Math.pow(pos.y - object.centerpoint.y, 2)) < 0.1){
////                        System.out.println(Math.sqrt(Math.pow(pos.x - object.centerpoint.x, 2) + Math.pow(pos.y - object.centerpoint.y, 2)));
//                        object.centerpoint.x = pos.x;
//                        object.centerpoint.y = pos.y;
//                        object.updateCenterpoint();
//                        break;
//                    }
//                }
//                for (Vector3f point : objectsPointsControl.get(0).vertices){
//                    if (Math.sqrt(Math.pow(pos.x - point.x, 2) + Math.pow(pos.y - point.y, 2)) < 0.1){
////                        System.out.println(Math.sqrt(Math.pow(pos.x - point.x, 2) + Math.pow(pos.y - point.y, 2)));
//                        point.x = pos.x;
//                        point.y = pos.y;
//                        objectsPointsControl.get(0).setupVAOVBO();
//                        objectsPointsControl.get(0).drawLine();
//                        break;
//                    }
//                }
//                for (Vector3f point : objectsPointsControl.get(1).vertices){
//                    if (Math.sqrt(Math.pow(pos.x - point.x, 2) + Math.pow(pos.y - point.y, 2)) < 0.1){
////                        System.out.println(Math.sqrt(Math.pow(pos.x - point.x, 2) + Math.pow(pos.y - point.y, 2)));
//                        point.x = pos.x;
//                        point.y = pos.y;
//                        objectsPointsControl.get(1).createCurve();
//                        objectsPointsControl.get(1).drawLineForCurve();
//                        return;
//                    }
//                }
//                objectsPointsControl.get(0).addVertices(new Vector3f(pos.x, pos.y, 0));
//                objectsPointsControl.get(1).addVerticesForCurve(new Vector3f(pos.x, pos.y, 0));
//                objectsSquare.add(new SquareWithRumusCircle(
//                        Arrays.asList(
//                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                        ),
//                        new ArrayList<>(),
//                        new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
//                        new Vector3f(pos.x, pos.y, 0.0f),
//                        0.1
//                ));
//            }
//        }
    }

    public void loop() {
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            GL.createCapabilities();

            input();

            // code here
//            for (Object2d object : objects){
//                object.draw();
//            }

//            for (Object2d object : objects){
//                object.drawPolygon();
//            }

//            for (Object2d object : objects) {
//                object.drawWithVerticesColor();
//            }
//
//            for (Object2d object : objectsRectangle) {
//                object.draw();
//            }
//
//            for (Object2d object : objectsCircle) {
//                object.draw();
//            }
//
//            for (Object2d object : objectsEclipse) {
//                object.draw();
//            }
//
//            for (Object2d object : objectsStar) {
//                object.draw();
//            }

//            for (Object2d object : objectsSquare) {
//                object.draw();
//            }
//
//            for (Object2d object : objectsTriangle) {
//                object.draw();
//            }

//            objectsPointsControl.get(0).drawLine();
//            objectsPointsControl.get(1).drawLineForCurve();

//            for (Object2d object : objectsSphere) {
//                object.drawLine(camera, projection);
//            }

            for (Object2d object : objectsSphere) {
                object.drawWithChild(camera, projection);
            }

            objectsSphere.get(0).rotateWithChild(0.01f, 1, 0, 0);

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
