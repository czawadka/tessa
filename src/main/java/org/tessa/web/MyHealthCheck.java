package org.tessa.web;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MyHealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        return Health.status(Status.OUT_OF_SERVICE).build();
    }
}
