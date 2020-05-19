package com.fu.linmou.job;

import com.fu.linmou.service.impl.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/19 11:05
 * @Version: 1.0
 */
@Slf4j
@DisallowConcurrentExecution
public class ClusterJob01 extends QuartzJobBean {

    @Autowired
    private DemoService demoService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("[executeInternal][我开始的执行了, demoService 为 ({})]", demoService);

    }
}
