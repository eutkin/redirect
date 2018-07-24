package net.eutkin.redirect.service.redirect;

import net.eutkin.redirect.entity.RedirectType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import static net.eutkin.redirect.entity.RedirectType.STATUS_307;
import static org.springframework.http.HttpStatus.TEMPORARY_REDIRECT;

@Component
public class Redirect307Strategy implements RedirectByTypeStrategy {

    @Override
    public boolean support(RedirectType type) {
        return STATUS_307.equals(type);
    }

    @Override
    public ModelAndView redirect(String direction) {
        RedirectView view = new RedirectView();
        view.setStatusCode(TEMPORARY_REDIRECT);
        view.setUrl(direction);
        return new ModelAndView(view);
    }

}
