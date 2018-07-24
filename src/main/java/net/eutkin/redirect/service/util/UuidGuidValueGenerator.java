package net.eutkin.redirect.service.util;

import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Service
public class UuidGuidValueGenerator implements GuidValueGenerator {

    @Override
    public String generate(Object... args) {
        return randomUUID().toString();
    }
}
