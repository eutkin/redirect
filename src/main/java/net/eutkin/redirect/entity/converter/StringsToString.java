package net.eutkin.redirect.entity.converter;

import org.springframework.lang.NonNull;

import javax.persistence.AttributeConverter;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

public class StringsToString implements AttributeConverter<List<String>, String> {

    private final static String SEPARATOR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return isNull(attribute) || attribute.isEmpty() ? null : String.join(SEPARATOR, attribute);
    }

    @Override
    @NonNull
    public List<String> convertToEntityAttribute(String dbData) {
        return isNull(dbData) ? emptyList() : asList(dbData.split(SEPARATOR));
    }
}
