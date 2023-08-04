package co.com.bancolombia.form.qr.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.model.HabeasData;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HabeasData.class)
public class HabeasDataTest {
	
	private final String agreementDateTime = "agreementDateTime";
	private final String version = "version";
	
	private HabeasData actual;
	
	@Before
	public void setUp() {
		actual = new HabeasData();
		actual.setAgreementDateTime(agreementDateTime);
		actual.setVersion(version);
	}

	@Test
	public void toStringOktest() {
		assertEquals(String.format("HabeasData [agreementDateTime=%s, version=%s]", actual.getAgreementDateTime(), actual.getVersion()), actual.toString());
	}

}
