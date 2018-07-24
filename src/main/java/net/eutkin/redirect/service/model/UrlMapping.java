package net.eutkin.redirect.service.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.net.URL;
import java.util.Optional;

@EqualsAndHashCode
@ToString
public class UrlMapping {

    private final URL url;
    private final String guid;

    public UrlMapping(URL url, String guid) {
        this.url = url;
        this.guid = guid;
    }

    public UrlMapping(URL url) {
        this.url = url;
        this.guid = null;
    }

    public URL url() {
        return url;
    }

    public Optional<String> guid() {
        return Optional.ofNullable(guid);
    }
}
