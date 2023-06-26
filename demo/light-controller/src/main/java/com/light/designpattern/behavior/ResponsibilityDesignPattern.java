package com.light.designpattern.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author light
 * @Date 2023/6/25
 * @Desc 责任链模式
 * 原理：将请求的发送和接收解耦，让多个接收对象都有机会处理这个请求。将这些接收对象串成一条链，并沿着这条链传递这个请求，直到链上的某个接收对象能够处理它为止。
 * 应用场景：1、利用职责链模式来过滤这些敏感词
 * 2、过滤器
 * 3、拦截器
 **/

/**
 * 学习案例：将方法中统计的代码抽出来，再留一个函数，将这个函数当做必须要实现的方法
 */
abstract class Handler {
    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public final void handle() {
        boolean handled = doHandle();
        if (successor != null && !handled) {
            successor.handle();
        }
    }

    protected abstract boolean doHandle();
}


public class ResponsibilityDesignPattern {
    public static void main(String[] args) {
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new HandlerA());
        chain.addHandler(new HandlerB());
        chain.handle();
    }
}

interface IHandler {
    boolean handle();
}

class HandlerA implements IHandler {
    @Override
    public boolean handle() {
        boolean handled = false;
        //...
        return handled;
    }
}

class HandlerB implements IHandler {
    @Override
    public boolean handle() {
        boolean handled = false;
        //...
        return handled;
    }
}

class HandlerChain {
    private List<IHandler> handlers = new ArrayList<>();

    public void addHandler(IHandler handler) {
        this.handlers.add(handler);
    }

    public void handle() {
        for (IHandler handler : handlers) {
            boolean handled = handler.handle();
            if (handled) {
                break;
            }
        }
    }
}