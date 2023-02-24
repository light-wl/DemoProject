package com.light.service;

import com.light.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.HashSet;

/**
 * @Description
 * @Author light
 * @Date 2023/2/24 14:56
 **/
public class RewriteEqualsService {
        public static void main(String[] args) throws IllegalAccessException {
        HashSet stuSet = new HashSet();
        stuSet.add(new Student(11, "张三", "王老师"));
        stuSet.add(new Student(11, "张三", "李老师"));
        System.out.println(stuSet.size());
        Student student = new Student();
        for(Field field : student.getClass().getFields()){
            if(field.isAnnotationPresent(Autowired.class)){
                field.set(student, null);
            }
        }
    }
}
