package br.com.kecyo.deviceapp.http.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class VisitDataContract implements Serializable {

    @DecimalMin(value = "0")
    @ApiModelProperty(required = true)
    private int batteryState;

    @ApiModelProperty(required = true)
    @DecimalMin(value = "0")
    private int batteryPercentage;

    @Setter
    @NotNull
    @ApiModelProperty(required = true, example = "2017-07-29T02:43:16.657")
    private LocalDateTime arrival;

    @Setter
    @NotNull
    @ApiModelProperty(required = true, example = "2017-07-29T02:43:16.657")
    private LocalDateTime departure;

    @Valid
    private VenueDataContract venue;

}
