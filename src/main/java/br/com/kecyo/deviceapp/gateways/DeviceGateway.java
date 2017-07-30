package br.com.kecyo.deviceapp.gateways;


import br.com.kecyo.deviceapp.entities.Device;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DeviceGateway {

    Page<Device> findAll(int page);

    Optional<Device> findById(final String id);

    void save(final Device device);
}
