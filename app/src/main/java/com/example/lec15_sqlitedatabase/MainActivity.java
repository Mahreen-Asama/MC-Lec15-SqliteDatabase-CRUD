package com.example.lec15_sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonAdd, buttonViewAll;
    EditText editName, editRollNumber;
    Switch switchIsActive;
    ListView listViewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.button3);
        buttonViewAll = findViewById(R.id.button4);
        editName = findViewById(R.id.editTextTextPersonName3);
        editRollNumber = findViewById(R.id.editTextNumber);
        switchIsActive = findViewById(R.id.switch1);
        listViewStudent = findViewById(R.id.listView);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModel;
            @Override
            public void onClick(View view) {
                try {
                    studentModel = new StudentModel(editName.getText().toString(), Integer.parseInt(editRollNumber.getText().toString()), switchIsActive.isChecked());
                    Log.d("ok"," run");

                    //Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.d("error","not run");
                    //Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                DBHelper dbHelper  = new DBHelper(MainActivity.this);
                dbHelper.addStudent(studentModel);
            }
        });
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                List<StudentModel> list = dbHelper.getAllStudents();
                MyCustomAdapter arrayAdapter = new MyCustomAdapter<StudentModel>
                        (MainActivity.this,0,list);
                listViewStudent.setAdapter(arrayAdapter);
                Log.d("get also ok"," run");

            }
        });

    }
}