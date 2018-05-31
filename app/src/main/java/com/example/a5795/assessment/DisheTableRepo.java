package com.example.a5795.assessment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 52437 on 24/05/2018.
 */

public class DisheTableRepo {
    private dishDbhelper dbhelper;
    public DisheTableRepo(Context context){
        dbhelper = new dishDbhelper(context);
    }

    public int insert(Dishes dishes){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Dishes.Dish_Name,dishes.dishname);
        values.put(Dishes.Materials,dishes.materials);
        values.put(Dishes.Dishpictures,dishes.dishpictures);
        values.put(Dishes.CateID,dishes.cateid);
        long dishes_id=db.insert(Dishes.TABLE,null,values);
        db.close();
        return (int)dishes_id;
    }

    public void delete(int dishes_id){
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        db.delete(Dishes.TABLE,Dishes.Dish_ID+"=?",new  String[]{
                String.valueOf(dishes_id)
        });
        db.close();
    }
    public ArrayList<HashMap<String,String>> getDishList(){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String selectQuery="SELETE "
                +Dishes.Dish_ID+","
                +Dishes.Dish_Name+","
                +Dishes.Materials+","
                +Dishes.Dishpictures
                +Dishes.CateID
                +" FROM "+Dishes.TABLE;
        ArrayList<HashMap<String,String>> dishesList=new ArrayList<HashMap<String,String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                HashMap<String,String> dish=new HashMap<String, String>();
                dish.put("dishID",cursor.getString(cursor.getColumnIndex(Dishes.Dish_ID)));
                dish.put("dishName",cursor.getString(cursor.getColumnIndex(Dishes.Dish_Name)));
                dishesList.add(dish);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dishesList;
    }

    public Dishes getDishByid(int id){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String selectQuery="SELETE "
                +Dishes.Dish_ID+","
                +Dishes.Dish_Name+","
                +Dishes.Materials+","
                +Dishes.Dishpictures+" FROM "+Dishes.TABLE
                +" WHERE "
                +Dishes.Dish_ID+"=?";
        int iCount=0;
        Dishes dish=new Dishes();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{
                String.valueOf(id)
        });
        if(cursor.moveToFirst()) {
            do {
                dish.dishid = cursor.getInt(cursor.getColumnIndex(Dishes.Dish_ID));
                dish.dishname = cursor.getString(cursor.getColumnIndex(Dishes.Dish_Name));
                dish.materials = cursor.getString(cursor.getColumnIndex(Dishes.Materials));
                dish.dishpictures = cursor.getString(cursor.getColumnIndex(Dishes.Dishpictures));
                dish.cateid=cursor.getInt(cursor.getColumnIndex(Dishes.CateID));
            }
            while (cursor.moveToNext());
        }
            cursor.close();
            db.close();
            return dish;
    }

}
