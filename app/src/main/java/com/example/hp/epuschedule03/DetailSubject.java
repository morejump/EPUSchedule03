package com.example.hp.epuschedule03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.hp.epuschedule03.Database.Subject;
import com.example.hp.epuschedule03.Database.passSubject;
import com.example.hp.epuschedule03.Model.CustomAdapter;
import com.example.hp.epuschedule03.Model.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailSubject extends AppCompatActivity {
    private ListView listView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_subject);
        init();
    }
    private void init(){
        listView= (ListView) findViewById(R.id.listView);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        List<passSubject> list = (List<passSubject>) bundle.getSerializable("key");
        for (int i=0;i<list.size();i++){
            Log.d("bug", "init: "+list.get(i).getTenMH());
        }
        adapter= new MyAdapter(list,this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
