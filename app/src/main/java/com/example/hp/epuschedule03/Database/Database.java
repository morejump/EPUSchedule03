package com.example.hp.epuschedule03.Database;

import io.realm.Realm;

/**
 * Created by HP on 10/26/2016.
 */

public class Database {
    public static Realm realm;
    //
    public void addStudent(Student student){ // add a new student to database
        realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(student);
        realm.commitTransaction();

    }
    public Student findStudentByID(String ID){// find out a student by student's id
        realm= Realm.getDefaultInstance();
        return  realm.where(Student.class).equalTo("ID",ID).findFirst();
    }

}
