package net.eutkin.redirect.service.listener;

import io.micrometer.core.instrument.MeterRegistry;
import net.eutkin.redirect.event.RedirectEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MetricRedirectListener implements ApplicationListener<RedirectEvent> {

    private final MeterRegistry meterRegistry;

    public MetricRedirectListener(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void onApplicationEvent(RedirectEvent event) {
        meterRegistry.counter("redirect.count", "path", event.getPath()).increment();
    }
}
