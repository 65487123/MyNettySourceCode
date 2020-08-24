package com.utstar.netty;

import com.google.common.util.concurrent.SettableFuture;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * @Author：luzeping
 * @Date: 2020/2/21 14:31
 */
public class Test {
    /** @Date: 2020/3/16 10:08

      * @Return
      **/
    public static void main(String[] args){
        SettableFuture<String> future = SettableFuture.create();
        future.set("2");
        future.set("3");
        HashMap hashMap =new HashMap();
        hashMap.put("2","@1");
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
