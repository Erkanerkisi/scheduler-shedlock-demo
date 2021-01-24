package com.erkan.scheduler.demo.service;

import net.javacrumbs.shedlock.core.LockProvider;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerManager3 {

    private TaskScheduler taskScheduler;
    private Map<String, ScheduledFuture> tasks = new HashMap<>();
    private LockProvider lockProvider;

    public SchedulerManager3(TaskScheduler taskScheduler,
                             LockProvider lockProvider) {
        this.taskScheduler = taskScheduler;
        this.lockProvider = lockProvider;
    }


    @PostConstruct
    void load() {

        String name = "First Job!";
        tasks.put(name, taskScheduler.schedule(wrap(() -> {
            System.out.println("NAME IS : " + name);
        },name), new CronTrigger("20 * * * * *")));
        //*******************************************************************
        String name2 = "Second Job!";
        tasks.put(name2, taskScheduler.schedule(
                wrap(() -> {System.out.println("NAME IS : " + name2);
                    }, name2),
                new CronTrigger("30 * * * * *")));
    }

    public Runnable wrap(Runnable runnable, String lockName) {
        return new LockRunnable(runnable, lockName, lockProvider);
    }
}