package net.eutkin.redirect.service.redirect;


import net.eutkin.redirect.entity.RedirectType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import static java.util.Collections.singletonMap;
import static net.eutkin.redirect.entity.RedirectType.META;

@Component
public class RedirectMetaStrategy implements RedirectByTypeStrategy {

    @Override
    public boolean support(RedirectType type) {
        return META.equals(type);
    }

    @Override
    public ModelAndView redirect(String direction) {
        return new ModelAndView("meta", singletonMap("url", direction));
    }
}
