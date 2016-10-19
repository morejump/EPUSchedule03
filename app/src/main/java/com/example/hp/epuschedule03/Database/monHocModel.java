package com.example.hp.epuschedule03.Database;

import io.realm.RealmObject;

/**
 * Created by HP on 10/18/2016.
 */

public class monHocModel extends RealmObject{
    private String maSV;// using this field to check whether student's database is aldready persist on system or not
    private String maMH;
    private String tenMH;
    private String nhomMH;
    private String STC;
    private String maLop;
    private String STCHP;
    private String KDK;
    private String TH;
    private String thu;
    private String tietBatDau;
    private String ST;
    private String phong;
    private String CBGD;
    private String tuan;
    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

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

    public String getNhomMH() {
        return nhomMH;
    }

    public void setNhomMH(String nhomMH) {
        this.nhomMH = nhomMH;
    }

    public String getSTC() {
        return STC;
    }

    public void setSTC(String STC) {
        this.STC = STC;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getSTCHP() {
        return STCHP;
    }

    public void setSTCHP(String STCHP) {
        this.STCHP = STCHP;
    }

    public String getKDK() {
        return KDK;
    }

    public void setKDK(String KDK) {
        this.KDK = KDK;
    }

    public String getTH() {
        return TH;
    }

    public void setTH(String TH) {
        this.TH = TH;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getTietBatDau() {
        return tietBatDau;
    }

    public void setTietBatDau(String tietBatDau) {
        this.tietBatDau = tietBatDau;
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
