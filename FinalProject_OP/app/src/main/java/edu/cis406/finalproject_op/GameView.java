package edu.cis406.finalproject_op;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by geforce on 11/24/2015.
 */
public class GameView extends GLSurfaceView implements GLSurfaceView.Renderer {
    private  Sprite sprite;
    private Map map;
    private Camera camera;
    TextRenderer txt;
    long startTime = System.nanoTime();
    int frames = 0;
    public GameView(Context context) {
        super(context);
        setEGLContextClientVersion(1);
        setRenderer(this);
}

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.f, 0.f, 0.f, 1.f);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_ALPHA_TEST);
        gl.glAlphaFunc(GL10.GL_GREATER, 0.01f);
        //gl.glEnable(GL10.GL_BLEND);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glClearDepthf(1.f);
        gl.glShadeModel(GL10.GL_SMOOTH);
        //load shit here
       sprite = new Sprite(new SpriteSheet(this.getContext(),R.mipmap.bad1,4,3,gl));
        map = new Map(this.getContext(),new SpriteSheet(this.getContext(),R.mipmap.sp2,12,10,gl),R.raw.map2);
        txt= new TextRenderer(this.getContext(),gl,"Hello world",10,10);
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
        sprite.setY(map.getMaxY()-128);


    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
      //  gl.glLoadIdentity();
        frames++;
        if(System.nanoTime() - startTime >= 1000000000) {
           txt.setText(String.valueOf(frames));
            frames = 0;
            startTime = System.nanoTime();
        }
        map.Draw(gl,camera);
        sprite.Draw(gl, camera);
        camera.setX(camera.getX()+2);
        txt.Draw(gl);
    }
}
