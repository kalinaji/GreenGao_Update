package com.jowney.database;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jowney.database.adapter.RecyclerAdapter;
import com.jowney.database.bean.manager.StudentManager;
import com.jowney.database.bean.manager.TeacherManager;
import com.jowney.database.dao.Student;
import com.jowney.database.dao.StudentDao;
import com.jowney.database.dao.Teacher;
import com.jowney.database.bean.manager.ManagerFactory;
import com.jowney.database.dao.DaoManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] arr_subject = new String[]{
            "数学", "语文", "英语", "物理",
            "化学", "音乐", "美术", "体育"
    };
    private String[] arr_surname = new String[]{
            "赵", "钱", "孙", "李",
            "周", "吴", "郑", "王",
            "冯", "陈", "褚", "卫",
            "蒋", "沈", "韩", "杨"
    };

    // 学生
    private RecyclerView mListView_Student;
    private RecyclerAdapter mAdapter_Student;
    private List<Student> mList_Student = new ArrayList<>();

    // 老师
    private RecyclerView mListView_Teacher;
    private RecyclerAdapter<Teacher> mAdapter_Teacher;
    private List<Teacher> mList_Teacher = new ArrayList<>();

    //
    private DaoManager mDaoManager;
    private StudentManager mStudentManager;
    private TeacherManager mTeacherManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDaoManager = DaoManager.getInstance(MyApplication.getContext());
        mStudentManager = ManagerFactory.getInstance().getStudentManager();
        mTeacherManager = ManagerFactory.getInstance().getTeacherManager();

        initView();
    }

    private void initView() {
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_delete = findViewById(R.id.btn_delete_all);
        Button btn_update = findViewById(R.id.btn_update);
        Button btn_query = findViewById(R.id.btn_query_all);
        Button btn_query_1 = findViewById(R.id.btn_query_1);
        Button btn_query_2 = findViewById(R.id.btn_query_2);
        Button btn_query_3 = findViewById(R.id.btn_query_3);
        Button btn_query_4 = findViewById(R.id.btn_query_4);

        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_query_1.setOnClickListener(this);
        btn_query_2.setOnClickListener(this);
        btn_query_3.setOnClickListener(this);
        btn_query_4.setOnClickListener(this);

        mAdapter_Student = new RecyclerAdapter(this);
        mListView_Student = findViewById(R.id.list_view_student);
        mListView_Student.setLayoutManager(new LinearLayoutManager(this));
        mListView_Student.setAdapter(mAdapter_Student);

        mAdapter_Teacher = new RecyclerAdapter(this);
        mListView_Teacher = findViewById(R.id.list_view_teacher);
        mListView_Teacher.setLayoutManager(new LinearLayoutManager(this));
        mListView_Teacher.setAdapter(mAdapter_Teacher);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDaoManager.closeDataBase();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_add:
                Log.e("LiYi", "btn_add");

                // 添加8个老师
                for (int i = 0; i < 4; i++) {
                    Teacher teacher = new Teacher(null, arr_subject[i] + "老师");
                    mTeacherManager.save(teacher);
                }

                // 添加8个学生
                for (int i = 0; i < 4; i++) {
                    Student student = new Student(null, 0L, arr_surname[i] + "同学", 10 + i, "男", "数学班");
                    mStudentManager.save(student);
                }

                mList_Student = mStudentManager.queryAll();
                mList_Teacher = mTeacherManager.queryAll();
                Log.e("LiYi", "新增学生数据" + mList_Student.size() + "条");
                Log.e("LiYi", "新增老师数据" + mList_Teacher.size() + "条");
                break;

            case R.id.btn_delete_all:
                Log.e("LiYi", "btn_delete_all");

                // 学生数据
                mList_Student = mStudentManager.queryAll();
                if (mList_Student.size() > 0) {
                    // 从数据库 删除数据
                    mStudentManager.deleteAll();
                    // 从Activity 删除数据
                    mList_Student.clear();

                    UpdateStudent();

                    Log.e("LiYi", "已删除全部学生数据");
                } else {
                    Log.e("LiYi", "没有学生数据可以删除");
                }

                // 老师数据
                mList_Teacher = mTeacherManager.queryAll();
                if (mList_Teacher.size() > 0) {
                    // 从数据库 删除数据
                    mTeacherManager.deleteAll();
                    // 从Activity 删除数据
                    mList_Teacher.clear();

                    UpdateTeacher();
                    Log.e("LiYi", "已删除全部老师数据");
                } else {
                    Log.e("LiYi", "没有老师数据可以删除");
                }
                break;

            case R.id.btn_update:
                Log.e("LiYi", "btn_update");

                mList_Student.clear();

                // 查询 姓钱 学生数据
                String name_2 = "%钱%";
                mList_Student = mStudentManager.queryBuilder()
                        .where(StudentDao.Properties.Name.like(name_2))
                        .list();

                // 修改 钱同学 为钱钱钱同学
                for (int i = 0; i < mList_Student.size(); i++) {
                    mList_Student.get(i).setName("钱钱钱同学");
                }
                mStudentManager.update(mList_Student);

                // 查询 全部学生数据
                mList_Student = mStudentManager.queryAll();

                UpdateStudent();
                break;

            case R.id.btn_query_all:
                Log.e("LiYi", "btn_query_all");
                mList_Student = mStudentManager.queryAll();

                if (mList_Student.size() > 0) {
                    UpdateStudent();
                    Log.e("LiYi", "查询到学生数据" + mList_Student.size() + "条");
                } else {
                    Log.e("LiYi", "没有查询到学生数据");
                }

                mList_Teacher = mTeacherManager.queryAll();
                if (mList_Teacher.size() > 0) {
                    UpdateTeacher();
                    Log.e("LiYi", "查询到老师数据" + mList_Teacher.size() + "条");
                } else {
                    Log.e("LiYi", "没有查询到老师数据");
                }

                break;

            case R.id.btn_query_1:
                Log.e("LiYi", "btn_query_1");

                String name = "%李%";
                // 查询 姓李 学生数据
                mList_Student = mStudentManager.queryBuilder()
                        .where(StudentDao.Properties.Name.like(name))
                        .list();

                UpdateStudent();
                break;

            case R.id.btn_query_2:
                Log.e("LiYi", "btn_query_2");

                // 查询 id为233 学生数据
                long id_1 = 233;
                mList_Student = mStudentManager.queryBuilder()
                        .where(StudentDao.Properties.Id.eq(id_1))
                        .list();

                UpdateStudent();
                break;


            case R.id.btn_query_3:
                Log.e("LiYi", "btn_query_3");

                // 查询 id不为233 学生数据
                long id_2 = 233;
                mList_Student = mStudentManager.queryBuilder()
                        .where(StudentDao.Properties.Id.notEq(id_2))
                        .list();

                UpdateStudent();
                break;


            case R.id.btn_query_4:
                Log.e("LiYi", "btn_query_4");
                mList_Student.clear();

                // 查询 姓赵 学生数据
                String name_4 = "%赵%";
                mList_Student = mStudentManager.queryBuilder()
                        .where(StudentDao.Properties.Name.like(name_4))
                        .list();

                // 删除 姓赵 学生数据
                mStudentManager.delete(mList_Student);

                // 查询 全部学生数据
                mList_Student = mStudentManager.queryAll();

                UpdateStudent();
                break;

        }
    }




    private void UpdateStudent() {
        mAdapter_Student.setListData(mList_Student, 1);
        mAdapter_Student.notifyDataSetChanged();
    }

    private void UpdateTeacher() {
        mAdapter_Teacher.setListData(mList_Teacher, 2);
        mAdapter_Teacher.notifyDataSetChanged();
    }
}
