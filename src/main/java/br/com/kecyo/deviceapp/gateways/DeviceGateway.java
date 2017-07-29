package br.com.kecyo.deviceapp.gateways;


import br.com.kecyo.deviceapp.entities.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceGateway {

    List<Device> findAll();

    Optional<Device> findById(final String id);

    void save(final Device device);
}
