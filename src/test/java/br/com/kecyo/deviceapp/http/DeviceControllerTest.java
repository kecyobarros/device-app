package br.com.kecyo.deviceapp.http;

import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.usescases.exception.DeviceNotFoundException;
import br.com.kecyo.deviceapp.utils.ObjectMapperConfig;
import br.com.kecyo.deviceapp.utils.WebMvcTestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DeviceControllerTest extends WebMvcTestBase {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = ObjectMapperConfig.getObjectMapper();

    @Test
    public void endpointDeviceFindAll() throws Exception {
        given(deviceSearch.findAll(anyInt())).willReturn(
                new PageImpl<DeviceDataContract>(Collections.singletonList(createDeviceDataContract())));

        mvc.perform(get(EndPointMapping.DEVICE+"/{page}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].model").value("Iphone"))
                .andExpect(jsonPath("$.content.[0].systemOS.name").value("IPhoneOS"))
                .andExpect(jsonPath("$.content.[0].systemOS.version").value("10"))
                .andExpect(jsonPath("$.content.[0].appsInstalled.[0]").value("facebook"))
                .andExpect(jsonPath("$.content.[0].appsInstalled.[1]").value("uber"))
                .andExpect(jsonPath("$.content.[0].visits.[0].batteryPercentage").value(10))
                .andExpect(jsonPath("$.content.[0].visits.[0].batteryState").value(10))
                .andExpect(jsonPath("$.content.[0].visits.[0].categorie").value("Rua"))
                .andExpect(jsonPath("$.content.[0].visits.[0].venue.address").value("Rua Doutor Plinio Barreto"))
                .andExpect(jsonPath("$.content.[0].visits.[0].venue.city").value("São Paulo"))
                .andExpect(jsonPath("$.content.[0].visits.[0].venue.country").value("São Paulo"))
                .andExpect(jsonPath("$.content.[0].visits.[0].venue.latitude").value(1111))
                .andExpect(jsonPath("$.content.[0].visits.[0].venue.longitude").value(11111))
                .andExpect(jsonPath("$.content.[0].visits.[0].venue.name").value("Kecyo 2"))
                .andExpect(jsonPath("$.content.[0].visits.[0].venue.precision").value(0))
                .andExpect(jsonPath("$.content.[0].visits.[0].venue.state").value("teste"))
                .andExpect(jsonPath("$.content.[0].visits.[0].venue.totalTime").value(1))
                .andExpect(jsonPath("$.content.[0].visits.[0].arrival").value("2017-07-29T02:43:16.657"))
                .andExpect(jsonPath("$.content.[0].visits.[0].departure").value("2017-07-29T02:43:16.657"));

    }

    @Test
    public void endpointDeviceFindById() throws Exception {
        given(deviceSearch.findById(anyString())).willReturn(createDeviceDataContract());

        mvc.perform(get(EndPointMapping.DEVICE+"/id/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Iphone"))
                .andExpect(jsonPath("$.systemOS.name").value("IPhoneOS"))
                .andExpect(jsonPath("$.systemOS.version").value("10"))
                .andExpect(jsonPath("$.appsInstalled.[0]").value("facebook"))
                .andExpect(jsonPath("$.appsInstalled.[1]").value("uber"))
                .andExpect(jsonPath("$.visits.[0].batteryPercentage").value(10))
                .andExpect(jsonPath("$.visits.[0].batteryState").value(10))
                .andExpect(jsonPath("$.visits.[0].categorie").value("Rua"))
                .andExpect(jsonPath("$.visits.[0].venue.address").value("Rua Doutor Plinio Barreto"))
                .andExpect(jsonPath("$.visits.[0].venue.city").value("São Paulo"))
                .andExpect(jsonPath("$.visits.[0].venue.country").value("São Paulo"))
                .andExpect(jsonPath("$.visits.[0].venue.latitude").value(1111))
                .andExpect(jsonPath("$.visits.[0].venue.longitude").value(11111))
                .andExpect(jsonPath("$.visits.[0].venue.name").value("Kecyo 2"))
                .andExpect(jsonPath("$.visits.[0].venue.precision").value(0))
                .andExpect(jsonPath("$.visits.[0].venue.state").value("teste"))
                .andExpect(jsonPath("$.visits.[0].venue.totalTime").value(1))
                .andExpect(jsonPath("$.visits.[0].arrival").value("2017-07-29T02:43:16.657"))
                .andExpect(jsonPath("$.visits.[0].departure").value("2017-07-29T02:43:16.657"));

    }

    @Test
    public void endpointDeviceFindByIdNotFound() throws Exception {
        given(deviceSearch.findById(anyString())).willThrow(new DeviceNotFoundException());

        mvc.perform(get(EndPointMapping.DEVICE+"/id/{id}", "1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Device Not Found!"));

    }

    @Test
    public void endpointDeviceFindByIdInternalServerError() throws Exception {
        given(deviceSearch.findById(anyString())).willThrow(new RuntimeException());
        mvc.perform(get(EndPointMapping.DEVICE+"/id/{id}", "1"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void endpointDeviceCreate() throws Exception {
        given(deviceSave.save(any(DeviceDataContract.class))).willReturn("12312312321");

        mvc.perform(post(EndPointMapping.DEVICE).contentType(MediaType.APPLICATION_JSON)
                .content(getJson()))
                .andExpect(status().isCreated())
                .andExpect(content().string("12312312321"))
                .andExpect(redirectedUrl("http://localhost/device/id/12312312321"));
    }

    @Test
    public void endpointInvalidData() throws Exception {
        mvc.perform(post(EndPointMapping.DEVICE).contentType(MediaType.APPLICATION_JSON)
                .content(getJsonInvalid()))
                .andExpect(status().isBadRequest());
    }

    private String getJsonInvalid() throws URISyntaxException, IOException {
        return loadFile("json/deviceContractInvalid.json");
    }

    private String getJson() throws URISyntaxException, IOException {
        return loadFile("json/deviceContract.json");
    }

    private String loadFile(final String path) throws URISyntaxException, IOException {
        return Files.toString(new File(getUrl(path)), Charset.defaultCharset());
    }

    private DeviceDataContract createDeviceDataContract() throws IOException, URISyntaxException {
        return mapper.readValue(getJson(), DeviceDataContract.class);
    }

    private URI getUrl(String path) throws URISyntaxException {
        return this.getClass().getClassLoader().getResource(path).toURI();
    }
}
