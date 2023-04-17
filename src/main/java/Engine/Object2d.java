package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

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
    private List<Object2d> childObject;

    public Vector3f updateCenterPoint(){
        Vector3f centerTemp = new Vector3f();
        model.transformPosition(0f, 0f, 0f, centerTemp);
        return centerTemp;
    }

    public List<Object2d> getChildObject() {
        return childObject;
    }

    public void setChildObject(List<Object2d> childObject) {
        this.childObject = childObject;
    }

    public Object2d(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        setupVAOVBO();
        this.color = color;
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
        uniformsMap.createUniform("model");
        uniformsMap.createUniform("view");
        uniformsMap.createUniform("projection");
        model = new Matrix4f().scale(1, 1, 1);
        childObject = new ArrayList<>();
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
    public void drawLine(Camera camera, Projection projection) {
        drawSetup(camera, projection);
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

    public void drawWithChild() {
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
        glDrawArrays(GL_LINE_STRIP, 0, vertices.size());
        for(Object2d child: childObject){
            child.drawLine();
        }
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
        glDrawArrays(GL_LINE_STRIP, 0, vertices.size());
        for(Object2d child: childObject){
            child.drawLine(camera, projection);
        }
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

    public void translateWithChild(float x, float y, float z){
        model = (new Matrix4f().translate(x, y, z)).mul(model);
        for(Object2d child: childObject){
            child.translate(x, y, z);
        }
    }

    public void rotate(float angle, float x, float y, float z){
        model = new Matrix4f().rotate(angle, x, y, z).mul(new Matrix4f(model));
    }

    public void rotateWithChild(float angle, float x, float y, float z){
        model = new Matrix4f().rotate(angle, x, y, z).mul(new Matrix4f(model));
        for(Object2d child: childObject){
            child.rotate(angle, x, y, z);
        }
    }

    public void scale(float x, float y, float z){
        model = model.mul(new Matrix4f().scale(x, y, z));
    }

    public void scaleWithChild(float x, float y, float z){
        model = model.mul(new Matrix4f().scale(x, y, z));
        for(Object2d child: childObject){
            child.scale(x, y, z);
        }
    }

    public void createEllipsoid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.cos(v) * Math.cos(u));
                float y = 0.5f * (float)(Math.cos(v) * Math.sin(u));
                float z = 0.5f * (float)(Math.sin(v));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;
    }

    // to make minion
    public void createCylinder(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(float i = 0.0f; i <= 1f; i+= 0.01f) {
            for (double v = 0; v <= Math.PI*2; v += Math.PI / 60) {
                for (double u = 0; u <= Math.PI*2; u += Math.PI / 60) {
                    float x = 0.3f * (float) (Math.cos(v));
                    float y = 0.3f * (float) (Math.sin(v));
                    temp.add(new Vector3f(x, y, i));
                }
            }
        }
        vertices = temp;
    }
}
