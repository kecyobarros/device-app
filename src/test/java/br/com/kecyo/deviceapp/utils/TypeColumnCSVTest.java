package br.com.kecyo.deviceapp.utils;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.time.format.DateTimeParseException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TypeColumnCSVTest {

    @Test
    public void testValidSize(){
        assertThat(TypeColumnCSV.values().length, is(22));
    }

    @Test
    public void testValidIndex(){
        assertThat(TypeColumnCSV.INDEX_ID_USER.getIndex(), is(0));
        assertThat(TypeColumnCSV.INDEX_DEVICE_ID.getIndex(), is(1));
        assertThat(TypeColumnCSV.INDEX_OS_NAME.getIndex(), is(2));
        assertThat(TypeColumnCSV.INDEX_OS_VERSION.getIndex(), is(3));
        assertThat(TypeColumnCSV.INDEX_DEVICE_MODEL.getIndex(), is(4));
        assertThat(TypeColumnCSV.INDEX_HOME.getIndex(), is(5));
        assertThat(TypeColumnCSV.INDEX_WORK.getIndex(), is(6));
        assertThat(TypeColumnCSV.INDEX_INSTALLED_APPS.getIndex(), is(7));
        assertThat(TypeColumnCSV.INDEX_BATTERY_STATE.getIndex(), is(8));
        assertThat(TypeColumnCSV.INDEX_BATTERY_PERCENTAGE.getIndex(), is(9));
        assertThat(TypeColumnCSV.INDEX_ARRIVAL.getIndex(), is(10));
        assertThat(TypeColumnCSV.INDEX_DEPARTURE.getIndex(), is(11));
        assertThat(TypeColumnCSV.INDEX_CATAGORIE.getIndex(), is(12));
        assertThat(TypeColumnCSV.INDEX_VENUE_NAME.getIndex(), is(13));
        assertThat(TypeColumnCSV.INDEX_VENUE_TOTAL_TIME.getIndex(), is(14));
        assertThat(TypeColumnCSV.INDEX_PRECISION.getIndex(), is(15));
        assertThat(TypeColumnCSV.INDEX_VENUE_LONG_LAT.getIndex(), is(16));
        assertThat(TypeColumnCSV.INDEX_ADDRESS.getIndex(), is(17));
        assertThat(TypeColumnCSV.INDEX_CITY.getIndex(), is(18));
        assertThat(TypeColumnCSV.INDEX_STATE.getIndex(), is(19));
        assertThat(TypeColumnCSV.INDEX_COUNTRY.getIndex(), is(20));
        assertThat(TypeColumnCSV.INDEX_LAST.getIndex(), is(21));
    }

    @Test
    public void testFindByIndex(){
        TypeColumnCSV typeColumnCSV = TypeColumnCSV.findByIndex(19);
        assertThat(typeColumnCSV, is(Matchers.equalTo(TypeColumnCSV.INDEX_STATE)));
    }

    @Test
    public void testFindValid(){
        TypeColumnCSV typeColumnCSV = TypeColumnCSV.findByIndex(19);
        typeColumnCSV.valid("null");
        assertThat(typeColumnCSV, is(Matchers.equalTo(TypeColumnCSV.INDEX_STATE)));
    }

    @Test
    public void testValidStringSuccess(){
        TypeColumnCSV typeColumnCSV = TypeColumnCSV.findByIndex(20);
        typeColumnCSV.valid("Teste");
        assertThat(typeColumnCSV, is(Matchers.equalTo(TypeColumnCSV.INDEX_COUNTRY)));
    }

    @Test
    public void testValidIntegerSuccess(){
        TypeColumnCSV typeColumnCSV = TypeColumnCSV.findByIndex(8);
        typeColumnCSV.valid("123");
        assertThat(typeColumnCSV, is(Matchers.equalTo(TypeColumnCSV.INDEX_BATTERY_STATE)));
    }

    @Test
    public void testValidLocalDateTimeSuccess(){
        TypeColumnCSV typeColumnCSV = TypeColumnCSV.findByIndex(11);
        typeColumnCSV.valid("29-07-2017 02:43:16");
        assertThat(typeColumnCSV, is(Matchers.equalTo(TypeColumnCSV.INDEX_DEPARTURE)));
    }

    @Test(expected = NumberFormatException.class)
    public void testFindValidErroType(){
        TypeColumnCSV typeColumnCSV = TypeColumnCSV.findByIndex(8);
        typeColumnCSV.valid("1211AS1");
    }

    @Test(expected = DateTimeParseException.class)
    public void testFindValidErroTypeLocalDateTime(){
        TypeColumnCSV typeColumnCSV = TypeColumnCSV.findByIndex(10);
        typeColumnCSV.valid("1211AS1");
    }

    @Test
    public void testToString(){
        TypeColumnCSV index_device_id = TypeColumnCSV.valueOf("INDEX_DEVICE_ID");
        assertThat(index_device_id, is(Matchers.equalTo(TypeColumnCSV.INDEX_DEVICE_ID)));
    }
}
