package br.com.kecyo.deviceapp.http.converter;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class DeviceDataContractConverter implements Converter<Device, DeviceDataContract> {

    @Override
    public DeviceDataContract convert(final Device device) {
        return null;
    }
}
