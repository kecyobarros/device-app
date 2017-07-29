package br.com.kecyo.deviceapp.http.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor
public class VenueDataContract implements Serializable {

    @ApiModelProperty(required = true)
    @NotEmpty
    private String categorie;

    @ApiModelProperty(required = true)
    @NotEmpty
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
    @NotEmpty
    private String address;

    @ApiModelProperty(required = true)
    @NotEmpty
    private String city;

    @ApiModelProperty(required = true)
    @NotEmpty
    private String state;

    @ApiModelProperty(required = true)
    @NotEmpty
    private String country;

}
