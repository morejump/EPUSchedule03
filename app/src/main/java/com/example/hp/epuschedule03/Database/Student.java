package com.example.hp.epuschedule03.Database;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by HP on 10/26/2016.
 */

public class Student extends RealmObject {
    private String name;
    private String ID;
    public RealmList<Subject> subjectRealmList;
//    public List<Subject> subjectRealmList;
    public RealmList<Week> weekRealmList;

    public Student() {
        subjectRealmList= new RealmList<>();
        weekRealmList= new RealmList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
