package br.com.kecyo.deviceapp.usescases.impl;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.gateways.DeviceGateway;
import br.com.kecyo.deviceapp.http.converter.DeviceConverter;
import br.com.kecyo.deviceapp.http.converter.DeviceDataContractConverter;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.usescases.exception.DeviceNotFoundException;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class DeviceSearch {

    private final DeviceGateway deviceGateway;

    private final DeviceDataContractConverter dataContractConverter;

    public List<DeviceDataContract> findAll() {
        return deviceGateway
                        .findAll()
                        .stream()
                        .map(this::convert)
                        .collect(Collectors.toList());
    }

    private DeviceDataContract convert(final Device device){
        return dataContractConverter.convert(device);
    }

    public DeviceDataContract findById(final String id) {
        Preconditions.checkArgument(!isEmpty(id), "Id is Required");

        final Device device = deviceGateway.findById(id)
                                            .orElseThrow(DeviceNotFoundException::new);

        return convert(device);
    }

}
