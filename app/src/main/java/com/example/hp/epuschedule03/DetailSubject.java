package com.example.hp.epuschedule03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.hp.epuschedule03.Database.Subject;
import com.example.hp.epuschedule03.Model.CustomAdapter;
import com.example.hp.epuschedule03.Model.MyAdapter;

import java.util.ArrayList;

public class DetailSubject extends AppCompatActivity {
    private ListView listView;
    private MyAdapter adapter;
    private ArrayList<Subject> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_subject);
    }
    private void init(){
        listView= (ListView) findViewById(R.id.listView);
        
    }
}
