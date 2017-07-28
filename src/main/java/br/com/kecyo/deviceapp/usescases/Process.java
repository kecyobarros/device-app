package br.com.kecyo.deviceapp.usescases;

import br.com.kecyo.deviceapp.http.data.DeviceDataContract;

import java.util.List;

public interface Process {

    List<DeviceDataContract> findAll();

    DeviceDataContract findById(final String id);

    void save(final DeviceDataContract device);
}
