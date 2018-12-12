package net.eutkin.redirect.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

/**
 * Сущность "Назначение".
 *<p>
 * Хранит информацию и адресе, куда происходит редирект
 */
@Entity
@Table(name = "destinations")
@Data
@Accessors(chain = true)
public class Destination {

    @Id
    @Column(name = "id")
//    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    /**
     * Ссылка на источник редиректа
     */
    @ManyToOne
    @JoinColumn(name = "source_path", insertable = false, updatable = false)
    private Source source;

    /**
     * Источник редиректа
     */
    @Column(name = "source_path")
    private String sourcePath;

    /**
     * Домен
     */
    @Column(name = "domain")
    private String domain;

    /**
     * Полный адрес, куда происходит редирект
     */
    @Column(name = "url")
    private URL url;

    /**
     * Является ли данное направление направлением по умолчанию
     */
    @Column(name = "is_default")
    private boolean isDefault;


    /**
     * Ссылка на параметры адреса назначения
     */
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

    public Destination setUrl(@NonNull URL url) {
        this.domain = url.getHost();
        this.url = url;
        return this;
    }

}
