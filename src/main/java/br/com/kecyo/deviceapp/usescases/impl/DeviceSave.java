package br.com.kecyo.deviceapp.usescases.impl;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.gateways.DeviceGateway;
import br.com.kecyo.deviceapp.http.converter.DeviceConverter;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeviceSave {

    private final DeviceGateway deviceGateway;

    private final DeviceConverter deviceConverter;

    public String save(final DeviceDataContract deviceDataContract) {
        log.info("save: {}", deviceDataContract);

        Preconditions.checkNotNull(deviceDataContract, "Device is Required");

        final Device device = deviceConverter.convert(deviceDataContract);

        deviceGateway.save(device);

        return device.getId();
    }
}
