package com.light.interfaces;

/**
 * @Description
 * @Author light
 * @Date 2023/2/28 15:06
 **/

import java.util.concurrent.CompletableFuture;

/**
 * 转账服务
 */
public interface ITransferService {
    /**
     * 异步转账服务
     * @param fromAccount 转出账户
     * @param toAccount 转入账户
     * @param amount 转账金额，单位分
     */
    CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount);
}