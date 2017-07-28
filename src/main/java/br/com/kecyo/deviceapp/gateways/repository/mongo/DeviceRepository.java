package br.com.kecyo.deviceapp.gateways.repository.mongo;

import br.com.kecyo.deviceapp.entities.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {}
