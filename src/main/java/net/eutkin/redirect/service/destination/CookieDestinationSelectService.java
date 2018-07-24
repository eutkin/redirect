package net.eutkin.redirect.service.destination;

import net.eutkin.redirect.entity.Source;
import net.eutkin.redirect.service.model.Redirect;
import net.eutkin.redirect.service.util.CookieValueEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Service
public class CookieDestinationSelectService implements DestinationSelectStrategy {

    private final CookieValueEncryptor cookieEncryptor;
    private final boolean enabled;

    public CookieDestinationSelectService(
            CookieValueEncryptor cookieEncryptor,
            @Value("${redirect-service.cookie-cache.enabled:true}") boolean enabled
    ) {
        this.cookieEncryptor = cookieEncryptor;
        this.enabled = enabled;
    }

    @Override
    public boolean support(Source source, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        return enabled && nonNull(cookies) && Stream.of(cookies).map(Cookie::getName).anyMatch(source.getPath()::equals);
    }

    @Override
    public Redirect getRedirectView(Source source, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        return Stream.of(cookies)
                .filter(cookie -> source.getPath().equals(cookie.getName()))
                .map(cookieEncryptor::decrypt)
                .findFirst()
                .get();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
