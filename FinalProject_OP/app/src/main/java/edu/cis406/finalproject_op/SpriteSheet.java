package edu.cis406.finalproject_op;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by geforce on 11/24/2015.
 */
public class SpriteSheet {
    private int sheetRows=1;
    private int sheetCols=1;
    public int width,height;
   // private GL10 gl;
    int []textureids=null;

    Context context;
    public SpriteSheet (Context context,int resourceID,int rows, int cols, GL10 gl){
         sheetRows=rows;
        sheetCols=cols;
        //this.gl=gl;
        this.context=context;
      loadTexture(resourceID,gl);

    }
    public  int[] loadTexture(int resourceID, GL10 gl){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled=false;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),resourceID,options);
        textureids = new int[1];
        gl.glGenTextures(1, textureids, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureids[0]);

       gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
       // gl.glTexImage2D(GL10.GL_TEXTURE_2D,0,GL10.GL_RGBA,bitmap.getWidth(),bitmap.getHeight(),0,GL10.GL_RGBA,GL10.GL_,bitmap);
       // gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL);
        width=bitmap.getWidth();
        height=bitmap.getHeight();
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
        return textureids;
    }
    public void drawSprite(GL10 gl,float x,float y,float width,float height, float imgx,float imgy){

            float vertcords[]={x,y,0.f,
                    x+width,y,0.f,
                    x,y+height,0.f,
                    x+width,y+height,0.f};


            float sx=(1.0f/getTextureWidth())*getSpriteWidth()*imgx;
            float sy=(1.0f/getTextureHeight())*getSpriteHeight()*imgy;
            float sw=(1.0f/getTextureWidth())*getSpriteWidth();
            float sh=(1.0f/getTextureHeight())*getSpriteHeight();
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

            int txtid=getTextureIDs();
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
    public int getTextureIDs(){
        return textureids[0];
    }
    public int getTextureWidth(){
        return width;
    }
    public int getTextureHeight(){
        return height;
    }
    public int getSheetCols(){
        return  sheetCols;
    }
    public int getSheetRows(){
        return sheetRows;
    }
    public int getSpriteWidth(){
        return  width/sheetCols;
    }
    public int getSpriteHeight(){
        return  height/sheetRows;
    }
}
