package br.com.kecyo.deviceapp.http.converter;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceConverter implements Converter<DeviceDataContract, Device> {

    private final ObjectMapper mapper;

    @Override
    public Device convert(final DeviceDataContract deviceDataContract) {
        return mapper.convertValue(deviceDataContract, Device.class);
    }
}
