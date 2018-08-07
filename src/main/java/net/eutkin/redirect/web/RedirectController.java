package net.eutkin.redirect.web;

import lombok.extern.slf4j.Slf4j;
import net.eutkin.redirect.service.logging.RedirectLogger;
import net.eutkin.redirect.service.util.CookieValueEncryptor;
import net.eutkin.redirect.service.RedirectService;
import net.eutkin.redirect.view.RedirectView;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Controller
@Slf4j
public class RedirectController {

    private final RedirectService redirectService;
    private final CookieValueEncryptor cookieValueEncryptor;
    private final RedirectLogger logger;

    public RedirectController(RedirectService redirectService, CookieValueEncryptor cookieValueEncryptor, RedirectLogger logger) {
        this.redirectService = requireNonNull(redirectService);
        this.cookieValueEncryptor = requireNonNull(cookieValueEncryptor);
        this.logger = requireNonNull(logger);
    }

    @RequestMapping("/{path}")
    public ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable String path
    ) {
        Optional<RedirectView> redirectView = redirectService.from(path, request);
        redirectView
                .map(view -> cookieValueEncryptor.encrypt(path, view))
                .ifPresent(response::addCookie);

        redirectView.ifPresent(view -> {
            logger.log(path, request.getRemoteAddr(), view);
        });

        return redirectView.map(RedirectView::getView).orElse(null);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        return "Произошла ошибка";
    }
}
