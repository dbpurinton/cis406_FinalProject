package edu.cis406.finalproject_op;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by geforce on 12/2/2015.
 */
public class Player  extends Entity{
    long spriteTime = System.nanoTime();
    private int jumpcount;
    private float jumptoheight;
    private boolean topReached;
    private boolean dashing=false;
    private SpriteSheet dashSprite;
    private int dashLength=14;
    private int dashX=2,dashY=3;
    public  Player(SpriteSheet sp, SpriteSheet DashSprite, GameView gv){
        super(sp,gv);
        this.dashSprite=DashSprite;
        init();

    }

    public  Player(SpriteSheet sp, SpriteSheet DashSprite,  GameView gv,float x,float y){
        super(sp,gv, x, y);
        this.dashSprite=DashSprite;
        init();
       // this.gv = gv;
    }
    public  Player(SpriteSheet sp, SpriteSheet DashSprite,  GameView gv,float x,float y,float width,float height){
        super(sp,gv, x, y, width, height);
        this.dashSprite=DashSprite;
        //this.gv = gv;
        init();
    }
    private void init(){

        topReached = false;
        jumptoheight = 0;
        jumpcount = 2; // start game with player NOT already jumping.
    }

    @Override
    public void Draw(GL10 gl, Camera cam){
         this.Update();
  //     super.setX(super.getX() + cam.getX());
        super.setX(super.getX() + super.getGv().getCameraXPos());

        if(!dashing) {
            super.Update();
            super.Draw(gl, cam);
        }else{
            dashSprite.drawSprite(gl,super.getX()-cam.getX()-128,super.getY()-cam.getY()-64,288,160,dashX,dashY);

        }
    }
    @Override
    public void Update()
    {


if(!dashing) {
    if (System.nanoTime() - spriteTime >= 1100000) // 1000000000
    {
        if (super.checkCollisionFalling()) {
            super.setSpriteX(1);
        } else {
            super.setSpriteX(super.getSpriteX() - 1);
            if (super.getSpriteX() < 0) {
                super.setSpriteY(super.getSpriteY() - 1);
                if (super.getSpriteY() < 0) {
                    super.setSpriteY(2);

                }
                super.setSpriteX(8);
            }
        }

    }
    }else {
        if (System.nanoTime() - spriteTime >= 16700000) { // 1000000000
            dashLength--;
            dashX--;
            if(dashX<0){
                dashX=3;
                dashY--;
                if(dashY<0){
                    dashY=3;
                    dashing=false;
                    dashX=2;
                    super.getGv().setMovementSpeed(super.getGv().getMovementSpeed()-300);
                }
            }
            if(dashLength<0){
               // dashLength=14;
               // dashing=false;

            }
        }

    }

        spriteTime = System.nanoTime();

    }
    public void Dash(){
        this.dashing=true;
        super.getGv().setMovementSpeed(super.getGv().getMovementSpeed()+300);
    }
    public boolean isDashing(){
        return dashing;
    }


}
