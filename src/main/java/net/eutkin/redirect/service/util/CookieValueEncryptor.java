package net.eutkin.redirect.service.util;

import net.eutkin.redirect.entity.RedirectType;
import net.eutkin.redirect.service.model.Redirect;
import net.eutkin.redirect.view.RedirectView;

import javax.servlet.http.Cookie;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public interface CookieValueEncryptor {

    Redirect decrypt(Cookie cookie);

    default Cookie encrypt(String name, RedirectView redirectView) {
        String url = redirectView.getURL();
        String cookieValue = encrypt(url, redirectView.getType(), redirectView.getGuid());
        return new Cookie(name, cookieValue);

    }

    String encrypt(String url, RedirectType type, String guid);
}
