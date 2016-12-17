package com.example.hp.epuschedule03.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.epuschedule03.Database.Subject;
import com.example.hp.epuschedule03.Database.passSubject;
import com.example.hp.epuschedule03.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 12/16/2016.
 */

public class MyAdapter  extends BaseAdapter{
    List<passSubject> list;
    Context context;
    LayoutInflater inflater;

    public MyAdapter(List<passSubject> list, Context context) { // constructor here :))
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
        convertView = inflater.inflate(R.layout.subject_on_list, parent, false);
        if (convertView!=null){
            TextView txtMH = (TextView) convertView.findViewById(R.id.txt_monhoc);
            TextView txtTietBD = (TextView) convertView.findViewById(R.id.txt_tietBD);
            TextView txtLop= (TextView) convertView.findViewById(R.id.txt_Lop);
            TextView txtPhong= (TextView) convertView.findViewById(R.id.txt_Phong);
            TextView txtSoTiet = (TextView) convertView.findViewById(R.id.txt_soTiet);
            //
            txtMH.setText(list.get(position).getTenMH());
            txtTietBD.setText("- Tiết bắt đầu: "+list.get(position).getTietBD());
            txtLop.setText("- Mã lớp: "+list.get(position).getMaLop());
            txtPhong.setText("- Phòng: "+list.get(position).getPhong());
            txtSoTiet.setText("- Số tiết: "+list.get(position).getST());

        }

        return convertView;
    }
}
