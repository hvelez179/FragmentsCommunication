package com.example.android.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView studentListView;
    ArrayList<Student> students;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentListView = (ListView) findViewById(R.id.list_student);
    }

    protected void onStart() {
        super.onStart();
        students = generateStudentList();
    }

    protected void onResume() {
        super.onResume();
        studentAdapter = new StudentAdapter(students, this);
        studentListView.setAdapter(studentAdapter);
    }

    private ArrayList<Student> generateStudentList() {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Student student = new Student("Student " + i);
            students.add(student);
        }
        return students;
    }
}
