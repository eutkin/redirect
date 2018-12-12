package net.eutkin.redirect.view;

import lombok.Data;
import net.eutkin.redirect.entity.Parameter;
import net.eutkin.redirect.entity.ParameterName;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Data
public class ParameterView {

    @URL(message = "redirect.save.domain.not-empty")
    private java.net.URL domain;

    @NotEmpty
    private Map<ParameterName, String> params;

    public List<Parameter> toEntity() {
        return Stream.of(ParameterName.values())
                .map(parameterName -> {
                    if (params.containsKey(parameterName)) {
                        return new Parameter()
                                .setDomain(domain.getHost())
                                .setSrcName(parameterName)
                                .setDestName(params.get(parameterName));
                    }
                    return new Parameter()
                            .setDomain(domain.getHost())
                            .setSrcName(parameterName);

                }).collect(toList());
    }

}
