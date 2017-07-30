package br.com.kecyo.deviceapp.gateways.impl;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.gateways.DeviceGateway;
import br.com.kecyo.deviceapp.gateways.repository.mongo.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeviceGatewayImpl implements DeviceGateway {

    private final DeviceRepository repository;

    @Override
    public Page<Device> findAll(int page) {
        log.info("Gateway FindAll");
        return repository.findAll(new PageRequest(page, 500));
    }

    @Override
    public Optional<Device> findById(final String id) {
        log.info("Gateway FindById {}", id);
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public void save(final Device device) {
        log.info("Gateway save {}", device);
        repository.save(device);
    }
}
