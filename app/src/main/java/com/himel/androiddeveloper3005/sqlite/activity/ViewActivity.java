package com.himel.androiddeveloper3005.sqlite.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.himel.androiddeveloper3005.sqlite.R;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    CustomizedAdapter adapter;
    RecyclerView reView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        dbHelper = new DatabaseHelper(this);
        reView= (RecyclerView) findViewById(R.id.recycleView);
        reView.setHasFixedSize(true);
        reView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Employee> employees = dbHelper.getAllEmployees();
        if (employees != null && employees.size() > 0) {
            Toast.makeText(this, "data found", Toast.LENGTH_LONG).show();
            adapter = new CustomizedAdapter(this, employees);
            reView.setAdapter(adapter);
        }
        else{
            Toast.makeText(this, "No data found", Toast.LENGTH_LONG).show();
        }

    }
}
