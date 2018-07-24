package net.eutkin.redirect.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Data
public class ParameterKey implements Serializable {

    private static final long serialVersionUID = -9192045065897254951L;

    private String domain;
    private ParameterName srcName;
}
