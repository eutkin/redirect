package net.eutkin.redirect.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import net.eutkin.redirect.entity.RedirectType;

import java.io.IOException;

import static net.eutkin.redirect.entity.RedirectType.STATUS_307;
import static net.eutkin.redirect.entity.RedirectType.STATUS_308;

public class RedirectTypeSerializer extends JsonSerializer<RedirectType> {

    @Override
    public void serialize(RedirectType value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        String s = from(value);
        gen.writeString(s);

    }

    private String from(RedirectType type) {
        if (STATUS_307.equals(type)) return "307";
        if (STATUS_308.equals(type)) return "308";
        return type.name();
    }
}
