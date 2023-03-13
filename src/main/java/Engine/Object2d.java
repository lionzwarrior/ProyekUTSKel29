package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object2d extends ShaderProgram {
    public List<Vector3f> vertices;
    int vao;
    int vbo;
    Vector4f color;
    UniformsMap uniformsMap;
    List<Vector3f> verticesColor;
    int vboColor;
    List<Vector3f> curve = new ArrayList<>();
    public Matrix4f model;
    public Vector3f currentPosition;

    public Object2d(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        setupVAOVBO();
        this.color = color;
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
        uniformsMap.createUniform("model");
        model = new Matrix4f().scale(1, 1, 1);
    }

    public Object2d(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, List<Vector3f> verticesColor) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.verticesColor = verticesColor;
        setupVAOVBOWithVerticesColor();
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

    public void setupVAOVBOWithVerticesColor() {
        // set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        // mengirim vertices ke shader
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);

        // set vboColor
        vboColor = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        // mengirim vertices ke shader
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(verticesColor), GL_STATIC_DRAW);
    }

    public void drawSetup() {
        bind();
        uniformsMap.setUniform("uni_color", color);
        uniformsMap.setUniform("model", model);
        // bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
    }

    public void drawSetupWithVerticesColor() {
        bind();
        // bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        // bind VBOColor
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
    }

    public void draw() {
        drawSetup();
        // Draw the vertices
        glLineWidth(0);
        glPointSize(0);
        // GL_TRIANGLES
        // GL_LINE_LOOP
        // GL_LINE_STRIP
        // GL_LINES
        // GL_POINTS
        // GL_TRIANGLE_FAN
        glDrawArrays(GL_TRIANGLES, 0, vertices.size());
    }

    public void drawPolygon() {
        drawSetup();
        // Draw the vertices
        glLineWidth(10);
        glPointSize(10);
        // GL_TRIANGLES
        // GL_LINE_LOOP
        // GL_LINE_STRIP
        // GL_LINES
        // GL_POINTS
        // GL_TRIANGLE_FAN
        glDrawArrays(GL_POLYGON, 0, vertices.size());
    }

    public void drawLine() {
        drawSetup();
        // Draw the vertices
        glLineWidth(1);
        glPointSize(0);
        glDrawArrays(GL_LINE_STRIP, 0, vertices.size());
    }

    public void drawLineForCurve() {
        drawSetup();
        // Draw the vertices
        glLineWidth(1);
        glPointSize(0);
        glDrawArrays(GL_LINE_STRIP, 0, curve.size());
    }

    public void addVertices(Vector3f newVector) {
        vertices.add(newVector);
        setupVAOVBO();
    }

    public void addVerticesForCurve(Vector3f newVector) {
        vertices.add(newVector);
        createCurve();
    }

    public void drawWithVerticesColor() {
        drawSetupWithVerticesColor();
        // Draw the vertices
        glLineWidth(10);
        glPointSize(10);
        // GL_TRIANGLES
        // GL_LINE_LOOP
        // GL_LINE_STRIP
        // GL_LINES
        // GL_POINTS
        // GL_TRIANGLE_FAN
        glDrawArrays(GL_TRIANGLES, 0, vertices.size());
    }

    public void setupVAOVBOForCurve() {
        // set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        // mengirim vertices ke shader
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(curve), GL_STATIC_DRAW);
    }

    public void createCurve(){
        curve.clear();
        for(double i = 0; i <= 1.01; i += 0.01){
            curve.add(bezierCurve(i));
        }
        setupVAOVBOForCurve();
    }

    private Vector3f bezierCurve(double t){
        int i = 0;
        int size = vertices.size() - 1;
        Vector3f result = new Vector3f(0.0f, 0.0f, 0.0f);
        for(Vector3f vertice : vertices){
//            System.out.println(combinations(size, i));
            result.x += combinations(size, i) * Math.pow((1-t), size - i) * vertice.x * Math.pow(t, i);
//            System.out.println("(1-" + t + ")^" + (size-i) + " * " + vertice.x + " * " + t + "^" + i);
            result.y += combinations(size, i) * Math.pow((1-t), size - i) * vertice.y * Math.pow(t, i);
            i += 1;
        }
//        System.out.println(result.x + " " + result.y);
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

    public void translate(float x, float y, float z){
        model = (new Matrix4f().translate(x, y, z)).mul(model);
    }

    public void rotate(float angle, float x, float y, float z){
        model = new Matrix4f().rotate(angle, x, y, z).mul(new Matrix4f(model));
    }

    public void scale(float x, float y, float z){
        model = model.mul(new Matrix4f().scale(x, y, z));
    }
}
