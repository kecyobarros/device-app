package br.com.kecyo.deviceapp.utils;

import br.com.kecyo.deviceapp.entities.Device;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class ExtractorCSVTest {

    @Test
    public void testSuccess() throws IOException, URISyntaxException {
        Map<String, Optional<Device>> mapDevice = new ExtractorCSV().execute();

        List<Device> list = mapDevice.values()
                .stream()
                .map(Optional::get)
                .collect(Collectors.toList());

        assertThat(list, hasSize(16));
    }
}
