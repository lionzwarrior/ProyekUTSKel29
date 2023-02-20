package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL15.*;

public class SquareWithRumusCircle extends Object2d{
    double rad;
    float x;
    float y;
    public SquareWithRumusCircle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, Vector3f centerpoint, double radius) {
        super(shaderModuleDataList, vertices, color);
        this.createStar(centerpoint, radius);
        setupVAOVBO();
    }

    public void createStar(Vector3f centerpoint, double radius){
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
}
