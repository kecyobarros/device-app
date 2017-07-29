package br.com.kecyo.deviceapp.http;

import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.usescases.impl.DeviceSave;
import br.com.kecyo.deviceapp.usescases.impl.DeviceSearch;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(EndPointMapping.DEVICE)
public class DeviceController {

    private final DeviceSave deviceSave;

    private final DeviceSearch deviceSearch;

    @ApiOperation(value = "Request All Devices")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Device found")
    })
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeviceDataContract>> findAll() {
        log.info("Endpoint: findAll");
        final List<DeviceDataContract> deviceAll = deviceSearch.findAll();
        return ResponseEntity.ok(deviceAll);
    }

    @ApiOperation(value = "Request Device by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Device found!"),
            @ApiResponse(code = 404, message = "Device not found!")
    })
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{id}")
    public ResponseEntity<DeviceDataContract> findByNameAndResolution(@PathVariable("id") final String id) {
        log.info("Endpoint: findByNameAndResolution id={}", id);

        final DeviceDataContract device = deviceSearch.findById(id);
        return ResponseEntity.ok(device);
    }

    @ApiOperation(value = "Created Device")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Device created")
    })
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> create(@Valid @RequestBody final DeviceDataContract deviceDataContract,
                                         UriComponentsBuilder uriComponentsBuilder) {

        log.info("Endpoint: create value={}", deviceDataContract);
        String id = deviceSave.save(deviceDataContract);

        UriComponents uriComponents =
                uriComponentsBuilder.path(EndPointMapping.DEVICE.concat("{id}"))
                                    .buildAndExpand(id);

        return ResponseEntity
                .created(uriComponents.toUri())
                .body(id);
    }
}

