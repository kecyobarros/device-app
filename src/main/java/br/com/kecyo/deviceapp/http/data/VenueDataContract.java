package br.com.kecyo.deviceapp.http.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class VenueDataContract implements Serializable {

    @ApiModelProperty(required = true)
    private String categorie;

    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(required = true)
    private double longitude;

    @ApiModelProperty(required = true)
    private double latitude;

    @ApiModelProperty(required = true)
    private int totalTime;

    @ApiModelProperty(required = true)
    private int precision;

    @ApiModelProperty(required = true)
    private String address;

    @ApiModelProperty(required = true)
    private String city;

    @ApiModelProperty(required = true)
    private String state;

    @ApiModelProperty(required = true)
    private String country;

}
