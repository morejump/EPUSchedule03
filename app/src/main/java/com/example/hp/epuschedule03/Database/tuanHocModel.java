package com.example.hp.epuschedule03.Database;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by HP on 10/21/2016.
 */

public class tuanHocModel extends RealmObject{
    private String maSV;
    private int tuan;
    private String thoiGianBD;
    private String thoiGianKT;

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public int getTuan() {
        return tuan;
    }

    public void setTuan(int tuan) {
        this.tuan = tuan;
    }

    public String getThoiGianBD() {
        return thoiGianBD;
    }

    public void setThoiGianBD(String thoiGianBD) {
        this.thoiGianBD = thoiGianBD;
    }

    public String getThoiGianKT() {
        return thoiGianKT;
    }

    public void setThoiGianKT(String thoiGianKT) {
        this.thoiGianKT = thoiGianKT;
    }
}
