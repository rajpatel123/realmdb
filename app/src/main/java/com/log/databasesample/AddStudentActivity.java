package com.log.databasesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.log.databasesample.db.DataBaneHelper;
import com.log.databasesample.db.RealmHelper;
import com.log.databasesample.models.Student;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {

    DataBaneHelper dataBaneHelper;

    public static void start(HomeActivity homeActivity) {

        Intent intent = new Intent(homeActivity,AddStudentActivity.class);
        homeActivity.startActivity(intent);
    }

    private Button saveData;
    private EditText nameEdt, emailEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        dataBaneHelper = new DataBaneHelper(this);
        saveData = findViewById(R.id.saveData);
        nameEdt = findViewById(R.id.nameEdt);
        emailEdt = findViewById(R.id.emailEdt);

        saveData.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveData:
                //Validate
                Student student = new Student();

                student.setName(nameEdt.getText().toString());
                student.setEmail(nameEdt.getText().toString());

                //if debug use Realm else use sqlite
                if (BuildConfig.DEBUG){
                     boolean success  =RealmHelper.saveStudent(student);
                    if (success){
                        Toast.makeText(AddStudentActivity.this,"Data  in Realm inserted",Toast.LENGTH_LONG).show();
                        finish();
                    }else{
                        Toast.makeText(AddStudentActivity.this,"Data insertion failed",Toast.LENGTH_LONG).show();
                    }
                }else{
                    long id = dataBaneHelper.saveStudent(student);
                    if (id>0){
                        Toast.makeText(AddStudentActivity.this,"Data in SQLite inserted",Toast.LENGTH_LONG).show();
                        finish();
                    }else{
                        Toast.makeText(AddStudentActivity.this,"Data insertion failed",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
        }


    }


}
