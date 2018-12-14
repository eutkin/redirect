package net.eutkin.redirect.service.logging;

import net.eutkin.redirect.view.RedirectView;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public interface RedirectLogger {

    void log(String path, String clientIp, RedirectView redirectView);
}
