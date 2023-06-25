package com.light.designpattern.behavior;

/**
 * @Author light
 * @Date 2023/4/21
 * @Desc 模板模式
 * 应用场景：模板模式主要是用来解决复用和扩展两个问题
 * 实例作用一复用：Java InputStream、Java AbstractList，就是先写好框架代码，业务部分抽出一个方法让你自己实现，可以复用框架里的代码
 * 实例作用二扩展：Java Servlet、JUnit TestCase，框架通过模板模式提供功能扩展点，让框架用户可以在不修改框架源码的情况下，基于扩展点定制化框架的功能。
 *
 * 案例：当模板类中需要实现的方法过多，我又不需要那么多，该怎么办，总不能全部都实现一遍吧。
 * 解答：在 spring 生命周期中，InstantiationAwareBeanPostProcessorAdapter，就是解决这个问题的。
 * 写个适配器，把所有抽象方法默认实现一下，子类继承这个 adapter 就行了。
 **/
public class TemplateDesignPattern {
    public void templateMethod() {
        AbstractClass demo = new ConcreteClass1();
        demo.templateMethod();
    }
}


/**
 * 模板模式把一个算法中不变的流程抽象到父类的模板方法 templateMethod() 中，将可变的部分 method1()、method2()
 * 留给子类 ContreteClass1 和 ContreteClass2 来实现。所有的子类都可以复用父类中模板方法定义的流程代码。
 * */
abstract class AbstractClass {
    public final void templateMethod() {
        method1();
        method2();
    }
    protected abstract void method1();
    protected abstract void method2();
}

class ConcreteClass1 extends AbstractClass {
    @Override
    protected void method1() {
    }

    @Override
    protected void method2() {
    }
}

class ConcreteClass2 extends AbstractClass {
    @Override
    protected void method1() {
    }

    @Override
    protected void method2() {
    }
}

