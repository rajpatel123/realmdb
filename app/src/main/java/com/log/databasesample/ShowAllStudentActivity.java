package com.log.databasesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.log.databasesample.adapter.StudentAdapter;
import com.log.databasesample.db.DataBaneHelper;
import com.log.databasesample.db.RealmHelper;
import com.log.databasesample.models.Student;

import java.util.List;

public class ShowAllStudentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView noDataTV;

    List<Student> studentList;

    DataBaneHelper dataBaneHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_student);
        recyclerView = findViewById(R.id.recyclerView);
        noDataTV = findViewById(R.id.noData);
        dataBaneHelper = new DataBaneHelper(this);

        if (BuildConfig.DEBUG) {
            studentList = RealmHelper.getAllStudents();
        } else {
            studentList = dataBaneHelper.getAllStudents();
        }


        if (studentList != null && studentList.size() > 0) {
            //TODO
            //Add recyclerView
            StudentAdapter studentAdapter = new StudentAdapter(this, studentList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(studentAdapter);
            recyclerView.setVisibility(View.VISIBLE);
            noDataTV.setVisibility(View.GONE);

        } else {
            noDataTV.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }


    }

    public static void start(HomeActivity homeActivity) {
        Intent intent = new Intent(homeActivity, ShowAllStudentActivity.class);
        homeActivity.startActivity(intent);
    }
}
