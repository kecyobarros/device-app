package br.com.kecyo.deviceapp.http.exception;

import br.com.kecyo.deviceapp.usescases.exception.DeviceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExceptionProviderTest {

    private ExceptionProvider exceptionProvider;

    @Before
    public void before(){
        exceptionProvider = new ExceptionProvider();
    }

    @Test
    public void testResourceDeviceNotFound(){
        ResponseEntity<String> response = exceptionProvider.resourceDeviceNotFound(new DeviceNotFoundException());
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
        assertThat(response.getBody(), is(equalTo("Device Not Found!")));
    }

    @Test
    public void testMethodArgumentNotValidException(){
        ResponseEntity<String> response = exceptionProvider
                .resourceMethodArgumentNotValidException(Mockito.mock(MethodArgumentNotValidException.class));
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.BAD_REQUEST)));
    }

    @Test
    public void testHandleException(){
        ResponseEntity<String> response = exceptionProvider.handleException(new RuntimeException("Error"));
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.INTERNAL_SERVER_ERROR)));
        assertThat(response.getBody(), is(equalTo("Error")));
    }
}
