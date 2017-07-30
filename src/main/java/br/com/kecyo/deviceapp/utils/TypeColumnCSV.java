package br.com.kecyo.deviceapp.utils;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public enum TypeColumnCSV {


    INDEX_DEVICE_ID(0, String.class),
    INDEX_OS_NAME(1, String.class),
    INDEX_OS_VERSION(2, String.class),
    INDEX_DEVICE_MODEL(3, String.class),
    INDEX_HOME(4, String.class),
    INDEX_WORK(5, String.class),
    INDEX_INSTALLED_APPS(6, String.class),
    INDEX_BATTERY_STATE(7, Integer.class),
    INDEX_BATTERY_PERCENTAGE(8, Integer.class),
    INDEX_ARRIVAL(9, LocalDateTime.class),
    INDEX_DEPARTURE(10, LocalDateTime.class),
    INDEX_CATAGORIE(11, String.class),
    INDEX_VENUE_NAME(12, String.class),
    INDEX_VENUE_TOTAL_TIME(13, Integer.class),
    INDEX_PRECISION(14, Integer.class),
    INDEX_VENUE_LONG_LAT(15, String.class),
    INDEX_ADDRESS(16, String.class),
    INDEX_CITY(17, String.class),
    INDEX_STATE(18, String.class),
    INDEX_COUNTRY(19, String.class),
    INDEX_LAST(20, String.class);

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
