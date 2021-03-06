package br.com.kecyo.deviceapp.usescases.impl;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.gateways.DeviceGateway;
import br.com.kecyo.deviceapp.http.converter.DeviceDataContractConverter;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.usescases.exception.DeviceNotFoundException;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeviceSearch {

    private final DeviceGateway deviceGateway;

    private final DeviceDataContractConverter dataContractConverter;

    public Page<DeviceDataContract> findAll(int page) {
        log.info("Search FindAll");
        return deviceGateway
                        .findAll(page)
                        .map(this::convert);
    }

    public List<DeviceDataContract> findByUserId(final String userId) {
        log.info("Search findByUserId");

        Preconditions.checkArgument(!isEmpty(userId), "userId is Required");

        List<Device> result = deviceGateway
                .findByUserId(userId);

        if (result.isEmpty()){
            throw new DeviceNotFoundException();
        }

        return result
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private DeviceDataContract convert(final Device device){
        return dataContractConverter.convert(device);
    }

    public DeviceDataContract findById(final String id) {
        log.info("Search FindById: {}", id);

        Preconditions.checkArgument(!isEmpty(id), "Id is Required");

        final Device device = deviceGateway.findById(id)
                                            .orElseThrow(DeviceNotFoundException::new);

        return convert(device);
    }

}
