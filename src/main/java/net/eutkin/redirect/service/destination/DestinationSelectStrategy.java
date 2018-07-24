package net.eutkin.redirect.service.destination;

import net.eutkin.redirect.entity.Source;
import net.eutkin.redirect.service.model.Redirect;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public interface DestinationSelectStrategy extends Ordered, Comparable<DestinationSelectStrategy> {

    boolean support(Source source, HttpServletRequest request);

    Redirect getRedirectView(Source source, HttpServletRequest request);

    @Override
    default int compareTo(@NotNull DestinationSelectStrategy o) {
        return OrderComparator.INSTANCE.compare(this, o);
    }

    @Override
    default int getOrder() {
        return 100;
    }
}
