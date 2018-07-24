package net.eutkin.redirect.service.redirect;


import net.eutkin.redirect.entity.RedirectType;
import org.springframework.web.servlet.ModelAndView;

import java.net.URL;

public interface RedirectByTypeStrategy {

    boolean support(RedirectType type);

    default ModelAndView redirect(URL direction) {
        return redirect(direction.toString());
    }

    ModelAndView redirect(String direction);
}
