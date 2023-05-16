package com.light.model;

import lombok.Data;

/**
 * @Description
 * @Author light
 * @Date 2023/1/31 11:02
 **/
@Data
public class Student extends UserInfo {
    public Student(){}
    public Student(int age, String name, String teacher){
        super(age,name);
        this.teacher = teacher;
    }
    String teacher;
}
