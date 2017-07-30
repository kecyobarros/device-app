package br.com.kecyo.deviceapp.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class Visit implements Serializable {

    private int batteryState;

    private int batteryPercentage;

    private LocalDateTime arrival;

    private LocalDateTime departure;

    private String categorie;

    private Venue venue;

}
