package br.com.kecyo.deviceapp.http.exception;

import br.com.kecyo.deviceapp.usescases.exception.DeviceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ExceptionProvider {

    @ExceptionHandler(value = DeviceNotFoundException.class)
    public ResponseEntity<String> resourceDeviceNotFound(final DeviceNotFoundException ex) {
        log.info("Handler Exception DeviceNotFound: {}", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .contentType(MediaType.TEXT_PLAIN)
                             .body(ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(final Exception ex){
        log.info("Handler Exception: {}", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.TEXT_PLAIN)
                .body(ex.getMessage());
    }
}
