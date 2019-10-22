package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SQLiteActivity extends AppCompatActivity {
    private static final String TABALENAME = "student";
    private static final String CREATETABLE = "CREATE TABLE " + TABALENAME
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, age INTEGER)";

    private RecyclerView mRecyclerView;
    private MyAdapter3 mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG = "SQLiteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        //insertSQL(getData());
        initData();
        initView();
    }

    public void insertSQL(StudentEntity se) {
        MySQLiteOpenHelper mysql = new MySQLiteOpenHelper(this, "student.db", 1);
        SQLiteDatabase database = mysql.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", se.getName());
        values.put("age", se.getAge());
        database.insert("student", null, values);
        database.close();
    }

    public void insertSQL(ArrayList<StudentEntity> aSe) {
        MySQLiteOpenHelper mysql = new MySQLiteOpenHelper(this, "student.db", 1);
        SQLiteDatabase database = mysql.getWritableDatabase();

        for (int i=0; i<aSe.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("name", aSe.get(i).getName());
            values.put("age", aSe.get(i).getAge());
            database.insert("student", null, values);
        }

        database.close();
    }

    public ArrayList<StudentEntity> quaryAll() {
        ArrayList<StudentEntity> studentlist = new ArrayList<StudentEntity>();
        studentlist.clear();
        MySQLiteOpenHelper mysql = new MySQLiteOpenHelper(this, "student.db", 1);
        SQLiteDatabase database = mysql.getWritableDatabase();
        Cursor cursor = database.query(TABALENAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        do {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            StudentEntity se = new StudentEntity(name, age);
            studentlist.add(se);
        } while (cursor.moveToNext());
        database.close();
        Log.d(TAG, studentlist.toString());

        return studentlist;
    }

    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyAdapter3(quaryAll());
        mAdapter.setOnItemClickListener(new MyAdapter3.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SQLiteActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(SQLiteActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.sqlite_rv);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private ArrayList<StudentEntity> getData() {
        ArrayList<StudentEntity> mList = new ArrayList<StudentEntity>();
        mList.add(new StudentEntity("张三",12));
        mList.add(new StudentEntity("李四",15));
        mList.add(new StudentEntity("王五",18));
        mList.add(new StudentEntity("赵六",22));
        mList.add(new StudentEntity("麻子", 25));

        return mList;
    }
}
