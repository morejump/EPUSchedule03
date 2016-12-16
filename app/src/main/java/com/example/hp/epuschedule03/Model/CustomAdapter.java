package com.example.hp.epuschedule03.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.epuschedule03.R;

import java.util.ArrayList;

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

        }

        return convertView;
    }
}
