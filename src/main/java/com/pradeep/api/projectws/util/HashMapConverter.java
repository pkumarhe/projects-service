package com.pradeep.api.projectws.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter(autoApply = true)
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String, Object> mapdata) {
        final ObjectMapper objectMapper = new ObjectMapper();
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(mapdata);
        } catch (final JsonProcessingException e) {
        }
        return customerInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        final ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(s,
                    new TypeReference<HashMap<String, Object>>() {});
        } catch (final IOException e) {

        }

        return customerInfo;
    }
}
