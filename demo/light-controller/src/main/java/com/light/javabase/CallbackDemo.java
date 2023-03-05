package com.light.javabase;

/**
 * @Description
 * @Author light
 * @Date 2023/2/28 14:36
 **/

import com.light.interfaces.ITransferService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 转账服务的实现
 */
public class CallbackDemo implements ITransferService {

    // 使用依赖注入获取账户服务的实例
    private final AccountService accountService = new AccountService();

    private final static int A = 1000;
    private final static int B = 1001;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CallbackDemo demo = new CallbackDemo();
        //同步调用
        demo.transfer(A, B, 100).get();
        System.out.println("同步调用转账完成！");

        //异步调用
        demo.transfer(A, B, 100).thenRun(() -> System.out.println("异步调用转账完成！"));
    }

    @Override
    public CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount) {
        // 异步调用add方法从fromAccount扣减相应金额
        return accountService.add(fromAccount, -1 * amount)
                .thenCompose(v -> accountService.add(toAccount, amount));

    }

}





