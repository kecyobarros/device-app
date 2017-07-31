package br.com.kecyo.deviceapp.http.converter;

import br.com.kecyo.deviceapp.entities.*;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.utils.ObjectMapperConfig;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static br.com.kecyo.deviceapp.utils.asserts.AssertDeviceDataContract.assertDeviceDataContract;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DeviceDataContractConverterTest {

    private DeviceDataContractConverter converter;

    @Before
    public void before(){
        converter = new DeviceDataContractConverter(ObjectMapperConfig.getObjectMapper());
    }

    @Test
    public void successConverter(){
        DeviceDataContract deviceDataContract = converter.convert(createDevice());
        assertThat(deviceDataContract, is(notNullValue()));
        assertThat(deviceDataContract.getId(), is(equalTo("12331212")));
        assertDeviceDataContract(deviceDataContract);
    }

    @Test
    public void testToStringBuilder(){
        assertThat(Device.builder().toString(), is(equalTo("Device.DeviceBuilder(id=null, userId=null, systemOS=null, home=null, work=null, model=null, appsInstalled=null, visits=null, version=0)")));
        assertThat(Visit.builder().toString(), is(equalTo("Visit.VisitBuilder(batteryState=0, batteryPercentage=0, arrival=null, departure=null, categorie=null, venue=null)")));
        assertThat(Venue.builder().toString(), is(equalTo("Venue.VenueBuilder(name=null, longitude=0.0, latitude=0.0, totalTime=0, precision=0, address=null, city=null, state=null, country=null)")));
    }

    private Device createDevice(){
        return Device.builder()
                        .id("12331212")
                        .userId("12312312321")
                        .model("Iphone")
                        .appsInstalled(Sets.newHashSet("facebook", "uber"))
                        .visits(createListVisit())
                        .systemOS(createSystemOS())
                        .home(createHome())
                        .work(createWork())
                        .version(1)
                        .build();
    }

    private Home createHome(){
        return new Home(10 ,20);
    }

    private Work createWork(){
        return new Work(340, 40);
    }

    private List<Visit> createListVisit(){
        return Arrays.asList(Visit.builder()
                                    .arrival(LocalDateTime.parse("2017-07-29T02:43:16.657"))
                                    .batteryPercentage(10)
                                    .batteryState(10)
                                    .categorie("Rua")
                                    .departure(LocalDateTime.parse("2017-07-29T02:43:16.657"))
                                    .venue(createVenue())
                                    .build()

                            );


    }

    private Venue createVenue() {
        return Venue.builder()
                    .address("Rua Doutor Plinio Barreto")
                    .city("São Paulo")
                    .country("São Paulo")
                    .latitude(1111)
                    .longitude(11111)
                    .name("Kecyo 2")
                    .precision(0)
                    .state("teste")
                    .totalTime(1)
                    .build();
    }

    private SystemOS createSystemOS(){
        return new SystemOS("IPhoneOS", "10");
    }

}
