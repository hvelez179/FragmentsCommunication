package com.example.android.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {

    private ArrayList<Student> students;
    private Context context;

    public StudentAdapter(ArrayList<Student> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        // return students.get(position).getId();
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView != null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Student currentStudent = (Student) getItem(position);
        holder.studentName.setText(currentStudent.getStudentName());

        return convertView;
    }

    private class ViewHolder {
        TextView studentName;

        public  ViewHolder(View view) {
            studentName = (TextView) view.findViewById(R.id.tv_name);
        }
    }
}
