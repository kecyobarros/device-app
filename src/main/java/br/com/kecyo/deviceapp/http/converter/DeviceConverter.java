package br.com.kecyo.deviceapp.http.converter;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class DeviceConverter implements Converter<DeviceDataContract, Device> {

    @Override
    public Device convert(final DeviceDataContract deviceDataContract) {
        return null;
    }
}
