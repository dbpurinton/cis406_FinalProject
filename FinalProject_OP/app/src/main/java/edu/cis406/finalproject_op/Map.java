package edu.cis406.finalproject_op;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by geforce on 11/24/2015.
 */
public class Map {
    private SpriteSheet sp;
    private Context context;
    private ArrayList<Block> blocks;
    private float maxY=0;
    private float startX=10000.f;
    public Map(Context context,SpriteSheet sp,int  MapFile){
        this.context=context;
        this.sp=sp;
        loadMap(MapFile);

    }
    public void Draw(GL10 gl, Camera camera){
        for(int i=0;i<blocks.size();i++){
            Block blk= blocks.get(i);
            if(blk.x+64>=camera.getX() && blk.x<=camera.getX()+camera.getWidth()
                     && blk.y>= camera.getY() && blk.y<=camera.getY()+camera.getHeight()) {
                sp.drawSprite(gl, blk.x-camera.getX(), blk.y-camera.getY(), 64, 64, blk.imgx, blk.imgy);
            }
        }

    }


    public void loadMap(int MapFile){

        try {
            InputStream in = context.getResources().openRawResource(MapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            Log.d("Map", "LOADING MAP FILE");
            blocks = new ArrayList<Block>();
            String line =br.readLine();
            while (line!= null) {
                Block blk = new Block();

                for (int i = 0; i < line.length(); i++) {
                    if(i+4>=line.length()-1){
                        break;
                    }
                    if (line.substring(i, i+5).equalsIgnoreCase("posx=")) {
                        blk.x =( Float.valueOf(getValue(i + 5, line)))*2;
                    //  Log.d("BLOCK X", String.valueOf(blk.x));

                  }
                    if (line.substring(i, i+5).equalsIgnoreCase("posy=")) {
                        blk.y = (Float.valueOf(getValue(i + 5, line)))*2;
                        if(blk.y>maxY){
                            maxY=blk.y;
                        }
                  //      Log.d("BLOCK Y", String.valueOf(blk.y));
                    }

                    if (line.substring(i, i+5).equalsIgnoreCase("imgx=")) {
                        blk.imgx = Integer.valueOf(getValue(i + 5, line))/32;
                    }
                    if (line.substring(i, i+5).equalsIgnoreCase("imgy=")) {
                        blk.imgy = Integer.valueOf(getValue(i + 5, line))/32;

                    }
                   if (i+8<=line.length()-1) {
                       if (line.substring(i, i + 8).equalsIgnoreCase("blocked=")) {
                             if(getValue(i+8,line).equalsIgnoreCase("true")){
                                blk.blocked=true;
                                 if(blk.x<startX){
                                     startX=blk.x;
                                 }

                            }else{
                              blk.blocked=false;
                            }

                       }
                   }
                   }
                blocks.add(blk);
                line =br.readLine();
            }
        }catch (Exception e){
            Log.d("Map", "Error loading map file");
        }
        Log.d("STARTX",String.valueOf(startX));
    }

    private String getValue(int pos, String line){
        String value="";
        for (int i = pos; i < line.length()-1 && !line.substring(i,i+ 1).equalsIgnoreCase(";"); i++){
            value = value + line.substring(i,i+ 1);
        }
        return value;
    }
    public float getMaxY(){
        return maxY;
    }
    public float getStartX(){
        return startX;
    }
    public ArrayList<Block> getBlocks(){
        return blocks;
    }
}
class Block{
    public float x;
    public float y;
    public int imgx,imgy;
    public boolean blocked=false;

}
