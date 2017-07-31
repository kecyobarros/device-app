package br.com.kecyo.deviceapp.http.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
public class DeviceDataContract implements Serializable {

    @ApiModelProperty(hidden = true)
    private String id;

    @ApiModelProperty(required = true)
    private String userId;

    @Valid
    private SystemOSDataContract systemOS;

    private HomeDataContract home;

    private WorkDataContract work;

    @ApiModelProperty(required = true)
    @NotEmpty
    private String model;

    @ApiModelProperty(required = true)
    @NotEmpty
    private Set<String> appsInstalled;

    @ApiModelProperty(required = true)
    @Valid
    private List<VisitDataContract>  visits;

}
