package com.light.javabase;

import com.light.model.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author light
 * @Date 2023/2/28
 * @Desc 回调函数的使用
 * 原理：回调函数实际上就是在调用某个函数（通常是API函数）时，将自己的一个函数（这个函数为回调函数）的地址作为参数传递给那个函数。
 * 而那个函数在需要的时候，利用传递的地址调用回调函数，这时你可以利用这个机会在回调函数中处理消息或完成一定的操作。
 * 定义三个类，分别是主函数类，callback函数的接口类，业务处理类，在业务处理类中，处理完业务之后，执行一个callback函数。
 **/

/**
 * 回调函数的接口
 */
interface MessageCallBack {
    void onSccuess(String message, String account);

    void onFailure(String message, String account);
}

/**
 * 回调函数的实现
 */
class MessageClient implements MessageCallBack {
    private String message;
    private String account;
    MessageServer messageServer = new MessageServer();

    public MessageClient(String message, String account) {
        this.message = message;
        this.account = account;
    }

    @Override
    public void onSccuess(String message, String account) {
        System.out.println("进入回调函数--" + "消息推送成功," + "客户是:" + account + " 推送的消息是:" + message);
    }

    @Override
    public void onFailure(String message, String account) {
        System.out.println("进入回调函数--" + "消息推送失败," + "客户是:" + account + " 推送的消息是:" + message);
    }

    public void sendMessageSync() {
        System.out.println("以同步方式调用");
        messageServer.send(message, account, this);
    }

    public void sendMessageAsync() {
        System.out.println("以异步方式调用");
        //将自己以参数形式传递
        MessageClient client = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                messageServer.send(message, account, client);
            }
        }).start();
        ;
    }
}

/**
 * 在调用的service里，需要在参数里面添加回调函数的接口，并调用其中的方法
 */
class MessageServer {
    public void send(String message, String account, MessageClient messageClient) {
        try {
            Thread.sleep(2000);
            System.out.println(message + " " + account + "消息推送完毕");
            messageClient.onSccuess(message, account);
        } catch (Exception e) {
            e.printStackTrace();
            messageClient.onFailure(message, account);
        }
    }
}


@RestController
public class CallbackDemo {
    /**
     * 同步的回调函数的使用
     */
    @GetMapping("/sendMessage/sync")
    public void sendMesssageSync(@RequestParam String account) {
        long start = System.currentTimeMillis();
        String message = "这是一条同步测试消息";
        MessageClient messageClient = new MessageClient(message, account);
        messageClient.sendMessageSync();
        System.out.println("耗时" + (System.currentTimeMillis()-start));
    }

    /**
     * 异步方式的回调函数的使用
     */
    @GetMapping("/sendMessage/async")
    public void sendMesssageAsync(@RequestParam String account) {
        long start = System.currentTimeMillis();
        String message = "这是一条异步测试消息";
        MessageClient messageClient = new MessageClient(message, account);
        messageClient.sendMessageAsync();
        System.out.println("耗时" + (System.currentTimeMillis()-start));
    }

    /**
     * 函数式接口
     */
    @FunctionalInterface
    private interface CallBackFunction {
        void callback(int age, String name) throws Exception;
    }

    /**
     * 回调函数代码示例：定义方法
     */
    private void handleSingle(UserInfo user, CallBackFunction callBackFunction) throws Exception {
        callBackFunction.callback(user.getStatus(), user.getName());
    }

    /**
     * 回调函数代码示例：构造匿名方法
     */
    public void init() throws Exception {
        UserInfo user = new UserInfo();
        user.setName("TOM");
        handleSingle(user, (age, name) -> {
            System.out.printf("这个人的姓名是：%d,年龄：%s", age, name);
            System.out.println();
        });
    }

    public static void main(String[] args) throws Exception {
        CallbackDemo demo = new CallbackDemo();
//        demo.init();

        String account = "123";
        demo.sendMesssageSync(account);
        demo.sendMesssageAsync(account);
    }
}





