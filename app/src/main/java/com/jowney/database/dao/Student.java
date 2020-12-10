package com.jowney.database.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * 学生实体
 */
@Entity
public class Student {

    @Id(autoincrement = true)
    private Long Id;             // 学生ID
    private Long teacherId;      // 班主任ID

    private String name;         // 姓名
    private Integer age;         // 年龄
    private String gender;       // 性别
    private String department;   // 部门

    @Generated(hash = 620316353)
    public Student(Long Id, Long teacherId, String name, Integer age, String gender,
            String department) {
        this.Id = Id;
        this.teacherId = teacherId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
    }

    @Generated(hash = 1556870573)
    public Student() {

    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
