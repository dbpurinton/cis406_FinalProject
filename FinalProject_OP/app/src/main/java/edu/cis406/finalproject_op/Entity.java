package edu.cis406.finalproject_op;

/**
 * Created by geforce on 11/24/2015.
 */
public class Entity extends Sprite {
    public Entity(SpriteSheet sp){
        super(sp);
    }
    public Entity(SpriteSheet sp,float x,float y){
       super(sp,x,y);
    }
    public Entity(SpriteSheet sp,float x,float y,float width,float height){
        super(sp,x,y,width,height);
    }


}
