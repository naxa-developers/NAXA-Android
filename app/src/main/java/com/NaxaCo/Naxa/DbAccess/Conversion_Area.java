package com.NaxaCo.Naxa.DbAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by cri_r on 04/02/2018.
 */

public class Conversion_Area extends SQLiteOpenHelper {
    static String Db_name="conversion_area";
    static int version=1;
    String create_Table="CREATE TABLE if not exists `conversion_area` " +
            "( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " `name` TEXT NOT NULL, " +
            "`saveImage` BLOB NOT NULL )";
    public Conversion_Area(Context context) {
        super(context,Db_name,null,version);
        getWritableDatabase().execSQL(create_Table);
        Toast.makeText(context,"Database Created sucessfully",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertInfo(ContentValues contentValues){
        getWritableDatabase().insert("conversion_area","",contentValues);
    }

    public void getInfo(){
        String sql="select * from conversion_area";
        Cursor cursor=getWritableDatabase().rawQuery(sql,null);
        ArrayList<Conversion_Area_Dto> list=new ArrayList<Conversion_Area_Dto>();
        while (cursor.moveToNext()){
            Conversion_Area_Dto conversion_area_dto=new Conversion_Area_Dto();
            conversion_area_dto.setId(cursor.getInt(cursor.getColumnIndex("id")));
            conversion_area_dto.setName(cursor.getString(cursor.getColumnIndex("name")));
            conversion_area_dto.setImage(cursor.getBlob(cursor.getColumnIndex("saveImage")));
            list.add(conversion_area_dto);
        }
    }

}
