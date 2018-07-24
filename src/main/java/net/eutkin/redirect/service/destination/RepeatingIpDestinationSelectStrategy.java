package net.eutkin.redirect.service.destination;

import lombok.SneakyThrows;
import net.eutkin.redirect.entity.LogRecord;
import net.eutkin.redirect.entity.Source;
import net.eutkin.redirect.repository.LogRepository;
import net.eutkin.redirect.service.model.Redirect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class RepeatingIpDestinationSelectStrategy implements DestinationSelectStrategy {

    private final LogRepository logRepository;
    private final ThreadLocal<Map<String, LogRecord>> cachedRecords;
    private final boolean enabled;

    public RepeatingIpDestinationSelectStrategy(
            LogRepository logRepository,
            @Value("${redirect-service-ip-cached.enable}:true") boolean enabled
    ) {
        this.enabled = enabled;
        this.logRepository = requireNonNull(logRepository);
        this.cachedRecords = ThreadLocal.withInitial(HashMap<String, LogRecord>::new);
    }

    @Override
    public boolean support(Source source, HttpServletRequest request) {
        if (!enabled) return false;
        String remoteIp = request.getRemoteAddr();
        Optional<LogRecord> lastRedirectByIp = logRepository.findLastRedirectByIp(remoteIp);
        lastRedirectByIp.ifPresent(record -> cachedRecords.get().put(record.getIp(), record));
        return lastRedirectByIp.isPresent();
    }

    @Override
    @SneakyThrows
    public Redirect getRedirectView(Source source, HttpServletRequest request) {
        LogRecord recordOfLastRedirect = cachedRecords.get().remove(request.getRemoteAddr());
        URL url = recordOfLastRedirect.getTo();
        return new Redirect(url, source.getType()).guid(recordOfLastRedirect.getGuid());
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
