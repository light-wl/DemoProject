package com.light.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @Author light
 * @Date 2023/1/31 10:33
 **/
@Data
@Builder
@AllArgsConstructor
public class UserInfo {
    Integer id;
    Integer age;
    String name;
    Boolean sex;

    public UserInfo(){
        this.age = 99;
        this.name = "无";
    }

    public UserInfo(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserInfo)) {
            return false;
        }

        UserInfo user = (UserInfo) obj;

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
