package br.com.kecyo.deviceapp.utils;

import br.com.kecyo.deviceapp.http.DeviceController;
import br.com.kecyo.deviceapp.usescases.impl.DeviceSave;
import br.com.kecyo.deviceapp.usescases.impl.DeviceSearch;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {
        DeviceController.class
}, secure = false)
public class WebMvcTestBase {

    @MockBean
    protected DeviceSave deviceSave;

    @MockBean
    protected DeviceSearch deviceSearch;

}
