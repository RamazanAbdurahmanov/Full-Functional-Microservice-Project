package az.ramazan.ms_order.client.decoder;

import az.ramazan.ms_order.exception.CustomFeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

import static az.ramazan.ms_order.model.enums.ErrorMessage.*;

public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String s, Response response) {
        if (response.status()>=400&&response.status()<=499) {
            return new CustomFeignException(CLIENT_ERROR.getMessage());
        }
        if (response.status()>=500&&response.status()<=599){
            return new CustomFeignException(SERVER_ERROR.getMessage());
        }
        return defaultErrorDecoder.decode(s,response);
    }
}
