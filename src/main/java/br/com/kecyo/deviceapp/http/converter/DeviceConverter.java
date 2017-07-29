package br.com.kecyo.deviceapp.http.converter;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceConverter implements Converter<DeviceDataContract, Device> {

    private final ObjectMapper mapper;

    @Override
    public Device convert(final DeviceDataContract deviceDataContract) {
        log.info("Converter DeviceDataContract: {}", deviceDataContract);
        return mapper.convertValue(deviceDataContract, Device.class);
    }
}
