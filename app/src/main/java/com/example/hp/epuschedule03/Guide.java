package com.example.hp.epuschedule03;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

public class Guide extends AppCompatActivity implements View.OnClickListener{
    Button btn1, btn2, btn3;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        init();
    }
    private void init(){
        btn1= (Button) findViewById(R.id.btnStep1);
        btn2= (Button) findViewById(R.id.btnStep2);
        btn3= (Button) findViewById(R.id.btnStep3);
        image= (ImageView) findViewById(R.id.imgStep);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStep1:
            {
             image.setImageDrawable(getResources().getDrawable(R.drawable.buocmot));
                break;
            }
            case R.id.btnStep2:
            {
                image.setImageDrawable(getResources().getDrawable(R.drawable.buochai));
                break;
            }
            case R.id.btnStep3:
            {
                image.setImageDrawable(getResources().getDrawable(R.drawable.buocba));
                break;
            }
        }
    }
}
