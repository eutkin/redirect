package net.eutkin.redirect.service.redirect;


import net.eutkin.redirect.entity.RedirectType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static java.util.Collections.singletonMap;
import static net.eutkin.redirect.entity.RedirectType.JS;


@Component
public class RedirectJsStrategy implements RedirectByTypeStrategy {

    @Override
    public boolean support(RedirectType type) {
        return JS.equals(type);
    }

    @Override
    public ModelAndView redirect(String direction) {
        return new ModelAndView("js", singletonMap("url", direction));
    }
}
