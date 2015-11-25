package edu.cis406.finalproject_op;

/**
 * Created by geforce on 11/25/2015.
 */
public class Camera {
    private float x,y,width,height;
    public Camera(float x, float y,float width,float height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;

    }


    public float getX(){
        return  x;
    }
    public float getY(){
        return y;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return  height;
    }
    public void setX(float x){
        this.x=x;
    }
    public void setY(float y){
        this.y=y;
    }
    public void setWidth(float width){
        this.width=width;
    }
    public void setHeight(float height){
        this.height=height;
    }


}
