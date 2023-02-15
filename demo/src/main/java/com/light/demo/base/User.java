package com.light.demo.base;

import lombok.Data;

/**
 * @Description
 * @Author light
 * @Date 2023/1/31 10:33
 **/
@Data
public class User {
    int age;
    String name;

    public User(){
        this.age = 99;
        this.name = "无";
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User user = (User) obj;

        //如果名字相同，则表示属于同一个对象。

        if (user.getName().equals(this.getName())) {
            return true;
        } else {
            return false;
        }
    }

    //覆盖Object里的hashCode方法(不写会怎么样呢？？)
    @Override
    public int hashCode() {
        return name.hashCode();//返回名字的哈希码。
    }

}
