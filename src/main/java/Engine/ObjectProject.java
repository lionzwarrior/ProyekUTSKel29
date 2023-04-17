package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPointSize;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class ObjectProject extends ShaderProgram{
    public List<Vector3f> vertices;
    int vao;
    int vbo;
    Vector4f color;
    UniformsMap uniformsMap;
    List<Vector3f> curve = new ArrayList<>();
    public Matrix4f model;
    public Vector3f currentPosition;
    private List<ObjectProject> childObject;
    public Vector3f centerpoint;
    float radiusX;
    float radiusY;
    float radiusZ;
    boolean isCurve;
    float thickness;

    public void setThickness(float thickness){
        this.thickness = thickness;
    }

    public float getThickness() {
        return thickness;
    }

    public Vector3f updateCenterPoint(){
        Vector3f centerTemp = new Vector3f();
        model.transformPosition(0f, 0f, 0f, centerTemp);
        return centerTemp;
    }

    public List<ObjectProject> getChildObject() {
        return childObject;
    }

    public void setChildObject(List<ObjectProject> childObject) {
        this.childObject = childObject;
    }

    public ObjectProject(List<ShaderProgram.ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.color = color;
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
        uniformsMap.createUniform("model");
        uniformsMap.createUniform("view");
        uniformsMap.createUniform("projection");
        model = new Matrix4f().scale(1, 1, 1);
        childObject = new ArrayList<>();
        this.isCurve = false;
        this.thickness = 0;
    }

    public ObjectProject(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, Vector3f centerpoint, float radiusX, float radiusY, float radiusZ) {
        super(shaderModuleDataList);
        this.centerpoint = centerpoint;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.radiusZ = radiusZ;
        this.vertices = vertices;
        this.color = color;
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
        uniformsMap.createUniform("model");
        uniformsMap.createUniform("view");
        uniformsMap.createUniform("projection");
        model = new Matrix4f().scale(1, 1, 1);
        childObject = new ArrayList<>();
        this.isCurve = false;
    }

    public void setupVAOVBO() {
        // set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        // mengirim vertices ke shader
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);
    }

    public void drawSetup(Camera camera, Projection projection) {
        bind();
        uniformsMap.setUniform("uni_color", color);
        uniformsMap.setUniform("model", model);
        uniformsMap.setUniform("view", camera.getViewMatrix());
        uniformsMap.setUniform("projection", projection.getProjMatrix());
        // bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
    }

    public void draw(Camera camera, Projection projection) {
        drawSetup(camera, projection);
        // Draw the vertices
        glLineWidth(0);
        glPointSize(0);
        // GL_TRIANGLES
        // GL_LINE_LOOP
        // GL_LINE_STRIP
        // GL_LINES
        // GL_POINTS
        // GL_TRIANGLE_FAN
        glDrawArrays(GL_TRIANGLE_FAN, 0, vertices.size());
    }

    public void drawWithChild(Camera camera, Projection projection) {
        drawSetup(camera, projection);
        // Draw the vertices
        glLineWidth(0);
        glPointSize(0);
        // GL_TRIANGLES
        // GL_LINE_LOOP
        // GL_LINE_STRIP
        // GL_LINES
        // GL_POINTS
        // GL_TRIANGLE_FAN
        this.draw(camera, projection);
        for(ObjectProject child: childObject){
            if(child.isCurve){
                child.drawLine(camera, projection);
            } else {
                child.drawWithChild(camera, projection);
            }
        }
    }

    public void translate(float x, float y, float z){
        model = (new Matrix4f().translate(x, y, z)).mul(model);
    }

    public void translateWithChild(float x, float y, float z){
        model = (new Matrix4f().translate(x, y, z)).mul(model);
        for(ObjectProject child: childObject){
            child.translateWithChild(x, y, z);
        }
    }

    public void rotate(float angle, float x, float y, float z){
        model = new Matrix4f().rotate(angle, x, y, z).mul(new Matrix4f(model));
    }

    public void rotateWithChild(float angle, float x, float y, float z){
        model = new Matrix4f().rotate(angle, x, y, z).mul(new Matrix4f(model));
        for(ObjectProject child: childObject){
            child.rotateWithChild(angle, x, y, z);
        }
    }

    public void scale(float x, float y, float z){
        model = model.mul(new Matrix4f().scale(x, y, z));
    }

    public void scaleWithChild(float x, float y, float z){
        model = model.mul(new Matrix4f().scale(x, y, z));
        for(ObjectProject child: childObject){
            child.scaleWithChild(x, y, z);
        }
    }

    // to make minion
    public void createCylinder(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(float i = -radiusY; i <= radiusY; i+= 0.001f) {
            for (double v = 0; v <= Math.PI*2; v += Math.PI / 200) {
                float x = radiusX * (float) (Math.cos(v));
                float z = radiusZ * (float) (Math.sin(v));
                temp.add(new Vector3f(x, i, z));
            }
        }
        vertices = temp;
        this.setupVAOVBO();
    }

    public void createEllipticParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 0; v<= radiusZ; v+=0.01f){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)(Math.cos(u) * v);
                float y = radiusY * (float)(Math.sin(u) * v);
                float z = (float)(Math.pow(v, 2));
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;
        this.setupVAOVBO();
    }

    public void createEllipsoid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)(Math.cos(v) * Math.cos(u));
                float y = radiusY * (float)(Math.cos(v) * Math.sin(u));
                float z = radiusZ * (float)(Math.sin(v));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
        this.setupVAOVBO();
    }

    public void createHalfEllipsoid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI/2; u<= Math.PI/2; u+=Math.PI/60){
                float x = radiusX * (float)(Math.cos(v) * Math.cos(u));
                float y = radiusY * (float)(Math.cos(v) * Math.sin(u));
                float z = radiusZ * (float)(Math.sin(v));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
        this.setupVAOVBO();
    }

    public void addVerticesForCurve(Vector3f newVector) {
        vertices.add(newVector);
    }

    public void createCurve(){
        curve.clear();
        for(double i = 0; i <= 1.01; i += 0.01){
            curve.add(bezierCurve(i));
        }
        this.vertices = curve;
        setupVAOVBO();
        this.isCurve = true;
    }

    private Vector3f bezierCurve(double t){
        int i = 0;
        int size = vertices.size() - 1;
        Vector3f result = new Vector3f(0.0f, 0.0f, 0.0f);
        for(Vector3f vertice : vertices){
            result.x += combinations(size, i) * Math.pow((1-t), size - i) * vertice.x * Math.pow(t, i);
            result.y += combinations(size, i) * Math.pow((1-t), size - i) * vertice.y * Math.pow(t, i);
            result.z += combinations(size, i) * Math.pow((1-t), size - i) * vertice.z * Math.pow(t, i);
            i += 1;
        }
        return result;
    }

    private int combinations(int n, int r){
        return factorial(n) / factorial(r) / factorial(n - r);
    }

    private int factorial(int n){
        int result = 1;
        for(int i = 1; i <= n; i++){
            result *= i;
        }
        return result;
    }

    public void drawLine(Camera camera, Projection projection) {
        drawSetup(camera, projection);
        // Draw the vertices
        glLineWidth(this.thickness);
        glDrawArrays(GL_LINE_STRIP, 0, vertices.size());
    }
}

