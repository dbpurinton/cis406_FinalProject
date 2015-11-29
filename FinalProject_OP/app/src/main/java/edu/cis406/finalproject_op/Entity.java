package edu.cis406.finalproject_op;

import android.util.Log;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by geforce on 11/24/2015.
 */
public class Entity extends Sprite {
    private GameView gv;

    public Entity(SpriteSheet sp, GameView gv){
        super(sp);
        this.gv = gv;
    }

    public Entity(SpriteSheet sp, GameView gv,float x,float y){
       super(sp, x, y);
        this.gv = gv;
    }
    public Entity(SpriteSheet sp, GameView gv,float x,float y,float width,float height){
        super(sp, x, y, width, height);
        this.gv = gv;
    }

    @Override
    public void Draw(GL10 gl, Camera cam){
        this.Update();
        super.Draw(gl, cam);
    }
    public void Update()
    {
        super.setX(super.getX() + 5); // rate of moving forward.
        if(checkCollisionFalling()) // if in the air...
        {
            super.setY(super.getY() + 8); // rate of falling.
        }
            if (super.getY() > gv.getJumpHeight() && gv.getJumpCount() < 2)
            {
                super.setY(super.getY() - 16);
            }
            else {
                gv.setTopReached(true); // the top was reached, now just fall.
                // 6 is an arbitrary # to ensure jump height is lower (bigger #) than current Y.
                gv.setJumpHeight(super.getY() + 8);
            }
    }
    public boolean checkCollision(Sprite sp){
        if(super.getX()+super.getWidth()>=sp.getX() && super.getY()+super.getHeight()>=sp.getY() && super.getY()<=sp.getY()+sp.getHeight()){
            return true;

        }

        return false;
    }
    public boolean checkCollisionFalling(){
        ArrayList<Block>blks = gv.getMap().getBlocks();

        for(int i=0;i< blks.size();i++){
            Block blk = blks.get(i);
           if(super.getX()+super.getWidth() >=blk.x && super.getX()<=blk.x+64  && super.getX()>=blk.x && super.getY()+super.getHeight()>= blk.y   && super.getY()+super.getHeight()<= blk.y+10)
           {
               gv.setTopReached(false);
                   gv.resetJumpCount(); // jumpcount = 0
               return false; // not falling
           }
        }

        return true;//is falling
    }



}
