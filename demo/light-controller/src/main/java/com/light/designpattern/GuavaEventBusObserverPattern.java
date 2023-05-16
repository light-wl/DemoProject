package com.light.designpattern;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.light.service.UserInfoService;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * @Author light
 * @Date 2023/4/20
 * @Desc 异步非阻塞观察者模式的简易实现
 * 其中一种是：在每个 handleRegSuccess() 函数中创建一个新的线程执行代码逻辑；
 * 另一种是：在 UserController 的 register() 函数中使用线程池来执行每个观察者的 handleRegSuccess() 函数。
 *
 * EventBus 翻译为“事件总线”，它提供了实现观察者模式的骨架代码。
 * Google Guava EventBus 就是一个比较著名的 EventBus 框架，它不仅仅支持异步非阻塞模式，同时也支持同步阻塞模式
 * 框架的作用有：隐藏实现细节，降低开发难度，做到代码复用，解耦业务与非业务代码，让程序员聚焦业务开发。
 * 实现： Publisher（消费者）通过调用EventBus的post()方法抛出事件，EventBus基于事件通过发射找到相应的处理器（订阅者）执行
 * EventBus的角色，在此处eventBus主要起到线程之间传递数据的作用，其中有一个方法register()，会对传入的对象进行处理，
 * 将含有@Subscriber注解的将方法存入到一个map集合中，而这个map最终可以看成是：消息处理总线
 **/
public class GuavaEventBusObserverPattern {
  // 依赖注入
  private UserInfoService userInfoService;

  private EventBus eventBus;

  private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;

  public GuavaEventBusObserverPattern() {
    // 同步阻塞模式
    //eventBus = new EventBus();

    // 异步非阻塞模式
    eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));
  }

  /**register 方法，会将含有@Subscriber注解的将方法存入到一个map集合中*/
  public void setRegObservers(List<Object> observers) {
    for (Object observer : observers) {
      eventBus.register(observer);
    }
  }

  /**通知所有观察者*/
  public Long register(String name, Integer age) {
    long userId = userInfoService.register(name, age);
    //Publisher（发布者）通过调用post()方法，根据传入的实参，通过反射的方式找到相应的处理器并进行调
    eventBus.post(userId);
    return userId;
  }
}

/**观察者一号*/
class RegPromotionObserver {
  @Subscribe
  public void handleRegSuccess(Long userId) {
    System.out.println("注册成功");
  }
}

/**观察者二号*/
class RegNotificationObserver {
  @Subscribe
  public void handleRegSuccess(Long userId) {
    System.out.println("注册成功");
  }
}