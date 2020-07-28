package com.tca.thread.future.guava;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoua
 * @Date 2020/7/28
 */
@Slf4j
public class ListenableFutureDemo {

    public static void main(String[] args) {
        // 1.初始化线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 2.包装线程池
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        // 3.提交任务
        ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(() -> {
            TimeUnit.SECONDS.sleep(5L);
            return 1;
        });
        log.info("提交任务完成！");
        // 4.监听
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                log.info("执行成功! result = {}", result);
                executorService.shutdown();
            }

            @Override
            public void onFailure(Throwable t) {
                log.error("执行失败", t);
                executorService.shutdown();
            }
        });
    }
}
