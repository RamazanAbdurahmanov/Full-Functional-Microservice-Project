package az.ramazan.ms_order.client.decoder;

import az.ramazan.ms_order.exception.CustomFeignException;
import com.fasterxml.jackson.databind.JsonNode;
import feign.Response;
import feign.codec.ErrorDecoder;

import static az.ramazan.ms_order.client.decoder.JsonNodeFieldName.*;
import static az.ramazan.ms_order.model.enums.ErrorMessage.*;
import static az.ramazan.ms_order.util.MapperUtil.MAPPER_UTIL;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        var errorMessage = CLIENT_ERROR.getMessage();
        var statusCode= response.status();


        JsonNode jsonNode;
        try (var body = response.body().asInputStream()) {
            jsonNode = MAPPER_UTIL.map(body, JsonNode.class);
        } catch (Exception exception) {
            throw new CustomFeignException(statusCode,CLIENT_ERROR.getMessage());
        }
        if (jsonNode.has(MESSAGE.getValue())) errorMessage = jsonNode.get(MESSAGE.getValue()).asText();
        return new CustomFeignException(statusCode,errorMessage);

    }
}
