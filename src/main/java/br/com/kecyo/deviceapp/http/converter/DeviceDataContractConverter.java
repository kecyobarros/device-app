package br.com.kecyo.deviceapp.http.converter;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceDataContractConverter implements Converter<Device, DeviceDataContract>{

    private final ObjectMapper mapper;

    @Override
    public DeviceDataContract convert(final Device device) {
        return mapper.convertValue(device, DeviceDataContract.class);
    }
}
