package net.eutkin.redirect.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import net.eutkin.redirect.entity.RedirectType;

import java.io.IOException;

import static net.eutkin.redirect.entity.RedirectType.STATUS_307;
import static net.eutkin.redirect.entity.RedirectType.STATUS_308;

public class RedirectTypeDeserializer extends JsonDeserializer<RedirectType> {

    @Override
    public RedirectType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String s = p.getValueAsString();
        if ("307".equals(s)) return STATUS_307;
        if ("308".equals(s)) return STATUS_308;
        return RedirectType.valueOf(s.toUpperCase());
    }
}
