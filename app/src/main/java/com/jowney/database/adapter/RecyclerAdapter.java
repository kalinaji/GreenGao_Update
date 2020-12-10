package com.jowney.database.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jowney.database.R;
import com.jowney.database.dao.Student;
import com.jowney.database.dao.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<T> ListData = new ArrayList<>();
    private Context mContext;
    private int Index;


    public RecyclerAdapter(Context context) {
        mContext = context;
    }

    /**
     * 加载数据
     *
     * @param listData List<Student> List<Teacher>
     * @param index    1: Student 2: Teacher
     */
    public void setListData(List<T> listData, int index) {
        ListData = listData;
        Index = index;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StringBuilder sb = new StringBuilder();

        if (Index == 1) {
            List<Student> list_student = (List<Student>) ListData;
            Student student = list_student.get(position);
            sb.append(" \nId: ").append(student.getId())
//                    .append(" \ndepartment: ").append(student.getDepartment())
                    .append(" \nname: ").append(student.getName())
                    .append(" \nage: ").append(student.getAge())
//                    .append(" \ngender: ").append(student.getGender())
                    .append(" \nteacherId: ").append(student.getTeacherId());

        } else if (Index == 2) {
            List<Teacher> list_teacher = (List<Teacher>) ListData;
            Teacher teacher = list_teacher.get(position);
            sb.append(" \nId: ").append(teacher.getId())
                    .append(" \nname: ").append(teacher.getName());

            if (teacher.getStudentList().size() > 0) {
                sb.append(" \nteacher.getStudentList().get(0).getName(): ").append(teacher.getStudentList().get(0).getName());
            } else {
                sb.append(" \n该老师班级没有学生");
            }
        }

        holder.mTextView.setText(sb);

    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
        }
    }
}
