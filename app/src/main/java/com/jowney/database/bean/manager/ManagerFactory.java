package com.jowney.database.bean.manager;

import com.jowney.database.MyApplication;
import com.jowney.database.dao.DaoManager;

/**
 *  每一个BeanManager都管理着数据库中的一个表，我将这些管理者在ManagerFactory中进行统一管理
 */
public class ManagerFactory {

    private static ManagerFactory mInstance = null;
    // 对象管理器
    private StudentManager mStudentManager;
    private TeacherManager mTeacherManager;


    /**
     * 获取DaoFactory的实例
     */
    public static ManagerFactory getInstance() {
        if (mInstance == null) {
            synchronized (ManagerFactory.class) {
                if (mInstance == null) {
                    mInstance = new ManagerFactory();
                }
            }
        }
        return mInstance;
    }

    public synchronized StudentManager getStudentManager() {
        if (mStudentManager == null) {
            mStudentManager = new StudentManager(DaoManager.getInstance(MyApplication.getContext()).getDaoSession().getStudentDao());
        }
        return mStudentManager;
    }

    public synchronized TeacherManager getTeacherManager() {
        if (mTeacherManager == null) {
            mTeacherManager = new TeacherManager(DaoManager.getInstance(MyApplication.getContext()).getDaoSession().getTeacherDao());
        }
        return mTeacherManager;
    }
}
