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
    private float posx=10,posy=10;
    private float spriteHeight=32,spriteWidth=32;
    private float spriteX=0,spriteY=0;
    private SpriteSheet spriteSheet;
    public Sprite(SpriteSheet sp){
        this.spriteSheet=sp;

        spriteHeight=(1.f/sp.getTextureHeight())*32;

        spriteWidth=(1.f/sp.getTextureWidth())*32;
    }
    public void Draw(GL10 gl) {
        //   spriteSheet.draw(gl);
        int txtid=spriteSheet.getTextureIDs()[0];
        gl.glBindTexture(GL10.GL_TEXTURE_2D, txtid);
        gl.glEnable(GL10.GL_TEXTURE_2D);

      float vertcords[]={posx,posy,0.f,
                         posx+256f,posy,0.f,
                         posx,posy+256.f,0.f,
                         posx+256,posy+256.f,0.f};
        float textcords[]={
            0.f,1.f,
            0.f,0.f,
            1.f,1.f,
            1.f,0.f};
      // GLES20.glUniform1f(txtid,0);
        gl.glColor4f(0.f, 1.f, 0.f, 1f);
        ByteBuffer buf = ByteBuffer.allocateDirect(vertcords.length * 4);
        buf.order(ByteOrder.nativeOrder());
        FloatBuffer vertex  = buf.asFloatBuffer();
        vertex.put(vertcords);
        vertex.position(0);

        buf = ByteBuffer.allocateDirect(textcords.length * 4);
        buf.order(ByteOrder.nativeOrder());
        FloatBuffer textVert  = buf.asFloatBuffer();
        textVert .put(textcords);
        textVert .position(0);


        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
      //  gl.glVertexPointer(3, GL10.GL_FLOAT, 0, textVert);


      //  GLES20.glVertexAttribPointer(txtid,4,GLES20.GL_FLOAT,false,txtid,textVert);
       gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textVert);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertcords.length / 3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

    }


    public float getX(){
        return posx;
    }
    public float getY(){
        return posy;
    }
    public void setX(int x){
        this.posx=x;
    }
    public void setY(int y){
        this.posy=y;
    }
}
