package br.com.kecyo.deviceapp.usescases;

import br.com.kecyo.deviceapp.usescases.exception.DeviceNotFoundException;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DeviceNotFoundExceptionTest {

    @Test
    public void testException(){
        DeviceNotFoundException deviceNotFoundException = new DeviceNotFoundException();
        assertThat(deviceNotFoundException.getMessage(), is(equalTo("Device Not Found!")));

    }
}
