package com.spring.boot.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service层
 *
 * @author 代码的路
 * @date 2022/12/23
 */

@Component
@Slf4j
@Service
@EnableScheduling
public class MyService implements SchedulingConfigurer, Ordered {

    @Value("${param.cron_test1}")
    private String cron_test1;
    @Value("${param.cron_test2}")
    private String cron_test2;


    public String getTme() {
        Long current_time = System.currentTimeMillis();
        SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
        String formateTime = formate.format(current_time);
        String clock = formateTime.substring(8, 10) + ":" + formateTime.substring(10, 12) + ":" + formateTime.substring(12, 14);
        return clock;
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        Runnable testRunnable1 = () -> {
            System.out.println("current_time1:" + getTme());
            System.out.println("cron_test1:" + cron_test1);
        };

        Trigger testTrigger1 = triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger(cron_test1);
            return cronTrigger.nextExecutionTime(triggerContext);
        };

        Runnable testRunnable2 = () -> {
            System.out.println("current_time2:" + getTme());
            System.out.println("cron_test2:" + cron_test1);
        };

        Trigger testTrigger2 = triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger(cron_test2);
            return cronTrigger.nextExecutionTime(triggerContext);
        };

        taskRegistrar.addTriggerTask(testRunnable1, testTrigger1);
        taskRegistrar.addTriggerTask(testRunnable2, testTrigger2);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
