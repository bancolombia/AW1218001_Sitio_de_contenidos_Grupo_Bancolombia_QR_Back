package co.com.bancolombia.form.qr.model.generateqr.response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RenewResponse.class)
public class RenewResponseTest {

    private RenewResponse actual;

    @Before
    public void setUp() {
        Response response = new Response();
        var list = Collections.singletonList(response);

        actual = new RenewResponse();
        actual.setData(list);


    }

    @Test
    public void toStringOkTest() {
        assertEquals(1, actual.getData().size());
    }

}
