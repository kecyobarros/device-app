package br.com.kecyo.deviceapp.usescases.impl;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.gateways.DeviceGateway;
import br.com.kecyo.deviceapp.http.converter.DeviceConverter;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.utils.ObjectMapperConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static br.com.kecyo.deviceapp.utils.asserts.AssertDevice.assertDevice;
import static org.mockito.Mockito.verify;

public class DeviceSaveTest {

    private DeviceSave deviceSave;

    private DeviceGateway deviceGateway;

    private DeviceConverter deviceConverter;

    private ObjectMapper mapper = ObjectMapperConfig.getObjectMapper();

    @Before
    public  void before(){
        deviceGateway = Mockito.mock(DeviceGateway.class);
        deviceConverter = new DeviceConverter(mapper);
        deviceSave = new DeviceSave(deviceGateway, deviceConverter);
    }

    @Test
    public void saveSuccess() throws IOException, URISyntaxException {
        ArgumentCaptor<Device> deviceArgumentCaptor = ArgumentCaptor.forClass(Device.class);
        deviceSave.save(createDeviceDataContract());

        verify(deviceGateway).save(deviceArgumentCaptor.capture());

        Device deviceSave = deviceArgumentCaptor.getValue();

        assertDevice(deviceSave);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionDeviceRequired() throws IOException, URISyntaxException {
        deviceSave.save(null);
    }

    private DeviceDataContract createDeviceDataContract() throws IOException, URISyntaxException {
        String json = Files.toString(new File(getUrl("json/deviceContract.json")), Charset.defaultCharset());
        return mapper.readValue(json, DeviceDataContract.class);
    }

    private URI getUrl(String path) throws URISyntaxException {
        return this.getClass().getClassLoader().getResource(path).toURI();
    }
}
