package br.com.kecyo.deviceapp.entities;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Venue implements Serializable {

    private String categorie;

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