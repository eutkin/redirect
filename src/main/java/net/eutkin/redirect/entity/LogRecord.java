package net.eutkin.redirect.entity;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalDateTime.now;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Entity
@Table(name = "redirect_log")
@Data
@Accessors(chain = true)
public class LogRecord {

    @Id
    @Column(name = "id")
//    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "redirect_time")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime time;

    @Column(name = "ip")
    private String ip;

    @Column(name = "redirect_from")
    private String from;

    @Column(name = "url")
    private URL to;

    @Column(name = "guid")
    private String guid;

    @PrePersist
    private void prePersist() {
        this.time = now();
    }

    @SneakyThrows
    public LogRecord setTo(String to) {
        this.to = new URL(to);
        return this;
    }

    public LogRecord setTo(URL to) {
        this.to = to;
        return this;
    }
}
