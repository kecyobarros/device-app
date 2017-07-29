package br.com.kecyo.deviceapp.http.exception;

import br.com.kecyo.deviceapp.usescases.exception.DeviceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionProvider {

    @ExceptionHandler(value = DeviceNotFoundException.class)
    public ResponseEntity<String> resourceDeviceNotFound(final DeviceNotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(final Exception ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
