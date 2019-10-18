package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mAdapter = new MyAdapter(getData());
        mAdapter = new MyAdapter1(getData1());
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 20; i++) {
            data.add(i + temp);
        }

        return data;
    }

    private Map<String, String> getData1() {
        String json = "{\n" +
                "\t\"ID\": \"QmXzYkFL93SVgteZJr2TtXNozaariQvPX6sEZe1Hb4EjAE\",\n" +
                "\t\"PublicKey\": \"CAASpgIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDJ+f2hI30g0fto2vF8+W32WM8ehFxjqRm/oJXosqf7VOI8we1/0R/gO1MlcdWiUDCk0BJfZSc5tTS2thLevaMhZTLNfHyS5KALMd65gBmAKQa+kZqtHvQpmIk6Er+ZrpcG4HQvXR4sAF0LScFL6dbWWEtPpG4vATS9XCj9+gXb69EOgxqm71q28a/2YUOtTd9kk9ANHVGv3vlT8jATOyA5ZL4ZaKxrLXjkAw1yS+rMTK1i11uc8VeSjWHBZPMEtrEU/yXRhC/idJba6IoQk8JS6sTM6cD2V+9Obm4Y5u6wfPg0rezVhF9DKbmeDR0ZkJKz0AEkq2JT/xglkd5RieErAgMBAAE=\",\n" +
                "\t\"Addresses\": [\n" +
                "\t\t\"/ip4/127.0.0.1/tcp/4001/ipfs/QmXzYkFL93SVgteZJr2TtXNozaariQvPX6sEZe1Hb4EjAE\",\n" +
                "\t\t\"/ip4/192.168.3.89/tcp/4001/ipfs/QmXzYkFL93SVgteZJr2TtXNozaariQvPX6sEZe1Hb4EjAE\",\n" +
                "\t\t\"/ip4/192.168.122.1/tcp/4001/ipfs/QmXzYkFL93SVgteZJr2TtXNozaariQvPX6sEZe1Hb4EjAE\",\n" +
                "\t\t\"/ip6/::1/tcp/4001/ipfs/QmXzYkFL93SVgteZJr2TtXNozaariQvPX6sEZe1Hb4EjAE\"\n" +
                "\t],\n" +
                "\t\"AgentVersion\": \"go-ipfs/0.4.22/\",\n" +
                "\t\"ProtocolVersion\": \"ipfs/0.1.0\"\n" +
                "}";


        Map<String, String> map = new HashMap<String, String>();
        try {
            JSONObject object = new JSONObject(json);
            JSONArray array = new JSONArray(object.getString("Addresses"));
            Log.d(TAG, array.toString());
            String addr = "";
            for (int i=0; i<array.length(); i++) {
                addr += array.get(i) + "\n";
            }
            Log.d(TAG, addr);
            map.put("ID", object.getString("ID"));
            map.put("PublicKey", object.getString("PublicKey"));
            map.put("Addresses", addr);
            map.put("AgentVersion", object.getString("AgentVersion"));
            map.put("ProtocolVersion", object.getString("ProtocolVersion"));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
