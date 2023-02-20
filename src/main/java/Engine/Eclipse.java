package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glDrawArrays;

public class Eclipse extends Object2d{
    float x;
    float y;
    double rad;
    public Eclipse(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, Vector3f centerpoint, double height, double width) {
        super(shaderModuleDataList, vertices, color);
        createEclipse(centerpoint, height, width);
        setupVAOVBO();
    }

    public void createEclipse(Vector3f centerpoint, double height, double width){
        for(double i = 0.0f; i < 360; i += 0.01f){
            rad = Math.toRadians(i);
            x = (float)(centerpoint.x + width*Math.cos(i));
            y = (float)(centerpoint.y + height*Math.sin(i));
            vertices.add(new Vector3f(x, y, 0.0f));
        }
    }

    public void draw() {
        drawSetup();
        glDrawArrays(GL_TRIANGLE_FAN, 0, vertices.size());
    }
}
