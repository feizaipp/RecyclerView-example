package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.wandersnail.fileselector.FileSelector;
import cn.wandersnail.fileselector.OnFileSelectListener;

public class Main3Activity extends CheckPermissionsActivity {

    private static final String TAG = "Main3Activity";

    Button btnSelectSingleFile;
    Button btnSelectMultiFile;
    Button btnSelectSingleDir;
    Button btnSelectMultiDir;
    FileSelector selector;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btnSelectSingleFile = findViewById(R.id.btnSelectSingleFile);
        btnSelectMultiFile = findViewById(R.id.btnSelectMultiFile);
        btnSelectSingleDir = findViewById(R.id.btnSelectSingleDir);
        btnSelectMultiDir = findViewById(R.id.btnSelectMultiDir);
        tvResult = findViewById(R.id.tvResult);

        selector = new FileSelector().setScreenOrientation(false).showHiddenFiles(true);
        selector.setTitle("文件选择器");
        selector.setOnFileSelectListener(listener);

        btnSelectSingleFile.setOnClickListener(listenersinglefile);
        btnSelectMultiFile.setOnClickListener(listenermultifile);
        btnSelectSingleDir.setOnClickListener(listenersingledir);
        btnSelectMultiDir.setOnClickListener(listenermultidir);
    }

    OnFileSelectListener listener = new OnFileSelectListener() {
        @Override
        public void onFileSelect(int requestCode, List<String> paths) {
            Log.d(TAG, "requestCode: " + requestCode);
            tvResult.setText("");
            for (int i=0; i<paths.size(); i++) {
                tvResult.append(paths.get(i) + "\n");
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selector.onActivityResult(requestCode, resultCode, data);
    }

    Button.OnClickListener listenersinglefile = new Button.OnClickListener() {
        public void onClick(View v){
            selector.setMultiSelectionEnabled(true);
            selector.setSelectionMode(FileSelector.FILES_ONLY);
            selector.select(Main3Activity.this, 2);
        }
    };

    Button.OnClickListener listenermultifile = new Button.OnClickListener() {
        public void onClick(View v){
            selector.setMultiSelectionEnabled(true);
            selector.setSelectionMode(FileSelector.FILES_ONLY);
            selector.select(Main3Activity.this, 1);
        }
    };

    Button.OnClickListener listenersingledir = new Button.OnClickListener() {
        public void onClick(View v){
            selector.setMultiSelectionEnabled(true);
            selector.setSelectionMode(FileSelector.DIRECTORIES_ONLY);
            selector.select(Main3Activity.this, 3);
        }
    };

    Button.OnClickListener listenermultidir = new Button.OnClickListener() {
        public void onClick(View v){
            selector.setMultiSelectionEnabled(true);
            selector.setSelectionMode(FileSelector.DIRECTORIES_ONLY);
            selector.select(Main3Activity.this, 4);
        }
    };
}
