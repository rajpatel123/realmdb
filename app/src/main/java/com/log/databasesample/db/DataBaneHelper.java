package com.log.databasesample.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.log.databasesample.models.Student;

import java.util.ArrayList;
import java.util.List;

public class DataBaneHelper extends SQLiteOpenHelper {
    String TAG = DataBaneHelper.class.getSimpleName();

    private static final String DATEBASE_NAME = "sampleDB";
    private static final String TBL_STUDENT = "student";

    public DataBaneHelper(Context context) {
        super(context, DATEBASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TBL_STUDENT+"(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long saveStudent(Student student) {
        Log.d(TAG, "Going to save student data");
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", student.getName());
            values.put("email", student.getEmail());

            return sqLiteDatabase.insert(TBL_STUDENT, null, values);


        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    public List<Student> getAllStudents() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        List<Student> listStudents = new ArrayList<>();



        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TBL_STUDENT,null,null);

        if (cursor.moveToFirst()){
            do{
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex("id")));
                student.setName(cursor.getString(cursor.getColumnIndex("name")));
                student.setEmail(cursor.getString(cursor.getColumnIndex("email")));

                listStudents.add(student);

            }while(cursor.moveToNext());

        }
        return listStudents;
    }
}
