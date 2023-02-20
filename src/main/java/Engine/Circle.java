package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class Circle extends Object2d{
    float x;
    float y;
    double rad;
    int ibo;

    public Circle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, Vector3f centerpoint, double radius) {
        super(shaderModuleDataList, vertices, color);
        createCircle(centerpoint, radius);
        setupVAOVBO();
    }

    public void createCircle(Vector3f centerpoint, double radius){
        for(double i = 0.0f; i < 360; i += 0.01f){
            rad = Math.toRadians(i);
            x = (float)(centerpoint.x + radius*Math.cos(i));
            y = (float)(centerpoint.y + radius*Math.sin(i));
            vertices.add(new Vector3f(x, y, 0.0f));
        }
    }

    public void draw() {
        drawSetup();
        glDrawArrays(GL_TRIANGLE_FAN, 0, vertices.size());
    }
}
