package br.com.kecyo.deviceapp.gateways.impl;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.gateways.DeviceGateway;
import br.com.kecyo.deviceapp.gateways.repository.mongo.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeviceGatewayImpl implements DeviceGateway {

    private final DeviceRepository repository;

    @Override
    public List<Device> findAll() {
        return repository.findAll();
    }

    @Override
    public Device findById(final String id) {
        return repository.findOne(id);
    }

    @Override
    public void save(final Device device) {
        repository.save(device);
    }
}
