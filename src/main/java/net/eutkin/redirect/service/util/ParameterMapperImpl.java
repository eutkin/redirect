package net.eutkin.redirect.service.util;

import lombok.SneakyThrows;
import net.eutkin.redirect.entity.Destination;
import net.eutkin.redirect.entity.Parameter;
import net.eutkin.redirect.entity.ParameterName;
import net.eutkin.redirect.service.model.UrlMapping;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@Service
public class ParameterMapperImpl implements ParameterMapper {

    private final GuidValueGenerator guidValueGenerator;

    public ParameterMapperImpl(GuidValueGenerator guidValueGenerator) {
        this.guidValueGenerator = requireNonNull(guidValueGenerator, "GuidGenerator must not be null");
    }

    @Override
    @SneakyThrows
    public UrlMapping map(String queryString, Destination destination) {
        requireNonNull(destination);
        if (isNull(queryString)) return new UrlMapping(destination.getUrl());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(destination.getUrl().toString());

        EnumMap<ParameterName, Parameter> parametersFromDb = parametersToMap(destination.getParameters());
        EnumMap<ParameterName, String> parametersFromURL = queryStringToMap(requireNonNull(queryString));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(parametersFromURL.size());

        String guid = null;
        for (Map.Entry<ParameterName, Parameter> param : parametersFromDb.entrySet()) {
            String value = parametersFromURL.get(param.getKey());
            if (GUID.equalsIgnoreCase(value)) {
                value = guidValueGenerator.generate();
                guid = value;
            }
            if (param.getValue().isSlash()) {
                builder.pathSegment(value);
            } else {
                Parameter parameter = parametersFromDb.get(param.getKey());
                params.add(parameter.getDestName(), value);
            }
        }
        URL url = builder.queryParams(params).build().toUri().toURL();
        return new UrlMapping(url, guid);
    }


}
