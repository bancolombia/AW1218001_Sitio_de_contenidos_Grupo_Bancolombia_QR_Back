package co.com.bancolombia.form.qr.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Error.class)
public class ErrorTest {

	private final String code = "code";
	private final String detail = "detail";
	private final String href = "href";
	private final String status = "status";
	private final String title = "title";

	@Autowired
	private Error actual;

	@Before
	public void setUp() {
		actual.setCode(code);
		actual.setDetail(detail);
		actual.setHref(href);
		actual.setStatus(status);
		actual.setTitle(title);
	}

	@Test
	public void toStringOkTest() {
		assertEquals(
				String.format("Error [href=%s, status=%s, code=%s, title=%s, detail=%s]", actual.getHref(),
						actual.getStatus(), actual.getCode(), actual.getTitle(), actual.getDetail()),
				actual.toString());
	}

}
