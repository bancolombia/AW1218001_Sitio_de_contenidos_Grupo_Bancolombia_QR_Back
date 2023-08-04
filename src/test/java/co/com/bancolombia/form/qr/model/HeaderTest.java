package co.com.bancolombia.form.qr.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Header.class)
public class HeaderTest {

	private final String type = "type";
	private final String id = "id"; 
	
	private Header actual;
	
	@Before
	public void setUp() {
		actual = new Header();
		actual.setType(type);
		actual.setId(id);
	}
	
	@Test
	public void toStringOkTest() {
		assertEquals(String.format("Header [type=%s, id=%s]", actual.getType(), actual.getId()), actual.toString());
	}

}
