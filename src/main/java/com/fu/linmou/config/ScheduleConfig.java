package com.fu.linmou.config;

import com.fu.linmou.job.QuartzJob;
import com.fu.linmou.job.QuartzJob02;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linMou
 * @Description:    配置
 * @Date: 2020/5/19 10:10
 * @Version: 1.0
 */
//@Configuration
//@EnableScheduling
public class ScheduleConfig {

    public static class QuartzJobConfig{
        @Bean
        public JobDetail quartzJob() {
            return JobBuilder.newJob(QuartzJob.class)
                    .withIdentity("quartzJob")
                    .storeDurably()
                    .build();
        }
        @Bean
        public Trigger quartzJobTrigger() {
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever();
            return TriggerBuilder.newTrigger()
                        .forJob(quartzJob())
                        .withSchedule(scheduleBuilder)
                        .withIdentity("quartzJobTrigger")
                        .build();
        }
    }

    public static class QuartzJob02Config{
        @Bean
        public JobDetail quartzJob02() {
            return JobBuilder.newJob(QuartzJob02.class)
                    .withIdentity("quartzJob02") // 名字为 demoJob02
                    .storeDurably() // 没有 Trigger 关联的时候任务是否被保留。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                    .build();
        }

        @Bean
        public Trigger quartzJob02Trigger() {
            // 基于 Quartz Cron 表达式的调度计划的构造器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ? *");
            // Trigger 构造器
            return TriggerBuilder.newTrigger()
                    .forJob(quartzJob02()) // 对应 Job 为 demoJob02
                    .withIdentity("quartzJob02Trigger") // 名字为 demoJob02Trigger
                    .withSchedule(scheduleBuilder) // 对应 Schedule 为 scheduleBuilder
                    .build();
        }
    }
}
