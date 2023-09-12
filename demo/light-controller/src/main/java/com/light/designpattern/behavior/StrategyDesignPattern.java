package com.light.designpattern.behavior;

import com.light.enums.OrderTypeEnum;
import com.light.model.ValidationModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author light
 * @Date 2023/4/21
 * @Desc 策略模式
 * 应用场景：如何避免冗长的if-else/switch分支判断代码？
 * 问：在过去的项目开发中，你有没有用过策略模式，都是为了解决什么问题才使用的？
 * 问：你可以说一说，在什么情况下，我们才有必要去掉代码中的 if-else 或者 switch-case 分支逻辑呢？
 **/
public class StrategyDesignPattern {
  public static void main(String[] args) {
    ValidationModel order = new ValidationModel();
//    order.setType(OrderTypeEnum.NORMAL);
    OrderServiceBefore orderServiceBefore = new OrderServiceBefore();
    orderServiceBefore.discount(order);
  }
}

/**重构前*/
class OrderServiceBefore {
  public double discount(ValidationModel order) {
    double discount = 0.0;
    OrderTypeEnum type = OrderTypeEnum.NORMAL;
    if (type.equals(OrderTypeEnum.NORMAL)) { // 普通订单
      //...省略折扣计算算法代码
    } else if (type.equals(OrderTypeEnum.GROUPON)) { // 团购订单
      //...省略折扣计算算法代码
    } else if (type.equals(OrderTypeEnum.PROMOTION)) { // 促销订单
      //...省略折扣计算算法代码
    }
    return discount;
  }
}

/**重构后*/
class OrderServiceAfter {
  public double discount(ValidationModel order) {
//    OrderTypeEnum type = order.getType();
    DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(OrderTypeEnum.GROUPON);
    return discountStrategy.calDiscount(order);
  }
}

/**策略的创建，这是每次都创建相同的策略，如果每次要都创建新的，则要用if判断，然后new一个对象*/
class DiscountStrategyFactory {
  private static final Map<OrderTypeEnum, DiscountStrategy> strategies = new HashMap<>();

  static {
    strategies.put(OrderTypeEnum.NORMAL, new NormalDiscountStrategy());
    strategies.put(OrderTypeEnum.GROUPON, new GrouponDiscountStrategy());
    strategies.put(OrderTypeEnum.PROMOTION, new PromotionDiscountStrategy());
  }

  public static DiscountStrategy getDiscountStrategy(OrderTypeEnum type) {
    return strategies.get(type);
  }
}

// 策略的定义
interface DiscountStrategy {
  double calDiscount(ValidationModel order);
}

class NormalDiscountStrategy implements DiscountStrategy{
  @Override
  public double calDiscount(ValidationModel order) {
    return 0;
  }
}
class GrouponDiscountStrategy implements DiscountStrategy{
  @Override
  public double calDiscount(ValidationModel order) {
    return 0;
  }
}
class PromotionDiscountStrategy implements DiscountStrategy{
  @Override
  public double calDiscount(ValidationModel order) {
    return 0;
  }
}