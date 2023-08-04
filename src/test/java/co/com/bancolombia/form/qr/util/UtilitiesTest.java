package co.com.bancolombia.form.qr.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.bancolombia_home_rest.Constant;
import co.com.bancolombia.bancolombia_home_rest.HomeRest;
import co.com.bancolombia.bancolombia_home_rest.structure.Response;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Utilities.class)
public class UtilitiesTest {

	@Autowired
	private Utilities utilities;
	
	private HomeRest home;
	private Response response;
	
	@Before
	public void setUp() {
		home = mock(HomeRest.class);
		response = new Response();
	}
	
	@Test
	public void getEmptyResponseBadRequestTest() {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(Constant.CODE, Constant.BAD_REQUEST_ERROR_CODE);
		data.put(Constant.DESCRIPTION, Constant.BAD_REQUEST_ERROR_DESCRIPTION);
		response.setData(data);
		
		when(home.generateEmptyReponse(null, Constant.BAD_REQUEST_ERROR_CODE, Constant.BAD_REQUEST_ERROR_DESCRIPTION)).thenReturn(response);
		
		Response expected = utilities.getEmptyResponse(home, HttpStatus.BAD_REQUEST);
		assertEquals(Constant.BAD_REQUEST_ERROR_CODE, expected.getData().get(Constant.CODE));
		assertEquals(Constant.BAD_REQUEST_ERROR_DESCRIPTION, expected.getData().get(Constant.DESCRIPTION));
	}
	
	@Test
	public void getEmptyResponseUnauthoorizedTest() {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(Constant.CODE, Constant.UNAUTHORIZED_ERROR_CODE);
		data.put(Constant.DESCRIPTION, Constant.UNAUTHORIZED_ERROR_DESCRIPTION);
		response.setData(data);
		
		when(home.generateEmptyReponse(null, Constant.UNAUTHORIZED_ERROR_CODE, Constant.UNAUTHORIZED_ERROR_DESCRIPTION)).thenReturn(response);
		
		Response expected = utilities.getEmptyResponse(home, HttpStatus.UNAUTHORIZED);
		assertEquals(Constant.UNAUTHORIZED_ERROR_CODE, expected.getData().get(Constant.CODE));
		assertEquals(Constant.UNAUTHORIZED_ERROR_DESCRIPTION, expected.getData().get(Constant.DESCRIPTION));
	}
	
	@Test
	public void getEmptyResponseInternalServerErrorTest() {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(Constant.CODE, Constant.INTERNAL_SERVER_ERROR_CODE);
		data.put(Constant.DESCRIPTION, Constant.INTERNAL_SERVER_ERROR_DESCRIPTION);
		response.setData(data);
		
		when(home.generateEmptyReponse(null, Constant.INTERNAL_SERVER_ERROR_CODE, Constant.INTERNAL_SERVER_ERROR_DESCRIPTION)).thenReturn(response);
		
		Response expected = utilities.getEmptyResponse(home, HttpStatus.INTERNAL_SERVER_ERROR);
		assertEquals(Constant.INTERNAL_SERVER_ERROR_CODE, expected.getData().get(Constant.CODE));
		assertEquals(Constant.INTERNAL_SERVER_ERROR_DESCRIPTION, expected.getData().get(Constant.DESCRIPTION));
	}
	
	@Test
	public void generateEmptyReponseOkTest() {
		
		String messageId = "message-id";
		utilities.generateEmptyReponse(messageId, Constant.BAD_REQUEST_ERROR_CODE, Constant.BAD_REQUEST_ERROR_DESCRIPTION);
	}
	
}
