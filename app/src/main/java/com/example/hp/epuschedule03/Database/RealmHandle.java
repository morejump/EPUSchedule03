package com.example.hp.epuschedule03.Database;

import java.util.List;

import io.realm.Case;
import io.realm.Realm;

/**
 * Created by HP on 10/18/2016.
 */

public class RealmHandle {

    Realm realm = Realm.getDefaultInstance();// instanitaing realm instance// change this if have chance

    public void addMonHoc(monHocModel model) {// add an object to the schedule
        realm.beginTransaction();
        realm.copyToRealm(model);
        realm.commitTransaction();
    }
    public void addtuanHocModel(tuanHocModel model) {// add an object to the schedule
        realm.beginTransaction();
        realm.copyToRealm(model);
        realm.commitTransaction();
    }

    public void updateMonhoc(String maSV, monHocModel model) { // updating subject
        realm.beginTransaction();
        // delete monhoc with specific maSV and then add a new model to database
        List<monHocModel> list = realm.where(monHocModel.class) // get list of student who has corresponding maSV
                .contains("maSV", maSV, Case.INSENSITIVE).findAll();
        // deleting list from realm
        for (monHocModel model1 : list) {
            model.deleteFromRealm();
        }
        // add a new model to realm
        realm.copyToRealm(model);
        realm.commitTransaction();
    }

    //
    public List<monHocModel> findMonHoc(String tenMHTimKiem) { // finding corresponding subject with input string
        return realm.where(monHocModel.class)
                .contains("tenMH", tenMHTimKiem, Case.INSENSITIVE).findAll();
    }

    //
    public Boolean existStudent(String maSV) { // check out whether student's id is exist on database or not
        List<monHocModel> list = realm.where(monHocModel.class)
                .contains("maSV", maSV, Case.INSENSITIVE).findAll();
        if (list.size() == 0)
            return false;
        return true; // student is aldready exist
    }
    public String findTenSV(String maSV){
        return realm.where(monHocModel.class)
                .contains("maSV",maSV, Case.INSENSITIVE).findFirst().getTenSV();
    }
    public String findWeek(String maSV){
        return realm.where(tuanHocModel.class)
                .contains("maSV",maSV, Case.INSENSITIVE).findFirst().getThoiGianBD();
    }


}
