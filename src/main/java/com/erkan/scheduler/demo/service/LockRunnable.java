package com.erkan.scheduler.demo.service;

import net.javacrumbs.shedlock.core.DefaultLockingTaskExecutor;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.LockingTaskExecutor;

import java.time.Duration;
import java.time.Instant;

class LockRunnable implements Runnable {

    Runnable runnable;
    LockProvider lockProvider;
    String lockName;

    public LockRunnable(Runnable runnable, String lockName, LockProvider lockProvider) {
        this.runnable = runnable;
        this.lockName = lockName;
        this.lockProvider = lockProvider;
    }

    @Override
    public void run() {
        LockingTaskExecutor executor = new DefaultLockingTaskExecutor(lockProvider);
        Instant lockAtMostUntil = Instant.now().plusSeconds(60);//Değişebilir
        System.out.println("INSTANCE -> " + Instant.now());
        executor.executeWithLock(runnable, new LockConfiguration(Instant.now(), lockName, Duration.ofSeconds(60), Duration.ofMillis(1000)));//Değişebilir
    }
}