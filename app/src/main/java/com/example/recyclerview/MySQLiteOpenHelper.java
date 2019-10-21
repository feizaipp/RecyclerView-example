package com.example.recyclerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TABALENAME = "student";
    private static final String CREATETABLE = "CREATE TABLE " + TABALENAME
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, age INTEGER)";

    //构造方法，系统会自动创建数据库文件
    public MySQLiteOpenHelper(Context context, String name, int version){
        super(context,name,null,version);
    }

    //只有在第一次打开数据库的时候调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATETABLE);
    }

    //当数据库的版本发生变化的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新表结构或删除旧的表结构
        db.execSQL("DROP TABLE IF EXISTS "+TABALENAME);
        onCreate(db);
    }
}
