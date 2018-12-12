package net.eutkin.redirect.service.util;

import lombok.SneakyThrows;
import net.eutkin.redirect.entity.RedirectType;
import net.eutkin.redirect.service.model.Redirect;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.net.URL;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Service
public class Base64CookieValueEncryptor implements CookieValueEncryptor {

    @Override
    @SneakyThrows
    public Redirect decrypt(Cookie cookie) {
        String raw = new String(Base64.getDecoder().decode(cookie.getValue()), UTF_8);
        String[] values = raw.split(";");
        URL url = new URL(values[0]);
        RedirectType type = RedirectType.valueOf(values[1].toUpperCase());
        Redirect redirect = new Redirect(url, type);
        if (values.length > 2) {
            redirect.guid(values[2]);
        }
        return redirect;
    }

    @NonNull
    public String encrypt(String url, RedirectType type, String guid) {
        String raw = url + ";" + type.name();
        if (guid != null) {
            raw = raw + ";" + guid;
        }
        return Base64.getEncoder().encodeToString(raw.getBytes(UTF_8));
    }
}
