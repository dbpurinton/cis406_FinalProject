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
    public Map(Context context,SpriteSheet sp,int  MapFile){
        this.context=context;
        this.sp=sp;
        loadMap(MapFile);

    }
    public void Draw(GL10 gl){
        for(int i=0;i<blocks.size();i++){
            Block blk= blocks.get(i);
            sp.drawSprite(gl,blk.x,blk.y,32,32,blk.imgx,blk.imgy);
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
                        blk.x =( Float.valueOf(getValue(i + 5, line))+600);
                    //  Log.d("BLOCK X", String.valueOf(blk.x));
                  }
                    if (line.substring(i, i+5).equalsIgnoreCase("posy=")) {
                        blk.y = (Float.valueOf(getValue(i + 5, line))+600);
                  //      Log.d("BLOCK Y", String.valueOf(blk.y));
                    }

                    if (line.substring(i, i+5).equalsIgnoreCase("imgx=")) {
                        blk.imgx = Integer.valueOf(getValue(i + 5, line))/32;
                    }
                    if (line.substring(i, i+5).equalsIgnoreCase("imgy=")) {
                        blk.imgy = Integer.valueOf(getValue(i + 5, line))/32;
                    }
                //    if (i+9>=line.length()-1 && line.substring(i, i+8).equalsIgnoreCase("blocked=")) {
                     //   if(getValue(i+8,line).equalsIgnoreCase("true")){
                       //     blk.blocked=true;
                       // }else{
                         //   blk.blocked=false;
                       // }
                //    }
                }
                blocks.add(blk);
                line =br.readLine();
            }
        }catch (Exception e){
            Log.d("Map", "Error loading map file");
        }
    }

    private String getValue(int pos, String line){
        String value="";
        for (int i = pos; i < line.length()-1 && !line.substring(i,i+ 1).equalsIgnoreCase(";"); i++){
            value = value + line.substring(i,i+ 1);
        }
        return value;
    }
}
class Block{
    public float x;
    public float y;
    public int imgx,imgy;
    public boolean blocked=false;

}
