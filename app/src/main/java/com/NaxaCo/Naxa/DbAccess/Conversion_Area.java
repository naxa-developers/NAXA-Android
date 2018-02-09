package com.NaxaCo.Naxa.DbAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by cri_r on 04/02/2018.
 */

public class Conversion_Area extends SQLiteOpenHelper {
    static String Db_name="conversion_area";
    static int version=1;
    String create_Table="CREATE TABLE if not exists `conversion_area_table` " +
            "( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " `name` TEXT NOT NULL, " +
            "`saveImage` BLOB NOT NULL )";
    public Conversion_Area(Context context) {
        super(context,Db_name,null,version);
        getWritableDatabase().execSQL(create_Table);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertInfo(ContentValues contentValues){
        getWritableDatabase().insert("conversion_area_table","",contentValues);
    }

    public Conversion_Area_Dto getInfo(){
        String sql="select * from conversion_area_table";
        Cursor cursor=getWritableDatabase().rawQuery(sql,null);
        Conversion_Area_Dto conversion_area_dto=new Conversion_Area_Dto();
        while (cursor.moveToNext()){
            conversion_area_dto.setId(cursor.getInt(cursor.getColumnIndex("id")));
            conversion_area_dto.setName(cursor.getString(cursor.getColumnIndex("name")));
            conversion_area_dto.setImage(cursor.getBlob(cursor.getColumnIndex("saveImage")));
        }
        cursor.close();
        return conversion_area_dto;
    }

}
