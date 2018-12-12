package net.eutkin.redirect.service.util;

import lombok.val;
import net.eutkin.redirect.entity.Destination;
import net.eutkin.redirect.entity.Parameter;
import net.eutkin.redirect.entity.ParameterName;
import net.eutkin.redirect.service.model.UrlMapping;
import org.springframework.lang.NonNull;

import java.net.URL;
import java.util.Collection;
import java.util.EnumMap;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
public interface ParameterMapper {

    String GUID = "guid";

    UrlMapping map(String queryString, Destination destination);

    default UrlMapping map(URL url, Destination destination) {
        return map(requireNonNull(url).getQuery(), destination);
    }

    @NonNull
    default EnumMap<ParameterName, String> queryStringToMap(@NonNull String queryString) {
        val query = queryString.startsWith("?") ? queryString.substring(1) : queryString;
        val buffer = Stream.of(query.split("&"))
                .map(s -> s.split("="))
                .collect(toMap(a -> ParameterName.valueOf(a[0]), a -> a[1]));
        return new EnumMap<>(buffer);
    }

    @NonNull
    default EnumMap<ParameterName, Parameter> parametersToMap(@NonNull Collection<Parameter> parameters) {
        if (parameters.isEmpty()) return new EnumMap<>(ParameterName.class);
        val buffer = parameters.stream().collect(toMap(Parameter::getSrcName, p -> p));
        return new EnumMap<>(buffer);
    }
}
