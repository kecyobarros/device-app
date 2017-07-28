package br.com.kecyo.deviceapp.http;

import br.com.kecyo.deviceapp.http.data.DeviceDataContract;
import br.com.kecyo.deviceapp.usescases.Process;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/devices")
public class DeviceController {

    private final Process processDevice;
    

    @ApiOperation(value = "Request All Devices")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Device found")
    })
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DeviceDataContract>> findAll() {

        final List<DeviceDataContract> deviceAll = processDevice.findAll();

        return ResponseEntity.ok(deviceAll);
    }

    @ApiOperation(value = "Request Device by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Device found")
    })
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{id}")
    public ResponseEntity<DeviceDataContract> findByNameAndResolution(@PathVariable("id") final String id) {
        final DeviceDataContract device = processDevice.findById(id);

        return ResponseEntity.ok(device);
    }

    @ApiOperation(value = "Created Device")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Device created")
    })
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody final DeviceDataContract deviceDataContract) {
        processDevice.save(deviceDataContract);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

