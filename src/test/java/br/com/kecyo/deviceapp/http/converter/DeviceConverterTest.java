package br.com.kecyo.deviceapp.http.converter;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.util.ObjectMapperConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static br.com.kecyo.deviceapp.util.asserts.AssertDevice.assertDevice;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DeviceConverterTest {

    private DeviceConverter converter;

    private ObjectMapper mapper = ObjectMapperConfig.getObjectMapper();

    @Before
    public void before() {
        converter = new DeviceConverter(mapper);
    }


    @Test
    public void testValidConverter() throws URISyntaxException, IOException {
        String json = Files.toString(new File(getUrl("json/deviceContract.json")), Charset.defaultCharset());

        DeviceDataContract deviceDataContract = mapper.readValue(json, DeviceDataContract.class);

        Device device = converter.convert(deviceDataContract);

        assertThat(device, is(notNullValue()));
        assertDevice(device);
    }

    private URI getUrl(String path) throws URISyntaxException {
        return this.getClass().getClassLoader().getResource(path).toURI();
    }

}
