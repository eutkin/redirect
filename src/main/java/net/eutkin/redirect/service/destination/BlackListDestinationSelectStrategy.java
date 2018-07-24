package net.eutkin.redirect.service.destination;

import net.eutkin.redirect.entity.Source;
import net.eutkin.redirect.service.blacklist.BlackListChecker;
import net.eutkin.redirect.service.model.Redirect;
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
public class BlackListDestinationSelectStrategy implements DestinationSelectStrategy {

    private final BlackListChecker blackListChecker;

    public BlackListDestinationSelectStrategy(BlackListChecker blackListChecker) {
        this.blackListChecker = requireNonNull(blackListChecker);
    }

    @Override
    public boolean support(Source source, HttpServletRequest request) {
        return blackListChecker.isOnBlackList(request.getRemoteAddr(), source.getBlackList());
    }

    @Override
    public Redirect getRedirectView(Source source, HttpServletRequest request) {
        return new Redirect(source.getBlackUrl(), source.getType());
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
