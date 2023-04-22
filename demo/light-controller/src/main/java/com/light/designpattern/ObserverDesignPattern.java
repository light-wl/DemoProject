package com.light.designpattern;

import com.light.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author light
 * @Date 2023/4/20
 * @Desc 观察者模式 同步阻塞模式，还有另一种，异步非阻塞模式 GuavaEventBusObserverPattern
 * 定义：在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知。
 * 使用案例：邮件订阅、RSS Feeds，本质上都是观察者模式。
 **/
interface Subject {
  void registerObserver(Observer observer);
  void removeObserver(Observer observer);
  void notifyObservers(User user);
}

interface Observer {
  void update(User user);
}

/**
 * 被观察的主体
 * 方法：添加，删除，通知观察者
 * */
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
  public void notifyObservers(User user) {
    for (Observer observer : observers) {
      observer.update(user);
    }
  }

}

class ConcreteObserverOne implements Observer {
  @Override
  public void update(User user) {
    //TODO: 获取消息通知，执行自己的逻辑...
    System.out.println("ConcreteObserverOne is notified.");
  }
}

class ConcreteObserverTwo implements Observer {
  @Override
  public void update(User user) {
    //TODO: 获取消息通知，执行自己的逻辑...
    System.out.println("ConcreteObserverTwo is notified.");
  }
}

public class ObserverDesignPattern {
  public static void main(String[] args) {
    ConcreteSubject subject = new ConcreteSubject();
    subject.registerObserver(new ConcreteObserverOne());
    subject.registerObserver(new ConcreteObserverTwo());
    subject.notifyObservers(new User());
  }
}
