package net.eutkin.redirect.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.eutkin.redirect.entity.RedirectType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import java.net.URL;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@AllArgsConstructor(staticName = "of")
@Getter
public class RedirectView {

    private final ModelAndView view;
    private final RedirectType type;
    private final String guid;

    public String getURL() {
        View view = getView().getView();
        if (view instanceof AbstractUrlBasedView) {
            return ((AbstractUrlBasedView) view).getUrl();
        }
        if (this.view.getModelMap().containsAttribute("url")) {
            return this.view.getModel().get("url").toString();
        }
        throw new RuntimeException("View is not url-based");
    }

}
