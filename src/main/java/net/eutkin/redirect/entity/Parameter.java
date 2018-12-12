package net.eutkin.redirect.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

/**
 * Сущность, которая хранит информацию о маппинге
 * параметров для определенного домена
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Entity
@Table(name = "parameters")
@Data
@Accessors(chain = true)
@IdClass(ParameterKey.class)
public class Parameter {

    @Id
    @Column(name = "domain")
    private String domain;

    @Id
    @Column(name = "src_name")
    @Enumerated(STRING)
    private ParameterName srcName;

    @Column(name = "dest_name")
    private String destName;

    public boolean isSlash() {
        return destName == null || destName.isEmpty();
    }
}
