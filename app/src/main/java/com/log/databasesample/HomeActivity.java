package com.log.databasesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button addData;
    private Button showData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addData = findViewById(R.id.addData);
        showData = findViewById(R.id.showAll);

        /**
         * Adding clicklisteners
         */
        addData.setOnClickListener(this);
        showData.setOnClickListener(this);
    }

    public static void start(SplashActivity activity) {
        Intent intent =new Intent(activity,HomeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addData:
                AddStudentActivity.start(HomeActivity.this);

                break;
            case R.id.showAll:
                ShowAllStudentActivity.start(HomeActivity.this);


                break;
                default:
        }

    }
}
