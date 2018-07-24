package net.eutkin.redirect.service.logging;

import net.eutkin.redirect.entity.LogRecord;
import net.eutkin.redirect.repository.LogRepository;
import net.eutkin.redirect.view.RedirectView;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Service
public class JpaRedirectLogger implements RedirectLogger {

    private final LogRepository logRepository;

    public JpaRedirectLogger(LogRepository logRepository) {
        this.logRepository = requireNonNull(logRepository);
    }

    @Override
    public void log(String path, String clientIp, RedirectView redirectView) {
        LogRecord record = new LogRecord()
                .setId(randomUUID())
                .setIp(clientIp)
                .setGuid(redirectView.getGuid())
                .setFrom(path)
                .setTo(redirectView.getURL());
        logRepository.save(record);
    }
}
