package net.eutkin.redirect.service.destination;

import net.eutkin.redirect.entity.Source;
import net.eutkin.redirect.service.model.Redirect;
import net.eutkin.redirect.service.model.UrlMapping;
import net.eutkin.redirect.service.util.ParameterMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.requireNonNull;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Service
public class DefaultUrlDestinationSelectStrategy implements DestinationSelectStrategy {

    private final ParameterMapper mapper;

    public DefaultUrlDestinationSelectStrategy(ParameterMapper mapper) {
        this.mapper = requireNonNull(mapper);
    }

    @Override
    public boolean support(Source source, HttpServletRequest request) {
        return !source.isRandom() || !source.hasDestination();
    }

    @Override
    public Redirect getRedirectView(Source source, HttpServletRequest request) {
        UrlMapping urlMapping = mapper.map(request.getQueryString(), source.getDefaultDestination());
        Redirect redirect = new Redirect(urlMapping.url(), source.getType());
        urlMapping.guid().ifPresent(redirect::guid);
        return redirect;
    }

    @Override
    public int getOrder() {
        return 20;
    }
}
