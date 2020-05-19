package com.fu.linmou.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/19 11:06
 * @Version: 1.0
 */
@Slf4j
@DisallowConcurrentExecution
public class ClusterJob02 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("[executeInternal][我开始的执行了]");
    }
}
