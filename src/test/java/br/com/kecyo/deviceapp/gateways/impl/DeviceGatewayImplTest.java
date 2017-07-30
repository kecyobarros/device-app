package br.com.kecyo.deviceapp.gateways.impl;

import br.com.kecyo.deviceapp.entities.Device;
import br.com.kecyo.deviceapp.gateways.DeviceGateway;
import br.com.kecyo.deviceapp.gateways.repository.mongo.DeviceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class DeviceGatewayImplTest {

    private DeviceGateway deviceGateway;

    private DeviceRepository repository;

    @Before
    public void before(){
        repository = mock(DeviceRepository.class);
        deviceGateway = new DeviceGatewayImpl(repository);
    }

    @Test
    public void testFindAll(){
        deviceGateway.findAll(1);
        verify(repository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testFindById(){
        Mockito.when(repository.findOne(anyString())).thenReturn(Device.builder().build());

        Optional<Device> result = deviceGateway.findById("1");

        verify(repository, times(1)).findOne(anyString());
        assertTrue(result.isPresent());
    }

    @Test
    public void testFindByIdIsEmpty(){
        Mockito.when(repository.findOne(anyString())).thenReturn(null);

        Optional<Device> result = deviceGateway.findById("1");

        verify(repository, times(1)).findOne(anyString());
        assertFalse(result.isPresent());
    }

    @Test
    public void testSave(){
        deviceGateway.save(Device.builder().build());
        verify(repository, times(1)).save(Matchers.any(Device.class));
    }

}
