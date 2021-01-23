package com.erkan.scheduler.demo.service;

import net.javacrumbs.shedlock.core.*;
import net.javacrumbs.shedlock.spring.LockableTaskScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerManager {

    private TaskScheduler taskScheduler;
    private ApplicationContext applicationContext;
    private Map<String, ScheduledFuture> tasks = new HashMap<>();


    public SchedulerManager(TaskScheduler taskScheduler,
                            ApplicationContext applicationContext) {
        this.taskScheduler = taskScheduler;
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    void load() {

        LockProvider lockProvider = (LockProvider) applicationContext.getBean("lockProvider");

        String name = "First Job!";

        LockConfiguration lockConfiguration = new LockConfiguration(Instant.now(), name, Duration.ofMinutes(1), Duration.ofMinutes(1));
        LockConfigurationExtractor lockConfigurationExtractor = new DefaultLockConfigurationExtractor(lockConfiguration);
        LockManager lockManager = new DefaultLockManager(lockProvider, lockConfigurationExtractor);
        LockableTaskScheduler lockableTaskScheduler = new LockableTaskScheduler(taskScheduler, lockManager);

        tasks.put(name, lockableTaskScheduler.schedule(() -> {
            System.out.println("NAME IS : " + name);
        }, new CronTrigger("20 * * * * *")));

        String name2 = "Second Job!";

        LockConfiguration lockConfiguration2 = new LockConfiguration(Instant.now(), name2, Duration.ofMinutes(1), Duration.ofMinutes(1));
        LockConfigurationExtractor lockConfigurationExtractor2 = new DefaultLockConfigurationExtractor(lockConfiguration2);
        LockManager lockManager2 = new DefaultLockManager(lockProvider, lockConfigurationExtractor2);
        LockableTaskScheduler lockableTaskScheduler2 = new LockableTaskScheduler(taskScheduler, lockManager2);

        tasks.put(name2, lockableTaskScheduler2.schedule(() -> {
            System.out.println("NAME IS : " + name2);
        }, new CronTrigger("30 * * * * *")));
    }
}
