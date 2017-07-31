package br.com.kecyo.deviceapp.gateways.repository.mongo;

import br.com.kecyo.deviceapp.entities.Device;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("!test")
public interface DeviceRepository extends PagingAndSortingRepository<Device, String> {

    List<Device> findByUserId(final String userId);
}
