package com.example.hp.epuschedule03.Database;

import java.lang.ref.PhantomReference;

import io.realm.RealmObject;

/**
 * Created by HP on 10/26/2016.
 */

public class Subject extends RealmObject {
    private String maMH;
    private String tenMH;
    private String maLop;
    private String thu;
    private String tietBD;
    private String ST;
    private String phong;
    private String CBGD;
    private String tuan;

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getTietBD() {
        return tietBD;
    }

    public void setTietBD(String tietBD) {
        this.tietBD = tietBD;
    }

    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getCBGD() {
        return CBGD;
    }

    public void setCBGD(String CBGD) {
        this.CBGD = CBGD;
    }

    public String getTuan() {
        return tuan;
    }

    public void setTuan(String tuan) {
        this.tuan = tuan;
    }
}
