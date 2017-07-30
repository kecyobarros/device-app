package br.com.kecyo.deviceapp.utils.asserts;

import br.com.kecyo.deviceapp.entities.*;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AssertDevice {

    public static void assertDevice(final Device device){
        assertThat(device.getId(), is(nullValue()));
        assertThat(device.getModel(), is(equalTo("Iphone")));
        assertSystemOS(device.getSystemOS());
        assertHome(device.getHome());
        assertWork(device.getWork());
        assertVisits(device.getVisits().get(0));
        assertAppInstalled(device.getAppsInstalled());
    }

    private static void assertAppInstalled(final Set<String> appInstalled){
        assertThat(appInstalled, is(notNullValue()));
        assertThat(appInstalled, hasSize(2));
        assertThat(appInstalled, hasItems("facebook", "uber"));
    }

    private static void assertSystemOS(final SystemOS systemOS){
        assertThat(systemOS, is(notNullValue()));
        assertThat(systemOS.getName(), is(equalTo("IPhoneOS")));
        assertThat(systemOS.getVersion(), is(equalTo("10")));
    }

    private static void assertHome(final Home home){
        assertThat(home, is(notNullValue()));
        assertThat(home.getLongitude(), is(equalTo(10D)));
        assertThat(home.getLatitude(), is(equalTo(20D)));
    }

    private static void assertWork(final Work work){
        assertThat(work, is(notNullValue()));
        assertThat(work.getLongitude(), is(equalTo(340D)));
        assertThat(work.getLatitude(), is(equalTo(40D)));
    }

    private static void assertVisits(final Visit visit){
        assertThat(visit, is(notNullValue()));
        assertThat(visit.getArrival().toString(), is(equalTo("2017-07-29T02:43:16.657")));
        assertThat(visit.getBatteryPercentage(), is(equalTo(10)));
        assertThat(visit.getBatteryState(), is(equalTo(10)));
        assertThat(visit.getDeparture().toString(), is(equalTo("2017-07-29T02:43:16.657")));
        assertThat(visit.getCategorie(), is(equalTo("Rua")));
        assertVenue(visit.getVenue());
    }

    private static void assertVenue(final Venue venue){
        assertThat(venue, is(notNullValue()));
        assertThat(venue.getAddress(), is(equalTo("Rua Doutor Plinio Barreto")));
        assertThat(venue.getCity(), is(equalTo("São Paulo")));
        assertThat(venue.getCountry(), is(equalTo("São Paulo")));
        assertThat(venue.getLatitude(), is(equalTo(1111D)));
        assertThat(venue.getLongitude(), is(equalTo(11111D)));
        assertThat(venue.getName(), is(equalTo("Kecyo 2")));
        assertThat(venue.getPrecision(), is(equalTo(0)));
        assertThat(venue.getState(), is(equalTo("teste")));
        assertThat(venue.getTotalTime(), is(equalTo(1)));

    }
}
