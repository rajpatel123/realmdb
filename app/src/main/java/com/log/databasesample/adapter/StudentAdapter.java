package com.log.databasesample.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.log.databasesample.BuildConfig;
import com.log.databasesample.R;
import com.log.databasesample.StudentDetailActivity;
import com.log.databasesample.models.Student;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by rbpatel on 3/16/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private static final String TAG = "AlertToneAdapter";
    private Context context;
    private List<Student> studentList;


    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int position) {
        View view = null;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_item, viewGroup, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentViewHolder holder, int position) {

        //Remember which build flavor u are using for now

            holder.nameTV.setText(studentList.get(position).getName());
            holder.emailTV.setText(studentList.get(position).getEmail());

        holder.nameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,StudentDetailActivity.class);

            }
        });

    }

    @Override
    public int getItemCount() {
       if (studentList!=null)
            return studentList.size();


       return 0;


    }


    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView nameTV;

        @BindView(R.id.email)
        TextView emailTV;

        StudentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
