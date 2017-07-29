package br.com.kecyo.deviceapp.http.data;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Getter
@ToString
public class SystemOSDataContract implements Serializable {

    @ApiModelProperty(required = true)
    @NotEmpty
    private String name;

    @ApiModelProperty(required = true)
    @NotEmpty
    private String version;

}