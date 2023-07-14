package com.light.designpattern.structure;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author light
 * @Date 2023/3/20 23:44
 * @Desc 代理模式代码
 * java的动态代理技术的实现主要有两种方式：jdk动态代理（基于接口）和cglib代理（基于类的）；
 * 以下示例是静态代理，动态代理的区别是，动态生成类，而不用手动进行继承和实现。
 * 应用场景很多：监控，统计，鉴权，限流，日志等等。
 **/

public class ProxyDesignPattern {
  HashMap<String, String> one = new HashMap<>();
  Hashtable<String, String> two = new Hashtable<>();
  ConcurrentHashMap three = new ConcurrentHashMap();
}

/**接口*/
interface IUserController {
  void login(String telephone, String password);
}

/**实现类*/
class UserController implements IUserController {
  @Override
  public void login(String telephone, String password) {
    //...省略login逻辑...
  }
}

/**实现一：基于接口的动态代理
 * 因为原始类和代理类实现相同的接口，是基于接口而非实现编程
 * 将UserController类对象替换为UserControllerProxy类对象，不需要改动太多代码
 */
class UserControllerProxy implements IUserController {
  private UserController userController;

  public UserControllerProxy(UserController userController) {
    this.userController = userController;
  }

  @Override
  public void login(String telephone, String password) {
    // 可以添加的一些额外功能，
    long startTimestamp = System.currentTimeMillis();

    // 委托，真正需要实现的功能
    userController.login(telephone, password);

    long endTimeStamp = System.currentTimeMillis();
    long responseTime = endTimeStamp - startTimestamp;
  }
}

/**实现二：基于继承的动态代理
 * 如果这个类没有接口，只是一个普通的类，则可以使用此方式
 */
class UserControllerProxy2 extends UserController {

  public void login(String telephone, String password) {
    // 可以添加的一些额外功能，
    long startTimestamp = System.currentTimeMillis();

    // 委托，真正需要实现的功能
    super.login(telephone, password);

    long endTimeStamp = System.currentTimeMillis();
    long responseTime = endTimeStamp - startTimestamp;

  }

}

