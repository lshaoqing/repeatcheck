package com.fu.linmou.job;

import com.fu.linmou.service.impl.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/19 10:30
 * @Version: 1.0
 */
@Slf4j
public class QuartzJob extends QuartzJobBean {

    private final AtomicInteger counts = new AtomicInteger();

    @Autowired
    private DemoService demoService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("[executeInternal][定时第 ({}) 次执行, demoService 为 ({})]", counts.incrementAndGet(),
                demoService);
    }
}
