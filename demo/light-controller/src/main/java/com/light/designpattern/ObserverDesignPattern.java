package com.light.designpattern;

import com.light.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author light
 * @Date 2023/4/20
 * @Desc 观察者模式 同步阻塞模式，还有另一种，异步非阻塞模式 GuavaEventBusObserverPattern
 * 定义：在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知。
 * 使用案例：邮件订阅、RSS Feeds，本质上都是观察者模式。
 * 一般情况下，被依赖的对象叫作被观察者（Observable），依赖的对象叫作观察者（Observer）。不过，在实际的项目开发中，这两种对象的称呼是比较灵活的，有各种不同的叫法，
 * 比如：Subject-Observer、Publisher-Subscriber、Producer-Consumer、EventEmitter-EventListener、Dispatcher-Listener。
 **/
interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(UserInfo user);
}

interface Observer {
    void update(UserInfo user);
}

/**
 * 被观察的主体
 * 方法：添加，删除，通知观察者
 */
class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(UserInfo user) {
        for (Observer observer : observers) {
            observer.update(user);
        }
    }

}

class ConcreteObserverOne implements Observer {
    @Override
    public void update(UserInfo user) {
        //TODO: 获取消息通知，执行自己的逻辑...
        System.out.println("ConcreteObserverOne is notified.");
    }
}

class ConcreteObserverTwo implements Observer {
    @Override
    public void update(UserInfo user) {
        //TODO: 获取消息通知，执行自己的逻辑...
        System.out.println("ConcreteObserverTwo is notified.");
    }
}

public class ObserverDesignPattern {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());
        subject.notifyObservers(new UserInfo());
    }
}
