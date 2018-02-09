package com.NaxaCo.Naxa.DbAccess.Blob;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by cri_r on 08/02/2018.
 */

public class GetBitmap {
    public static Bitmap getBitmap(byte[] byteArray){
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
    }
}
