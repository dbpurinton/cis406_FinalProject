package edu.cis406.finalproject_op;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by geforce on 11/24/2015.
 */
public class SpriteSheet {
    private int sheetRows=1;
    private int sheetCols=1;
   // private GL10 gl;
    int []textureids=null;
    Bitmap bitmap=null;
    Context context;
    public SpriteSheet (Context context,int resourceID,int rows, int cols, GL10 gl){
         sheetRows=rows;
        sheetCols=cols;
        //this.gl=gl;
        this.context=context;
      loadTexture(resourceID,gl);

    }
    public  int[] loadTexture(int resourceID, GL10 gl){
        bitmap = BitmapFactory.decodeResource(context.getResources(),resourceID);
        int []txtids = new int[1];
        gl.glGenTextures(1,txtids, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, txtids[0]);

       gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

      //  gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
       // gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
        GLUtils.texImage2D(GL10.GL_TEXTURE,0,bitmap,0);
        textureids=txtids;
        return txtids;
    }
    public void draw(GL10 gl){

    }
    public int[]getTextureIDs(){
        return textureids;
    }
    public int getTextureWidth(){
        return bitmap.getWidth();
    }
    public int getTextureHeight(){
        return bitmap.getHeight();
    }
    public int getSheetCols(){
        return  sheetCols;
    }
    public int getSheetRows(){
        return sheetRows;
    }

}
