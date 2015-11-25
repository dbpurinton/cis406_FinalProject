package edu.cis406.finalproject_op;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by geforce on 11/24/2015.
 */
public class Map {
    private Sprite sprite;
    private Context context;
    public Map(Context context,int  MapFile){
        this.context=context;
        loadMap(MapFile);

    }
    public void Draw(){

    }


    public void loadMap(int MapFile){

        try {
            InputStream in = context.getResources().openRawResource(MapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line =br.readLine();
            while (line!= null) {
                Block blk = new Block();
/*
                for (int i = 0; i < line.length(); i++) {
                    if (line.substring(i, 5) == "posx=") {
                        blk.x = Float.valueOf(getValue(i + 5, line));
                        Log.d("BLOCK X", String.valueOf(blk.x));
                    }


                }*/
                line =br.readLine();
            }
        }catch (Exception e){
            Log.d("Map", "Error loading map file");
        }
    }

    private String getValue(int pos, String line){
        String value="";
        for (int i = pos; i < line.length() && line.substring(i, 1) != ";"; i++){
            value = value + line.substring(i, 1);
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
