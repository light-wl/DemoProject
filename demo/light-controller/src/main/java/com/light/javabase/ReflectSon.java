package com.light.javabase;

/**
 * @Author light
 * @Date 2023/8/19
 * @Desc
 **/
public class ReflectSon extends ReflectFather{
    public void selfPublic(String str, int num){
        System.out.println("selfPublic");
    }

    private void selfPrivate(){
        System.out.println("selfPrivate");
    }
}
