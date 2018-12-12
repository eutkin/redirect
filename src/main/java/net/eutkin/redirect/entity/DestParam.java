package net.eutkin.redirect.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

/**
 * Сущность "Параметр Направления".
 *<p>
 * Хранит информацию о параметрах конкретного направления
 */
@Entity
@Table(name = "dest_params")
@Data
@Accessors(chain = true)
public class DestParam {

    @Id
//    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

//    @Type(type="org.hibernate.type.PostgresUUIDType")
    /**
     * Идентификатор направления
     */
    @Column(name = "dest_id")
    private UUID destId;

    /**
     * Домен направления
     */
    @Column(name = "domain")
    private String domain;

    /**
     * Тип параметра
     */
    @Enumerated(STRING)
    @Column(name = "src_name")
    private ParameterName srcName;
}
