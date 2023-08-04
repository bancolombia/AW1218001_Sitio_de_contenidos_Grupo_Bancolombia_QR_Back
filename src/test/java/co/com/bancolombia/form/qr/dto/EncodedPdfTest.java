package co.com.bancolombia.form.qr.dto;

import static org.junit.Assert.assertEquals;

import java.util.Base64;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.util.Utilities;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EncodedPdf.class)
public class EncodedPdfTest {
	
	private final String bigText = "3zN8cFXE6TCacxAcjzV5EMcXnWVnI4N0yyda2cPC8pNF3sqNYMC9jejJmRB3oY9pir1O0qEpjdGa4r5BG20KX7L7AUWxZgRFWODkzLqkEaiSsFbvwYkSaoaXe6FtITFPV9i1dBOPyHPyyQ9gC83VFc20HQNDlZ1o9tPddnysAY2D8h0yd33d9tak7fcRsMcGnfm52u4RetLw1MCAhur1628wOJTnLs7yzipCiAPSEw7doqQLAeAQSBtd03dsdt98uyTXEfgB8NXhwd8SpvNFjSGrbBDMcGjKhraV8KMmMFW2S0uffQGTAuKosED0UFZS6sOuC2XHObD4TsCvc8s90rPKbMv6ohbRNole7OEcJmpQS8vhdNgy7D68JNNcTWTTxbScIi1E4va7KsgLa9bj8wa7Ocr8DjUOp1y0FAYovrKz0KdI6ylwVAhSsW2xegn1iyrLOrF4QTJVFlUtvRRKchXggEIpjoTmzCcbU8VdmZzskTkJNocTwNq6sa6TOnl15yT53Zz1upEh3ZmZTpq22l76UvkO7GoKxiYRY03Sq7c7Toxa0SZov6MEQDVXyY2W2lNwAFM57EXjZ9IKGTaIE05gayEaFkJ4qCV8cmHqcfB30xy0btqzuc1mrGXmqhN7nv1rWCOPYOpKDNFebRuNsj2AXkAMm83j3eknBLoJDuYrnDELnN3o2Rj9l8VQjSkjeZMUJ6iRZlCLqe1ft4LN8Orw4yvwEl8WnmMXjSGZRXrxKliOQx5cqklOGXPhHvnj7ipvt4f6p8yxulogOGMypxi2myz0enEdwBgpBaqIGYZ5PRjgRWtf3L9CpFTCCCOfzEn7JAW7pPgwq4QKnNwCr8QjO1cq0gCCKMuvhwqUnUmKpK1N7mjOkvZiYVngHbN03CYTWJjdMdU37lzGRLooiebtyi01ytpvAxGw2EKdsq9IINvhKusvGtUvFIt6eILeN7RApn41Jm3KOHhNPpWuRImQdHA1rra5cuMPCvop";
	private final String shortText = "3zN8cFXE6TCa";
	
	private EncodedPdf actual;
	
	@Before
	public void setUp() {
		actual = new EncodedPdf();
	}

	@Test
	public void toStringBigOkTest() {
		actual.setBase64(Base64.getEncoder().encode(bigText.getBytes()).toString());
		actual.setTemplate(bigText.getBytes());
		assertEquals(String.format("EncodedPdf [base64=%s, template=%s]", ((actual.getBase64() == null || actual.getBase64().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY), ((actual.getTemplate() == null || actual.getTemplate().length == 0) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY)), actual.toString());
	}
	
	@Test
	public void toStringShortOkTest() {
		actual.setBase64(Base64.getEncoder().encode(shortText.getBytes()).toString());
		actual.setTemplate(shortText.getBytes());
		assertEquals(String.format("EncodedPdf [base64=%s, template=%s]", ((actual.getBase64() == null || actual.getBase64().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY), ((actual.getTemplate() == null || actual.getTemplate().length == 0) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY)), actual.toString());
	}
	
	@Test
	public void toStringNullOkTest() {
		actual.setBase64(null);
		actual.setTemplate(null);
		assertEquals(String.format("EncodedPdf [base64=%s, template=%s]", ((actual.getBase64() == null || actual.getBase64().isEmpty()) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY), ((actual.getTemplate() == null || actual.getTemplate().length == 0) ? Constant.MESSAGE_ERROR_FILE_NOT_GENERATED_OR_ERROR : Constant.MESSAGE_FILE_GENERATED_SUCCESFULLY)), actual.toString());
	}

}
