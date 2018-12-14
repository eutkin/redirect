package net.eutkin.redirect.service.listener;

import net.eutkin.redirect.event.RedirectEvent;
import net.eutkin.redirect.service.logging.RedirectLogger;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class LoggingRedirectListener implements ApplicationListener<RedirectEvent> {

    private final Collection<RedirectLogger> redirectLoggers;

    public LoggingRedirectListener(Collection<RedirectLogger> redirectLoggers) {
        this.redirectLoggers = redirectLoggers;
    }

    @Override
    public void onApplicationEvent(RedirectEvent event) {
        redirectLoggers.forEach(redirectLogger ->
                redirectLogger.log(event.getPath(), event.getIp(), event.getView())
        );
    }
}
