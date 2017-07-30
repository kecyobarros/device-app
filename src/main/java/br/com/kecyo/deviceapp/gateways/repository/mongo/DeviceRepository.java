package br.com.kecyo.deviceapp.gateways.repository.mongo;

import br.com.kecyo.deviceapp.entities.Device;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public interface DeviceRepository extends MongoRepository<Device, String> {}
