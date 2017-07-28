package br.com.kecyo.deviceapp.gateways;


import br.com.kecyo.deviceapp.entities.Device;

import java.util.List;

public interface DeviceGateway {

    List<Device> findAll();

    Device findById(final String id);

    void save(final Device device);
}
