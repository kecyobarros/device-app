package br.com.kecyo.deviceapp.http.data;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VisitDataContract implements Serializable {

    @ApiModelProperty(required = true)
    private int batteryState;

    @ApiModelProperty(required = true)
    private int batteryPercentage;

    @ApiModelProperty(required = true)
    private LocalDateTime arrival;

    @ApiModelProperty(required = true)
    private LocalDateTime departure;

    private VenueDataContract venue;

}
