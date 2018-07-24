package net.eutkin.redirect.service.redirect;

import net.eutkin.redirect.entity.RedirectType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import static net.eutkin.redirect.entity.RedirectType.STATUS_308;
import static org.springframework.http.HttpStatus.PERMANENT_REDIRECT;

@Component
public class Redirect308Strategy implements RedirectByTypeStrategy {
    @Override
    public boolean support(RedirectType type) {
        return STATUS_308.equals(type);
    }

    @Override
    public ModelAndView redirect(String direction) {
        RedirectView view = new RedirectView(direction);
        view.setStatusCode(PERMANENT_REDIRECT);
        return new ModelAndView(view);
    }
}
