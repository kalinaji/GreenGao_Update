package com.jowney.database.bean.manager;

import com.jowney.database.base.BaseManager;
import com.jowney.database.dao.Teacher;

import org.greenrobot.greendao.AbstractDao;

public class TeacherManager extends BaseManager<Teacher,Long> {

    public TeacherManager(AbstractDao dao) {
        super(dao);
    }

}
