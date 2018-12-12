package net.eutkin.redirect.service.model;

import net.eutkin.redirect.entity.RedirectType;
import org.springframework.lang.Nullable;

import java.net.URL;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public class Redirect {

    private final URL url;
    private String guid;
    private final RedirectType type;

    public Redirect(URL url, RedirectType type) {
        this.url = url;
        this.type = type;
    }

    public Redirect guid(String guid) {
        this.guid = guid;
        return this;
    }

    public URL url() {
        return url;
    }

    public RedirectType type() {
        return type;
    }

    @Nullable
    public String guid() {
        return guid;
    }
}
