package br.com.kecyo.deviceapp.usescases.impl;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.gateways.DeviceGateway;
import br.com.kecyo.deviceapp.http.converter.DeviceConverter;
import br.com.kecyo.deviceapp.http.converter.DeviceDataContractConverter;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.usescases.Process;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class DeviceProcessImpl implements Process {

    private final DeviceGateway deviceGateway;

    private final DeviceDataContractConverter dataContractConverter;

    private final DeviceConverter deviceConverter;


    @Override
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

    @Override
    public DeviceDataContract findById(final String id) {
        Preconditions.checkArgument(!isEmpty(id), "Id is Required");
        return convert(deviceGateway.findById(id));
    }

    @Override
    public void save(final DeviceDataContract deviceDataContract) {
        Preconditions.checkNotNull(deviceDataContract, "Device is Required");
        deviceGateway.save(deviceConverter.convert(deviceDataContract));
    }
}
