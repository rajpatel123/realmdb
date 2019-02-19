package com.log.databasesample.db;

import android.util.Log;

import com.log.databasesample.models.Student;

import java.util.List;

import io.realm.Realm;

public class RealmHelper {


    public static boolean saveStudent(final Student student) {
        try {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm)
                {

                    realm.copyToRealmOrUpdate(student);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
            Log.d("RealmHelper",e.getMessage());
            return false;
        }
        return true;
    }

    public static List<Student> getAllStudents() {
        try {
            Realm realm = Realm.getDefaultInstance();
            return realm.where(Student.class).findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
