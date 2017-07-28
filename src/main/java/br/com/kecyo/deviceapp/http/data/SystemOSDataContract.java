package br.com.kecyo.deviceapp.http.data;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SystemOSDataContract implements Serializable {

    @ApiModelProperty("Name System OS")
    private String name;

    @ApiModelProperty("Version System OS")
    private String version;

}