
package br.com.consulting_delivery.javer_api_client.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.persistence.EntityNotFoundException;

public class CustomErrorDecoder implements ErrorDecoder {

    public CustomErrorDecoder() {
    }

    public Exception decode(String methodKey, Response response) {
        System.out.println(response);
        if (response.status() == 400) return new EntityNotFoundException();
        return new Exception();
    }
}
