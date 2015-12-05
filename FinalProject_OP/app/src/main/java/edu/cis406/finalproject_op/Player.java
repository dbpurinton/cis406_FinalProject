package edu.cis406.finalproject_op;

/**
 * Created by geforce on 12/2/2015.
 */
public class Player  extends Entity{

    public  Player(SpriteSheet sp, GameView gv){
        super(sp,gv);

    }

    public  Player(SpriteSheet sp, GameView gv,float x,float y){
        super(sp,gv, x, y);
       // this.gv = gv;
    }
    public  Player(SpriteSheet sp, GameView gv,float x,float y,float width,float height){
        super(sp,gv, x, y, width, height);
        //this.gv = gv;
    }
}
