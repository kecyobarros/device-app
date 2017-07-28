package br.com.kecyo.deviceapp.entities;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class Visit implements Serializable {

    private int batteryState;

    private int batteryPercentage;

    private LocalDateTime arrival;

    private LocalDateTime departure;

    private Venue venue;

}