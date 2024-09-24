package az.ramazan.ms_order.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public enum MapperUtil {
    MAPPER_UTIL;

    public <T> T map(InputStream sourece, Class<T> target) {
        try {
            return new ObjectMapper().readValue(sourece, target);
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
