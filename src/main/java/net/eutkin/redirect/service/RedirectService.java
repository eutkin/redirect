package net.eutkin.redirect.service;

import net.eutkin.redirect.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public interface RedirectService {

    Optional<RedirectView> from(String path, HttpServletRequest request);
}
