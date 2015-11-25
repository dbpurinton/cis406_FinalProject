package edu.cis406.finalproject_op;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by geforce on 11/24/2015.
 */
public class TextRenderer {
    String text;
    private Context context;
    private SpriteSheet sp;
    private GL10 gl;
    public TextRenderer(Context context,GL10 gl, String text,float x,float y){
        this.text=text;
        this.context=context;
        this.gl=gl;
        InitText(gl);
    }
    public void InitText(GL10 gl){

        Bitmap bitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_4444);

        Canvas canvas = new Canvas(bitmap);
        bitmap.eraseColor(0);

        Paint textPaint = new Paint();
        textPaint.setTextSize(64);
        textPaint.setAntiAlias(true);
        textPaint.setARGB(0xff, 0x00, 0x00, 0x00);

        canvas.drawText(text, 16,112, textPaint);
        sp=new SpriteSheet(context,bitmap,1,1,gl);

    }
    public void Draw(GL10 gl){
        sp.drawSprite(gl,100,100,256,256,0,0);
    }
    public void setText(String text){
        this.text=text;
        InitText(gl);
    }


}
