package br.com.kecyo.deviceapp.util.asserts;

import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.http.data.SystemOSDataContract;
import br.com.kecyo.deviceapp.http.data.VenueDataContract;
import br.com.kecyo.deviceapp.http.data.VisitDataContract;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AssertDeviceDataContract {

    public static void assertDeviceDataContract(final DeviceDataContract deviceDataContract){
        assertThat(deviceDataContract.getModel(), is(equalTo("Iphone")));
        assertSystemOSDataContract(deviceDataContract.getSystemOS());
        assertVisitsDataContract(deviceDataContract.getVisits().get(0));
        assertAppInstalled(deviceDataContract.getAppsInstalled());
    }

    private static void assertAppInstalled(final Set<String> appInstalled){
        assertThat(appInstalled, is(notNullValue()));
        assertThat(appInstalled, hasSize(2));
        assertThat(appInstalled, hasItems("facebook", "uber"));
    }

    private static void assertSystemOSDataContract(final SystemOSDataContract systemOSDataContract){
        assertThat(systemOSDataContract, is(notNullValue()));
        assertThat(systemOSDataContract.getName(), is(equalTo("IPhoneOS")));
        assertThat(systemOSDataContract.getVersion(), is(equalTo("10")));
    }

    private static void assertVisitsDataContract(final VisitDataContract visitDataContract){
        assertThat(visitDataContract, is(notNullValue()));
        assertThat(visitDataContract.getArrival().toString(), is(equalTo("2017-07-29T02:43:16.657")));
        assertThat(visitDataContract.getBatteryPercentage(), is(equalTo(10)));
        assertThat(visitDataContract.getBatteryState(), is(equalTo(10)));
        assertThat(visitDataContract.getDeparture().toString(), is(equalTo("2017-07-29T02:43:16.657")));
        assertVenueDataContract(visitDataContract.getVenue());
    }

    private static void assertVenueDataContract(final VenueDataContract venueDataContract){
        assertThat(venueDataContract, is(notNullValue()));
        assertThat(venueDataContract.getAddress(), is(equalTo("Rua Doutor Plinio Barreto")));
        assertThat(venueDataContract.getCategorie(), is(equalTo("Rua")));
        assertThat(venueDataContract.getCity(), is(equalTo("São Paulo")));
        assertThat(venueDataContract.getCountry(), is(equalTo("São Paulo")));
        assertThat(venueDataContract.getLatitude(), is(equalTo(1111D)));
        assertThat(venueDataContract.getLongitude(), is(equalTo(11111D)));
        assertThat(venueDataContract.getName(), is(equalTo("Kecyo 2")));
        assertThat(venueDataContract.getPrecision(), is(equalTo(0)));
        assertThat(venueDataContract.getState(), is(equalTo("teste")));
        assertThat(venueDataContract.getTotalTime(), is(equalTo(1)));
    }
}
