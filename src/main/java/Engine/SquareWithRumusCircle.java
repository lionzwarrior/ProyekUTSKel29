package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL15.*;

public class SquareWithRumusCircle extends Object2d{
    double rad;
    float x;
    float y;
    public Vector3f centerpoint;
    double radius;
    public SquareWithRumusCircle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, Vector3f centerpoint, double radius) {
        super(shaderModuleDataList, vertices, color);
        this.createSquare(centerpoint, radius);
        this.centerpoint = centerpoint;
        this.radius = radius;
        setupVAOVBO();
    }

    public void createSquare(Vector3f centerpoint, double radius){
        for(double i = 45.0f; i < 360; i += 90.0f){
            rad = Math.toRadians(i);
            x = (float)(centerpoint.x + radius*Math.cos(rad));
            y = (float)(centerpoint.y + radius*Math.sin(rad));
            vertices.add(new Vector3f(x, y, 0.0f));
        }
    }

    public void draw() {
        drawSetup();
        glDrawArrays(GL_TRIANGLE_FAN, 0, vertices.size());
    }

    public void updateCenterpoint(){
        vertices.clear();
        createSquare(centerpoint, radius);
        setupVAOVBO();
        draw();
    }
}
