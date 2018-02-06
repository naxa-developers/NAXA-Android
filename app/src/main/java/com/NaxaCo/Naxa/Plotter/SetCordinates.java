package com.NaxaCo.Naxa.Plotter;

import java.util.ArrayList;

/**
 * Created by cri_r on 29/01/2018.
 */

public class SetCordinates {
    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        ArrayList<SetCordinates> xy=new ArrayList<SetCordinates>();
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean getLastX(float x){
        if(x<370 && x>320){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean getLastY(float y){
        if(y<220 && y>190){
            return true;
        }
        else{
            return false;
        }
    }

}
