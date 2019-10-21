package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SQLiteActivity extends AppCompatActivity {
    private static final String TABALENAME = "student";
    private MySQLiteOpenHelper myOpenHlper;
    private SQLiteDatabase db;
    private Cursor mCursor;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG = "SQLiteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        initData();
        initView();
    }

    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyAdapter3(getData());
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
