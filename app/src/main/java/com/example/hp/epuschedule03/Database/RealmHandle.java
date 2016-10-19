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

    public void updateMonhoc(String maSV, monHocModel model) {
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
    public List<monHocModel> findMonHoc(String tenMHTimKiem) {
        return realm.where(monHocModel.class)
                .contains("tenMH", tenMHTimKiem, Case.INSENSITIVE).findAll();
    }

    //
    public Boolean existStudent(String maSV) {
        List<monHocModel> list = realm.where(monHocModel.class)
                .contains("maSV", maSV, Case.INSENSITIVE).findAll();
        if (list.size() == 0)
            return false;
        return true; // student is aldready exist
    }


}
