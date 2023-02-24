package com.light.model;

/**
 * @Description
 * @Author light
 * @Date 2023/1/31 11:02
 **/
public class Student extends User{
    public Student(){}
    public Student(int age, String name, String teacher){
        super(age,name);
        this.teacher = teacher;
    }
    String teacher;
}
