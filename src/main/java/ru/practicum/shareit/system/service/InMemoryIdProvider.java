package ru.practicum.shareit.system.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InMemoryIdProvider implements IdProvider {
    private final AtomicInteger id = new AtomicInteger(0);

    public int getId() {
        return id.incrementAndGet();
    }
}
