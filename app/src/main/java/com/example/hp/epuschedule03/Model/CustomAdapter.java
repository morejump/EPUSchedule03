package com.example.hp.epuschedule03.Model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.epuschedule03.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by HP on 12/15/2016.
 */

public class CustomAdapter extends BaseAdapter {
    ArrayList<Thu> list;
    Context context;
    LayoutInflater inflater;

    public CustomAdapter(ArrayList<Thu> list, Context context) { // constructor here :))
        this.list = list;
        this.context = context;
        inflater = inflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_on_list, parent, false);
        if (convertView!=null){

            TextView textView = (TextView) convertView.findViewById(R.id.txt_Thu);
            ImageView image= (ImageView) convertView.findViewById(R.id.img_avatar);
            textView.setText(list.get(position).getWeekDays());
            image.setImageResource(list.get(position).getImagePath());
            setBackGround(position, convertView);

        }

        return convertView;
    }
    public void setBackGround(int position, View convertView ){ // setting item's background for corresponding day of week
        Calendar calendar= Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (position==6 && day == Calendar.SUNDAY){
            convertView.setBackgroundColor(Color.GREEN);
        }
        else if (position==0 && day ==Calendar.MONDAY){
            convertView.setBackgroundColor(Color.GRAY);
        }
        else if (position==1 && day ==Calendar.TUESDAY){
            convertView.setBackgroundColor(Color.GRAY);
        }
        else if (position==2 && day ==Calendar.WEDNESDAY){
            convertView.setBackgroundColor(Color.GRAY);
        }
        else if (position==3 && day ==Calendar.THURSDAY){
            convertView.setBackgroundColor(Color.GRAY);
        }
        else if (position==4 && day ==Calendar.WEDNESDAY){
            convertView.setBackgroundColor(Color.GRAY);
        }
        else if (position==5 && day ==Calendar.SATURDAY){
            convertView.setBackgroundColor(Color.GRAY);
        }
    }

}
