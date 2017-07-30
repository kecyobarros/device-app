package br.com.kecyo.deviceapp.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Builder
@ToString
public class Venue implements Serializable {

    private String name;

    private double longitude;

    private double latitude;

    private int totalTime;

    private int precision;

    private String address;

    private String city;

    private String state;

    private String country;

}
