package com.sp.product_catalog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    private final ObjectMapper objectMapper;

    public Utility() {
        this.objectMapper  = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public <T> T sanitizeRequestBody(T object, Class<? extends T> type) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        String refactoredString = StringEscapeUtils.escapeEcmaScript(
                StringEscapeUtils.escapeHtml4(objectMapper.writeValueAsString(object)));
        refactoredString = replaceEscapeChar(refactoredString);
        return objectMapper.readValue(refactoredString, type);
    }

    public <T> T sanitizeObject(T object, Class<T> classType) {
        if (object == null) {
            return null;
        }
        String refactoredString = StringEscapeUtils.escapeEcmaScript(StringEscapeUtils.escapeHtml4(object.toString().strip()));
        refactoredString = replaceEscapeChar(refactoredString);
        return classType.cast(refactoredString);
    }

    private static String replaceEscapeChar(String refactoredString) {
        return refactoredString.replace("&quot;", "\"");
    }

}
