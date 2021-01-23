package com.erkan.scheduler.demo.service;

import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockConfigurationExtractor;

import java.util.Optional;

public class DefaultLockConfigurationExtractor implements LockConfigurationExtractor {
    private LockConfiguration lockConfiguration;

    public DefaultLockConfigurationExtractor(LockConfiguration lockConfiguration){

        this.lockConfiguration = lockConfiguration;
    }

    @Override
    public Optional<LockConfiguration> getLockConfiguration(Runnable runnable) {
        return Optional.of(lockConfiguration);
    }
}
