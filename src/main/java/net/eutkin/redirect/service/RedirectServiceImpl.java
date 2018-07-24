package net.eutkin.redirect.service;

import net.eutkin.redirect.entity.RedirectType;
import net.eutkin.redirect.entity.Source;
import net.eutkin.redirect.repository.SourcesRepository;
import net.eutkin.redirect.service.model.Redirect;
import net.eutkin.redirect.service.destination.DestinationSelectStrategy;
import net.eutkin.redirect.service.redirect.RedirectByTypeStrategy;
import net.eutkin.redirect.view.RedirectView;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Service
public class RedirectServiceImpl implements RedirectService {

    private final Collection<RedirectByTypeStrategy> redirectByTypeStrategies;
    private final List<DestinationSelectStrategy> destinationSelectStrategies;
    private final SourcesRepository sourcesRepository;

    public RedirectServiceImpl(
            Collection<RedirectByTypeStrategy> redirectByTypeStrategies,
            Collection<DestinationSelectStrategy> destinationSelectStrategies,
            SourcesRepository sourcesRepository
    ) {
        this.redirectByTypeStrategies = redirectByTypeStrategies;
        this.destinationSelectStrategies = destinationSelectStrategies.stream().sorted().collect(toList());
        this.sourcesRepository = requireNonNull(sourcesRepository);
    }

    @Override
    public Optional<RedirectView> from(String path, final HttpServletRequest request) {
        return sourcesRepository
                .findByPath(path)
                .flatMap(source -> getUrlAndType(source, request))
                .flatMap(this::getRedirectView);
    }

    private Optional<Redirect> getUrlAndType(Source source, HttpServletRequest request) {
        return destinationSelectStrategies
                .stream()
                .filter(redirectStrategy -> redirectStrategy.support(source, request))
                .map(redirectStrategy -> redirectStrategy.getRedirectView(source, request))
                .findFirst();
    }


    private Optional<RedirectView> getRedirectView(Redirect redirect) {
        RedirectType redirectType = redirect.type();
        return redirectByTypeStrategies
                .stream()
                .filter(strategy -> strategy.support(redirectType))
                .map(strategy -> strategy.redirect(redirect.url()))
                .findFirst()
                .map(view -> RedirectView.of(view, redirectType, redirect.guid()));
    }

}
