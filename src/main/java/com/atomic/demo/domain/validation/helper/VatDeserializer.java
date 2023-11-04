package com.atomic.demo.domain.validation.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Optional;

public class VatDeserializer extends JsonDeserializer<Optional<String>> {
    @Override
    public Optional<String> deserialize(JsonParser p, DeserializationContext txt) throws IOException {
        String value = p.getValueAsString();
        if (value == null || value.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(value.trim());
    }
}
