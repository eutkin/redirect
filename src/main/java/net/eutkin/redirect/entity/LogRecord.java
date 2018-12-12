package net.eutkin.redirect.entity;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Запись о переходе пользователем на другой адрес
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

    /**
     * Дата и время перехода
     */
    @Column(name = "redirect_time")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime time = LocalDateTime.now();

    /**
     * Ip адрес пользователя
     */
    @Column(name = "ip")
    private String ip;

    /**
     * С какого пути состоялся переход
     */
    @Column(name = "redirect_from")
    private String from;

    /**
     * На какой адрес произошел переход
     */
    @Column(name = "url")
    private URL to;

    /**
     * Уникальный ключ перехода
     */
    @Column(name = "guid")
    private String guid;

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
