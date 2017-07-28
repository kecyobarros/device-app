package br.com.kecyo.deviceapp.http.data;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


public class DeviceDataContract implements Serializable {

    @ApiModelProperty(required = true)
    private String id;

    private SystemOSDataContract systemOS;

    @ApiModelProperty(required = true)
    private String model;

    @ApiModelProperty(required = true)
    private Set<String> appsInstalled;

    @ApiModelProperty(required = true)
    private List<VisitDataContract>  visits;

}
