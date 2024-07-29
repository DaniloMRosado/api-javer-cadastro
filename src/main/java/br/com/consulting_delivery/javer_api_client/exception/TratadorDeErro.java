package br.com.consulting_delivery.javer_api_client.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErro {
    public TratadorDeErro() {
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity clienteComIdInvalido() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> tratarMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult excecao = e.getBindingResult();
        Map<String, String> camposInvalidos = new HashMap<>();

        for (FieldError campoInvalido : excecao.getFieldErrors()) {
            camposInvalidos.put(campoInvalido.getField(), campoInvalido.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(camposInvalidos);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<String> tratarHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Erro na requisição: corpo da requisição ausente");
    }

    @ExceptionHandler({UnrecognizedPropertyException.class})
    public ResponseEntity propriedadeDesconhecida(UnrecognizedPropertyException exception) {
        HashMap<Object, Object> camposInvalido = new HashMap();
        camposInvalido.put("Erro", "Propriedade inválida: " + exception.getPropertyName());
        return ResponseEntity.badRequest().body(camposInvalido);
    }

}
