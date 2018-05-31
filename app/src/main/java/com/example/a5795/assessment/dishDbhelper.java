package com.example.a5795.assessment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 52437 on 24/05/2018.
 */

public class dishDbhelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=4;

    private static final String DATABASE_NAME="DishDB";

    public dishDbhelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_DISHES="CREATE TABLE "+Dishes.TABLE+" IF NOT EXISTS ("
                +Dishes.Dish_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +Dishes.Dish_Name+" TEXT, "
                +Dishes.Materials+" TEXT, "
                +Dishes.Dishpictures+" TEXT, "
                +Dishes.CateID+" INTEGER)"
                +"CREATE TABLE "+DishStep.TABLE+" IF NOT EXISTS ("
                +DishStep.STEP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +DishStep.DESCRIPTION+" TEXT, "
                +"FORGIN KEY "+Dishes.Dish_ID+" FROM "+Dishes.TABLE+")";
        db.execSQL(CREATE_TABLE_DISHES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Dishes.TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+DishStep.TABLE);
        onCreate(db);
    }
}
