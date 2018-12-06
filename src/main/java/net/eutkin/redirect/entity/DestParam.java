package net.eutkin.redirect.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "dest_params")
@Data
@Accessors(chain = true)
public class DestParam {

    @Id
//    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

//    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "dest_id")
    private UUID destId;
    @Column(name = "domain")
    private String domain;
    @Enumerated(STRING)
    @Column(name = "src_name")
    private ParameterName srcName;
}
