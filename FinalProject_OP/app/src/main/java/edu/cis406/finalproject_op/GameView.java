package edu.cis406.finalproject_op;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by geforce on 11/24/2015.
 */
public class GameView extends GLSurfaceView implements GLSurfaceView.Renderer {
    private  Entity sprite;
    private Map map;
    private Camera camera;
    private int Score;
    private int jumpcount;
    private float jumptoheight;
    private boolean topReached;
    TextRenderer txt;
    TextRenderer textScore;
    long startTime = System.nanoTime();
    long spriteTime = System.nanoTime();
    int frames;
    public GameView(Context context) {
        super(context);
        setEGLContextClientVersion(1);
        setRenderer(this);
        Score = 0;
        topReached = false;
        jumptoheight = 0;
        jumpcount = 2; // start game with player NOT already jumping.
        frames = 0;
}

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.1f, 0.1f, 0.2f, 1.f);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_ALPHA_TEST);
        gl.glAlphaFunc(GL10.GL_GREATER, 0.01f);
        //gl.glEnable(GL10.GL_BLEND);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glClearDepthf(1.f);
        gl.glShadeModel(GL10.GL_SMOOTH);
        //load shit here

        map = new Map(this.getContext(),new SpriteSheet(this.getContext(),R.mipmap.sp3,12,10,gl),R.raw.map2);
        sprite = new Entity(new SpriteSheet(this.getContext(),R.mipmap.bad1,4,3,gl),this,map.getStartX()+3000,map.getMaxY()-512);
        txt= new TextRenderer(this.getContext(),gl,"1",10,10);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_ALPHA_TEST);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0.f, width, height, -1.0f, 0.0f, 1.0f);
        camera= new Camera(0,map.getMaxY()-height,width,height);
        sprite.setY(map.getMaxY() - 275);
        sprite.setX(map.getStartX() + 1675);
        camera.setX(map.getStartX() + 1550); // 1600 Location on the X plane that character starts.

    }

    //Implementation of the Renderer interface. Effectively the Renderer's onDrawFrame() method
    // can be considered analogous to a regular Thread's run() method.
    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
      //  gl.glLoadIdentity();
        frames++;

        if(System.nanoTime() - spriteTime >= 110000000) // 1000000000
        {

            switch (sprite.getSpriteX())
            {
                case 0: sprite.setSpriteX(1);
                    break;
                case 1: sprite.setSpriteX(2);
                    break;
                case 2: sprite.setSpriteX(0);
                    break;
                default: sprite.setSpriteX(0);
                    break;
            }
            spriteTime = System.nanoTime();
        }

        if(System.nanoTime() - startTime >= 1000000000) {
            txt.setText("FPS:"+String.valueOf(frames));
            frames = 0;
            startTime = System.nanoTime();
        }
        map.Draw(gl, camera);
        sprite.Draw(gl, camera);
        camera.setX(camera.getX() + 5);
        txt.Draw(gl);
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //Log.d("TOUCH EVENT", "SCREEN PREES");
        switch(e.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (jumpcount < 2)
                {
                    jumptoheight = sprite.getY() - 165.0f;
                    topReached = false;
                    jumpcount++;
                    //sprite.setY(sprite.getY() - 275.0f);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //** CODE **
                break;
            case MotionEvent.ACTION_UP:
                //** CODE **
                break;
        }
        return true;
    }
    public Map getMap(){
        return map;
    }

    public void resetJumpCount()
    {
        jumpcount = 0;
    }

    public int getJumpCount()
    {
        return jumpcount;
    }

    public float getJumpHeight()
    {
        return jumptoheight;
    }

    public boolean getTopReached()
    {
        return topReached;
    }

    public void setTopReached(boolean setTop)
    {
        topReached = setTop;
    }

    public void setJumpHeight(float jumph)
    {
        jumptoheight = jumph;
    }
}
