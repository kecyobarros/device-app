package br.com.kecyo.deviceapp.usescases.impl;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.gateways.DeviceGateway;
import br.com.kecyo.deviceapp.http.converter.DeviceDataContractConverter;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.usescases.exception.DeviceNotFoundException;
import br.com.kecyo.deviceapp.utils.ObjectMapperConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.kecyo.deviceapp.utils.asserts.AssertDeviceDataContract.assertDeviceDataContract;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class DeviceSearchTest {

    private DeviceSearch deviceSearch;

    private DeviceGateway deviceGateway;

    private DeviceDataContractConverter dataContractConverter;

    private ObjectMapper mapper = ObjectMapperConfig.getObjectMapper();

    @Before
    public  void before(){
        deviceGateway = Mockito.mock(DeviceGateway.class);
        dataContractConverter = Mockito.mock(DeviceDataContractConverter.class);
        deviceSearch = new DeviceSearch(deviceGateway, dataContractConverter);
    }

    @Test
    public void findAllSuccess() throws IOException, URISyntaxException {
        when(deviceGateway.findAll(anyInt())).thenReturn(
                new PageImpl<Device>(Collections.singletonList(Device.builder().build())));

        when(dataContractConverter.convert(any(Device.class)))
                .thenReturn(createDeviceDataContract());

        Page<DeviceDataContract> result = deviceSearch.findAll(1);

        assertThat(result.getContent(), hasSize(1));
        assertThat(result.getTotalPages(), is(equalTo(1)));
        assertThat(result.getTotalElements(), is(equalTo(1L)));

        DeviceDataContract deviceDataContractResult = result.getContent().get(0);

        assertDeviceDataContract(deviceDataContractResult);
    }

    @Test
    public void findAllIsEmpty(){
        when(deviceGateway.findAll(anyInt())).thenReturn(new PageImpl<Device>(Collections.emptyList()));
        Page<DeviceDataContract> result = deviceSearch.findAll(0);
        assertThat(result.getContent(), hasSize(0));
        assertThat(result.getTotalPages(), is(equalTo(1)));
        assertThat(result.getTotalElements(), is(equalTo(0L)));
    }

    @Test
    public void findByIdSuccess() throws IOException, URISyntaxException {

        when(deviceGateway.findById(anyString())).thenReturn(Optional.ofNullable(Device.builder().build()));
        when(dataContractConverter.convert(any(Device.class))).thenReturn(createDeviceDataContract());

        DeviceDataContract result = deviceSearch.findById("1");

        assertDeviceDataContract(result);
    }

    @Test
    public void findByUserIdSuccess() throws IOException, URISyntaxException {

        when(deviceGateway.findByUserId(anyString())).thenReturn(Collections
                .singletonList(Device.builder().build()));
        when(dataContractConverter.convert(any(Device.class))).thenReturn(createDeviceDataContract());

        List<DeviceDataContract> result = deviceSearch.findByUserId("1");

        assertDeviceDataContract(result.get(0));
    }

    @Test(expected = DeviceNotFoundException.class)
    public void findByUserIdIsEmpty() throws IOException, URISyntaxException {

        when(deviceGateway.findByUserId(anyString())).thenReturn(Arrays.asList());
        when(dataContractConverter.convert(any(Device.class))).thenReturn(createDeviceDataContract());
        deviceSearch.findByUserId("1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUserIdParamNotInformed() throws IOException, URISyntaxException {
        deviceSearch.findByUserId(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdParamNotInformed() throws IOException, URISyntaxException {
        deviceSearch.findById(null);
    }

    @Test(expected = DeviceNotFoundException.class)
    public void findByIdNotFound() throws IOException, URISyntaxException {
        when(deviceGateway.findById(anyString())).thenReturn(Optional.empty());
        deviceSearch.findById("1");
    }

    private DeviceDataContract createDeviceDataContract() throws IOException, URISyntaxException {
        String json = Files.toString(new File(getUrl("json/deviceContract.json")), Charset.defaultCharset());
        return mapper.readValue(json, DeviceDataContract.class);
    }

    private URI getUrl(String path) throws URISyntaxException {
        return this.getClass().getClassLoader().getResource(path).toURI();
    }
}
