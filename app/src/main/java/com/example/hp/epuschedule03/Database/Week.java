package com.example.hp.epuschedule03.Database;

import io.realm.RealmObject;

/**
 * Created by HP on 10/26/2016.
 */

public class Week extends RealmObject {
    private int tuan;
    private String thoigianBD;
    private String thoigianKT;

    public String getThoigianBD() {
        return thoigianBD;
    }

    public void setThoigianBD(String thoigianBD) {
        this.thoigianBD = thoigianBD;
    }

    public String getThoigianKT() {
        return thoigianKT;
    }

    public void setThoigianKT(String thoigianKT) {
        this.thoigianKT = thoigianKT;
    }

    public int getTuan() {
        return tuan;
    }

    public void setTuan(int tuan) {
        this.tuan = tuan;
    }
}
