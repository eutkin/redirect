package net.eutkin.redirect.service.destination;

import net.eutkin.redirect.entity.Destination;
import net.eutkin.redirect.entity.Source;
import net.eutkin.redirect.service.model.Redirect;
import net.eutkin.redirect.service.model.UrlMapping;
import net.eutkin.redirect.service.util.ParameterMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Objects.requireNonNull;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Service
public class RandomUrlDestinationSelectStrategy implements DestinationSelectStrategy {

    private final ParameterMapper mapper;

    public RandomUrlDestinationSelectStrategy(ParameterMapper mapper) {
        this.mapper = requireNonNull(mapper);
    }

    @Override
    public boolean support(Source source, HttpServletRequest request) {
        return source.isRandom() && source.hasDestination();
    }

    @Override
    public Redirect getRedirectView(Source source, HttpServletRequest request) {
        List<Destination> shuffledDestinations = shuffle(source.getDestinations());
        UrlMapping urlMapping = mapper.map(request.getQueryString(), shuffledDestinations.get(0));
        Redirect redirect = new Redirect(urlMapping.url(), source.getType());
        urlMapping.guid().ifPresent(redirect::guid);
        return redirect;
    }

    public static <T> List<T> shuffle(Collection<T> collection) {
        List<T> buffer = new ArrayList<>(collection);
        Collections.shuffle(buffer, ThreadLocalRandom.current());
        return buffer;
    }
}
