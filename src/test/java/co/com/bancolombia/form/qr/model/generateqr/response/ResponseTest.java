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
@ContextConfiguration(classes = Response.class)
public class ResponseTest {

    private Response actual;

    @Before
    public void setUp() {
        var list = Collections.singletonList("data");

        actual = new Response();
        actual.setIdQR("data");
        actual.setImage("image");
        actual.setIdRequest(list);


    }

    @Test
    public void toStringOkTest() {

        assertEquals("data", actual.getIdQR());
        assertEquals("image", actual.getImage());
        assertEquals(1, actual.getIdRequest().size());
    }

}
