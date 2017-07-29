package br.com.kecyo.deviceapp.gateways.impl;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.gateways.DeviceGateway;
import br.com.kecyo.deviceapp.gateways.repository.mongo.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeviceGatewayImpl implements DeviceGateway {

    private final DeviceRepository repository;

    @Override
    public List<Device> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Device> findById(final String id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public void save(final Device device) {
        repository.save(device);
    }
}
