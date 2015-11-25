package edu.cis406.finalproject_op;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11Ext;

/**
 * Created by geforce on 11/24/2015.
 */
public class Sprite {
    private float posx=10,posy=10;//position the image will be drawn to
    private float width=96,height=96;//height/width to be drawn on the screen
    private int spriteX=0,spriteY=2; // the x,y position of the image from sprite sheet


    private SpriteSheet spriteSheet;
    public Sprite(SpriteSheet sp){
        this.spriteSheet=sp;
    }
    public Sprite(SpriteSheet sp,float x,float y){
        this.spriteSheet=sp;
        this.posx=x;
        this.posy=y;
    }
    public Sprite(SpriteSheet sp,float x,float y,float width,float height){
        this.spriteSheet=sp;
        this.posx=x;
        this.posy=y;
        this.width=width;
        this.height=height;
    }
    public void Draw(GL10 gl) {

      float vertcords[]={posx,posy,0.f,
                         posx+width,posy,0.f,
                         posx,posy+height,0.f,
                         posx+width,posy+height,0.f};


        float sx=(1.0f/spriteSheet.getTextureWidth())*spriteSheet.getSpriteWidth()*spriteX;
        float sy=(1.0f/spriteSheet.getTextureHeight())*spriteSheet.getSpriteHeight()*spriteY;
        float sw=(1.0f/spriteSheet.getTextureWidth())*spriteSheet.getSpriteWidth();
        float sh=(1.0f/spriteSheet.getTextureHeight())*spriteSheet.getSpriteHeight();
        float textcords[]={
            sx,sy,
                sx+sw,sy,
                sx,sy+sh,
                sx+sw,sy+sh};

        ByteBuffer buf = ByteBuffer.allocateDirect(vertcords.length * 4);
        buf.order(ByteOrder.nativeOrder());
        FloatBuffer vertex  = buf.asFloatBuffer();
        vertex.put(vertcords);
        vertex.position(0);

        ByteBuffer   buff = ByteBuffer.allocateDirect(textcords.length * 4);
        buff.order(ByteOrder.nativeOrder());
        FloatBuffer textVert  = buff.asFloatBuffer();
        textVert .put(textcords);
        textVert .position(0);

        int txtid=spriteSheet.getTextureIDs();
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, txtid);



       gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
       gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textVert);
       gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);

       gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertcords.length / 3);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

    }

//getters and setters
    public float getX(){
        return posx;
    }
    public float getY(){
        return posy;
    }
    public float getWidth(){return width;}
    public float getHeight(){return height;}

    public void setX(int x){
        this.posx=x;
    }
    public void setY(int y){
        this.posy=y;
    }
    public void setWidth(float width){
        this.width=width;
    }
    public void setHeight(float height){
        this.height=height;
    }
    public int getSpriteX(){
        return  spriteX;
    }
    public void setSpriteX(int x){
        spriteX=x;
    }
    public int getSpriteY(){
        return  spriteY;
    }
    public void setSpriteY(int y){
        spriteY=y;
    }
}
