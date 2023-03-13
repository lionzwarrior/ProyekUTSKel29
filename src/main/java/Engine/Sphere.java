package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;

public class Sphere extends Eclipse{
    Float radiusZ;
    public Vector3f centerpoint;
    double height;
    double width;

    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, Vector3f centerpoint, double height, double width, Float radiusZ) {
        super(shaderModuleDataList, vertices, color, centerpoint, height, width);
        this.radiusZ = radiusZ;
        this.centerpoint = centerpoint;
        this.height = height;
        this.width = width;
        createEllipsoid();
        setupVAOVBO();
    }

    public void createBox(){
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //titik 1 kiri atas belakang
        temp.x = (float)(centerpoint.x - width);
        temp.y = (float)(centerpoint.y + height);
        temp.z = centerpoint.z - radiusZ;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 2 kiri bawah belakang
        temp.x = (float)(centerpoint.x - width);
        temp.y = (float)(centerpoint.y - height);
        temp.z = centerpoint.z - radiusZ;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 3 kanan bawah belakang
        temp.x = (float)(centerpoint.x + width);
        temp.y = (float)(centerpoint.y - height);
        temp.z = centerpoint.z - radiusZ;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 4 kanan atas belakang
        temp.x = (float)(centerpoint.x + width);
        temp.y = (float)(centerpoint.y + height);
        temp.z = centerpoint.z - radiusZ;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 5 kiri atas depan
        temp.x = (float)(centerpoint.x - width);
        temp.y = (float)(centerpoint.y + height);
        temp.z = centerpoint.z + radiusZ;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 6 kiri bawah depan
        temp.x = (float)(centerpoint.x - width);
        temp.y = (float)(centerpoint.y - height);
        temp.z = centerpoint.z + radiusZ;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 7 kanan bawah depan
        temp.x = (float)(centerpoint.x + width);
        temp.y = (float)(centerpoint.y - height);
        temp.z = centerpoint.z + radiusZ;
        tempVertices.add(temp);
        temp = new Vector3f();
        //Titik 8 kanan atas depan
        temp.x = (float)(centerpoint.x + width);
        temp.y = (float)(centerpoint.y + height);
        temp.z = centerpoint.z + radiusZ;
        tempVertices.add(temp);

        //kotak belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(3));

        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));

        //kotak depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(7));

        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));

        // kotak atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(6));

        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(6));

        // kotak bawah
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(7));

        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(7));

        // kotak kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));

        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));

        // kotak kanan
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(7));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
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

    public void createHyperboloid2(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float)((1/Math.cos(v)) * Math.cos(u));
                float y = 0.5f * (float)((1/Math.cos(v)) * Math.sin(u));
                float z = 0.5f * (float)(Math.tan(v));
                temp.add(new Vector3f(x, z, y));
            }
        }
        vertices = temp;
    }

    public void createHyperboloid1(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI/2; u<= Math.PI/2; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.tan(v) * Math.cos(u));
                float y = 0.5f * (float)(Math.tan(v) * Math.sin(u));
                float z = 0.5f * (float)(1/Math.cos(v));
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;
    }

    public void createEllipticCone(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.cos(u) * v);
                float y = 0.5f * (float)(Math.sin(u) * v);
                float z = 0.5f * (float)(v);
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;
    }

    public void createEllipticParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 0; v<= Math.PI; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.cos(u) * v);
                float y = 0.5f * (float)(Math.sin(u) * v);
                float z = (float)(Math.pow(v, 2));
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;
    }

    public void createHyperboloidParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 0; v<= Math.PI; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.tan(u) * v);
                float y = 0.5f * (float)((1/Math.cos(u)) * v);
                float z = (float)(Math.pow(v, 2));
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;
    }
}

