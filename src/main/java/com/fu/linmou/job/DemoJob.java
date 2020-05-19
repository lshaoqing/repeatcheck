package com.fu.linmou.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/19 10:12
 * @Version: 1.0
 */
@Component
public class DemoJob {

    private final AtomicInteger counts = new AtomicInteger();

    // @Scheduled(fixedRate = 2000)
    public void execute() {
        System.out.println("定时第 (" + counts.incrementAndGet() +") 次执行");
    }
}
