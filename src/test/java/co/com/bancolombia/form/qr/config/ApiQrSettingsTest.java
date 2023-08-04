package co.com.bancolombia.form.qr.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApiQrSettings.class)
public class ApiQrSettingsTest {

    private final String channel = "channel";
    private final String clientId = "clientId";
    private final String clientSecret = "clientSecret";
    private final String host = "host";
    private final String path = "path";
    private final String resourceQrCodes = "resourceQrCodes";
    private final String resourceRequestShipping = "resourceRequestShipping";
    private final String hostRenew = "hostRenew";
    private final String pathRenew = "pathRenew";
    private final String operation = "operation";


    private ApiQrSettings actual;

    @Before
    public void setUp() {
        actual = new ApiQrSettings();
        actual.setChannel(channel);
        actual.setHost(host);
        actual.setPath(path);
        actual.setResourceQrCodes(resourceQrCodes);
        actual.setResourceRequestShipping(resourceRequestShipping);
        actual.setHostRenew(hostRenew);
        actual.setPathRenew(pathRenew);
        actual.setOperation(operation);
    }

    @Test
    public void toStringOkTest() {
        String expected = String.format("CaptchaSettings [host=%s, path=%s, resourceQrCodes=%s," +
                        " resourceRequestShipping=%s, channel=%s, fullPathQrCodes=%s, " +
                        "fullPathRequestShipping=%s,hostRenew=%s, pathRenew=%s, operation=%s," +
                        " FullPathRequestRenew]", actual.getHost(), actual.getPath(), actual.getResourceQrCodes(),
                actual.getResourceRequestShipping(), actual.getChannel(), actual.getFullPathQrCodes(),
                actual.getFullPathRequestShipping(), actual.getHostRenew(), actual.getPathRenew(),
                actual.getOperation(), actual.getFullPathRequestRenew());
        assertEquals(expected, actual.toString());
    }

}
