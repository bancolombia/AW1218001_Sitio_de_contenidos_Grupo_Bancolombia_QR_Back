package co.com.bancolombia.form.qr.model.generateqr.request;

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
@ContextConfiguration(classes = RenewDataRequest.class)
public class RenewDataRequestTest {

    private RenewDataRequest actual;

    @Before
    public void setUp() {
        RenewRequest response = new RenewRequest();
        var list = Collections.singletonList(response);

        actual = new RenewDataRequest();
        actual.setData(list);


    }

    @Test
    public void toStringOkTest() {
        assertEquals(1, actual.getData().size());
    }

}
