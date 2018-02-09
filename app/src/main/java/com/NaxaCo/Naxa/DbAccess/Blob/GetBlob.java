package com.NaxaCo.Naxa.DbAccess.Blob;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by cri_r on 08/02/2018.
 */

public class GetBlob {

   public static byte[] getBlob(Bitmap bitmap){
       ByteArrayOutputStream bos=new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
       byte[] bArray=bos.toByteArray();
       return bArray;
   }
}
