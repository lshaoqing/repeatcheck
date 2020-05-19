package com.fu.linmou.config;

import com.fu.linmou.job.ClusterJob01;
import com.fu.linmou.job.ClusterJob02;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/19 11:24
 * @Version: 1.0
 */
@Configuration
public class ClusterScheduleConfig {
    public static class ClusterJob01Config {

        @Bean
        public JobDetail clusterJob01() {
            return JobBuilder.newJob(ClusterJob01.class)
                    .withIdentity("clusterJob01") // 名字为 demoJob01
                    .storeDurably() // 没有 Trigger 关联的时候任务是否被保留。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                    .build();
        }

        @Bean
        public Trigger clusterJob01Trigger() {
            // 简单的调度计划的构造器
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5) // 频率。
                    .repeatForever(); // 次数。
            // Trigger 构造器
            return TriggerBuilder.newTrigger()
                    .forJob(clusterJob01()) // 对应 Job 为 demoJob01
                    .withIdentity("clusterJob01Trigger") // 名字为 demoJob01Trigger
                    .withSchedule(scheduleBuilder) // 对应 Schedule 为 scheduleBuilder
                    .build();
        }

    }

    public static class ClusterJob02Configuration {

        @Bean
        public JobDetail clusterJob02() {
            return JobBuilder.newJob(ClusterJob02.class)
                    .withIdentity("demoJob02") // 名字为 demoJob02
                    .storeDurably() // 没有 Trigger 关联的时候任务是否被保留。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                    .build();
        }

        @Bean
        public Trigger clusterJob02Trigger() {
            // 简单的调度计划的构造器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ? *");
            // Trigger 构造器
            return TriggerBuilder.newTrigger()
                    .forJob(clusterJob02()) // 对应 Job 为 demoJob02
                    .withIdentity("clusterJob02Trigger") // 名字为 demoJob02Trigger
                    .withSchedule(scheduleBuilder) // 对应 Schedule 为 scheduleBuilder
                    .build();
        }

    }
}
