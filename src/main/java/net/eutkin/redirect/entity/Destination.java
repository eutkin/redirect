package net.eutkin.redirect.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "destinations")
@Data
@Accessors(chain = true)
public class Destination {

    @Id
    @Column(name = "id")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "source_path", insertable = false, updatable = false)
    private Source source;

    @Column(name = "source_path")
    private String sourcePath;

    @Column(name = "domain")
    private String domain;

    @Column(name = "url")
    private URL url;

    @Column(name = "is_default")
    private boolean isDefault;


    @ManyToMany(targetEntity = Parameter.class, fetch = EAGER)
    @JoinTable(
            name = "dest_params",
            joinColumns = @JoinColumn(name = "dest_id", referencedColumnName = "id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "domain", referencedColumnName = "domain"),
                    @JoinColumn(name = "src_name", referencedColumnName = "src_name")
            }
    )
    private List<Parameter> parameters;

    public Destination setUrl(@NotNull URL url) {
        this.domain = url.getHost();
        this.url = url;
        return this;
    }

}
