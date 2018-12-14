package net.eutkin.redirect.event;

import lombok.Builder;
import lombok.Getter;
import net.eutkin.redirect.view.RedirectView;
import org.springframework.context.ApplicationEvent;

@Builder
@Getter
public class RedirectEvent extends ApplicationEvent {

    private final String path;
    private final String  ip;
    private final RedirectView view;

    public RedirectEvent(String path, String ip, RedirectView view) {
        super(path);
        this.path = path;
        this.ip = ip;
        this.view = view;
    }
}
