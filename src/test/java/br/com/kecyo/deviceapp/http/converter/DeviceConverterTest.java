package br.com.kecyo.deviceapp.http.converter;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.entities.SystemOS;
import br.com.kecyo.deviceapp.entities.Venue;
import br.com.kecyo.deviceapp.entities.Visit;
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
import java.util.Set;

import static org.hamcrest.Matchers.*;
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

    private void assertDevice(final Device device){
        assertThat(device.getId(), is(nullValue()));
        assertThat(device.getModel(), is(equalTo("Iphone")));
        assertSystemOS(device.getSystemOS());
        assertVisits(device.getVisits().get(0));
        assertAppInstalled(device.getAppsInstalled());
    }

    private void assertAppInstalled(final Set<String> appInstalled){
        assertThat(appInstalled, is(notNullValue()));
        assertThat(appInstalled, hasSize(2));
        assertThat(appInstalled, hasItems("facebook", "uber"));
    }

    private void assertSystemOS(final SystemOS systemOS){
        assertThat(systemOS, is(notNullValue()));
        assertThat(systemOS.getName(), is(equalTo("IPhoneOS")));
        assertThat(systemOS.getVersion(), is(equalTo("10")));
    }

    private void assertVisits(final Visit visit){
        assertThat(visit, is(notNullValue()));
        assertThat(visit.getArrival().toString(), is(equalTo("2017-07-29T02:43:16.657")));
        assertThat(visit.getBatteryPercentage(), is(equalTo(10)));
        assertThat(visit.getBatteryState(), is(equalTo(10)));
        assertThat(visit.getDeparture().toString(), is(equalTo("2017-07-29T02:43:16.657")));
        assertVenue(visit.getVenue());
    }

    private void assertVenue(final Venue venue){
        assertThat(venue, is(notNullValue()));
        assertThat(venue.getAddress(), is(equalTo("Rua Doutor Plinio Barreto")));
        assertThat(venue.getCategorie(), is(equalTo("Rua")));
        assertThat(venue.getCity(), is(equalTo("São Paulo")));
        assertThat(venue.getCountry(), is(equalTo("São Paulo")));
        assertThat(venue.getLatitude(), is(equalTo(1111D)));
        assertThat(venue.getLongitude(), is(equalTo(11111D)));
        assertThat(venue.getName(), is(equalTo("Kecyo 2")));
        assertThat(venue.getPrecision(), is(equalTo(0)));
        assertThat(venue.getState(), is(equalTo("teste")));
        assertThat(venue.getTotalTime(), is(equalTo(1)));

    }




    private URI getUrl(String path) throws URISyntaxException {
        return this.getClass().getClassLoader().getResource(path).toURI();
    }

}
