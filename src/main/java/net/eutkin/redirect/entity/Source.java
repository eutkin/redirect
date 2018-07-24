package net.eutkin.redirect.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.eutkin.redirect.entity.converter.StringsToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Entity
@Table(name = "sources")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "destinations")
@ToString(exclude = "destinations")
public class Source {

    @Id
    @Column(name = "path")
    private String path;

    @Column(name = "type")
    @Enumerated(STRING)
    private RedirectType type;

    @Column(name = "is_random")
    private boolean random;

    @Convert(converter = StringsToString.class)
    @Column(name = "black_list")
    private List<String> blackList;

    @Column(name = "black_url")
    private URL blackUrl;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "source", fetch = EAGER, cascade={ALL})
    private List<Destination> destinations;

    public boolean hasDestination() {
        return !getDestinations().isEmpty();
    }

    @NotNull
    public List<Destination> getDestinations() {
        return destinations.stream().filter(destination -> !destination.isDefault()).collect(toList());
    }

    @NotNull
    public Destination getDefaultDestination() {
        return destinations
                .stream()
                .filter(Destination::isDefault)
                .findAny()
                .get();
    }


}
