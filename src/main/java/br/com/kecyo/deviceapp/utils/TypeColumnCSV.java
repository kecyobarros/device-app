package br.com.kecyo.deviceapp.utils;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public enum TypeColumnCSV {


    INDEX_DEVICE_ID(0, String.class),
    INDEX_ID_USER(1, String.class),
    INDEX_OS_NAME(2, String.class),
    INDEX_OS_VERSION(3, String.class),
    INDEX_DEVICE_MODEL(4, String.class),
    INDEX_HOME(5, String.class),
    INDEX_WORK(6, String.class),
    INDEX_INSTALLED_APPS(7, String.class),
    INDEX_BATTERY_STATE(8, Integer.class),
    INDEX_BATTERY_PERCENTAGE(9, Integer.class),
    INDEX_ARRIVAL(10, LocalDateTime.class),
    INDEX_DEPARTURE(11, LocalDateTime.class),
    INDEX_CATAGORIE(12, String.class),
    INDEX_VENUE_NAME(13, String.class),
    INDEX_VENUE_TOTAL_TIME(14, Integer.class),
    INDEX_PRECISION(15, Integer.class),
    INDEX_VENUE_LONG_LAT(16, String.class),
    INDEX_ADDRESS(17, String.class),
    INDEX_CITY(18, String.class),
    INDEX_STATE(19, String.class),
    INDEX_COUNTRY(20, String.class),
    INDEX_LAST(21, String.class);

    TypeColumnCSV(int index, Class aClass){
        this.index = index;
        this.aClass = aClass;
    }

    @Getter
    private int index;

    private Class aClass;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    public static TypeColumnCSV findByIndex(int index){
        return Stream.of(values())
                .filter(typeColumnCSV -> typeColumnCSV.getIndex() == index)
                .findFirst().get();
    }

    public void valid(String value){
        if (!value.equals("null")){
            if (aClass == Integer.class){
                Integer.parseInt(value);
            }else{
                if(aClass == LocalDateTime.class){
                    LocalDateTime.parse(value, formatter);
                }
            }
        }
    }
}
