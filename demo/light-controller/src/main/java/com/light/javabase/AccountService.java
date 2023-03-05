package com.light.javabase;

import com.light.interfaces.IAccountService;

import java.util.concurrent.CompletableFuture;

/**
 * @Description
 * @Author light
 * @Date 2023/2/28 15:08
 **/
public class AccountService implements IAccountService {
    @Override
    public CompletableFuture<Void> add(int account, int amount) {

        return CompletableFuture.supplyAsync(() -> {
            System.out.printf("%d 账户，增加了 %d 元",account,amount);
            return null;
        });
    }
}
