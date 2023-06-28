package com.light.designpattern.behavior;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author light
 * @Date 2023/5/3
 * @Desc 迭代器模式
 * 背景：很多编程语言都将迭代器作为一个基础的类库，直接提供出来了。在平时开发中，特别是业务开发，我们直接使用即可，很少会自己去实现一个迭代器。
 * 实例：遍历集合数据有三种方法：for 循环、foreach 循环、iterator 迭代器
 * （foreach 循环只是一个语法糖而已，底层是基于迭代器来实现的）
 *
 * iterator 迭代器优点：分离了集合对象的遍历行为; 抽象出了迭代器负责集合对象的遍历, 可以让外部的代码透明的访问集合内部的数据;
 * 问：在 Java 中，如果在使用迭代器的同时删除容器中的元素，会导致迭代器报错，这是为什么呢？如何来解决这个问题呢？
 * A:不要使用集合的remove，使用 iterator.remove();即可；因为集合的remove会导致modCount次数增加，而集合在迭代时不允许修改。
 *
 **/
public class IteratorDesignPattern {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("xzg");
        names.add("wang");
        names.add("zheng");
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            // 会报错：names.remove(iterator.next());
            String str = iterator.next();
            if("xzg".equals(str)){
                iterator.remove();
            }
            System.out.println(str);
        }

        for(String name : names){
            System.out.println(name);
        }
    }
}

class ArrayIterator<E> implements Iterator<E> {
    private int cursor;
    private ArrayList<E> arrayList;

    public ArrayIterator(ArrayList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        //注意这里，cursor在指向最后一个元素的时候，hasNext()仍旧返回true。
        return cursor != arrayList.size();
    }

    @Override
    public E next() {
        return arrayList.get(cursor++);
    }

    public E currentItem() {
        if (cursor >= arrayList.size()) {
        }
        return arrayList.get(cursor);
    }
}
