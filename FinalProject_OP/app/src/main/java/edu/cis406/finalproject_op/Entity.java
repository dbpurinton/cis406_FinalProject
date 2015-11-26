package edu.cis406.finalproject_op;

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
       super(sp,x,y);
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
    public void Update(){
        super.setX(super.getX() + 2);
        if(checkCollisionFalling()){
            super.setY(super.getY()+2);
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
           if(super.getX()+super.getWidth() >=blk.x && super.getX()<=blk.x+64  && super.getX()>=blk.x && super.getY()+super.getHeight()>= blk.y   && super.getY()+super.getHeight()<= blk.y+10){
               return false;
           }
        }

        return true;//is falling
    }



}
