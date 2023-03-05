package com.light.interfaces;

/**
 * @Description
 * @Author light
 * @Date 2023/2/28 15:04
 **/

import java.util.concurrent.CompletableFuture;

/**
 * 账户服务
 */
public interface IAccountService {
    /**
     * 变更账户金额
     * @param account 账户ID
     * @param amount 增加的金额，负值为减少
     */
    CompletableFuture<Void> add(int account, int amount);
}
