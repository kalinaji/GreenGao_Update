package com.jowney.database.bean.manager;

import com.jowney.database.base.BaseManager;
import com.jowney.database.dao.Student;

import org.greenrobot.greendao.AbstractDao;



public class StudentManager extends BaseManager<Student, Long> {

    public StudentManager(AbstractDao dao) {
        super(dao);
    }

}
