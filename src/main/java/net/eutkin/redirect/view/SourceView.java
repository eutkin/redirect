package net.eutkin.redirect.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.jirutka.validator.collection.constraints.EachPattern;
import cz.jirutka.validator.collection.constraints.EachURL;
import lombok.Data;
import lombok.val;
import net.eutkin.redirect.entity.Destination;
import net.eutkin.redirect.entity.RedirectType;
import net.eutkin.redirect.entity.Source;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Data
public class SourceView {

    @NotEmpty(message = "redirect.save.path.not-empty")
    private String path;

    @NotNull(message = "redirect.save.type.not-null")
    @JsonDeserialize(using = RedirectTypeDeserializer.class)
    @JsonSerialize(using = RedirectTypeSerializer.class)
    private RedirectType type;

    private boolean isRandom;

    private String desc;

    @NotNull(message = "redirect.save.url.not-valid")
    @URL(message = "redirect.save.url.not-valid")
    private java.net.URL defaultUrl;

    @EachPattern(regexp = "^((\\*|[0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}(\\*|[0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", message = "redirect.save.ip.not-valid")
    private List<String> blackList;

    @NotEmpty(message = "redirect.save.black-url.not-null")
    @URL(message = "redirect.save.url.not-valid")
    private java.net.URL blackUrl;

    @EachURL(message = "redirect.save.url.format")
    private List<java.net.URL> urls;


    public Source toEntity() {
        Source source = new Source()
                .setBlackUrl(blackUrl)
                .setBlackList(blackList)
                .setDescription(desc)
                .setPath(path)
                .setType(type)
                .setRandom(isRandom);
        List<Destination> destinations;
        if (isNull(urls)) {
            destinations =  emptyList();
        } else {
            destinations = urls.stream().map(url -> new Destination()
                    .setDefault(false)
                    .setUrl(url)
                    .setId(randomUUID())
                    .setSourcePath(path)
            ).collect(toList());
        }
        val defaultDestination = new Destination().setDefault(true).setUrl(defaultUrl).setId(randomUUID()).setSourcePath(path);
        destinations.add(defaultDestination);
        return source.setDestinations(destinations);
    }

}
