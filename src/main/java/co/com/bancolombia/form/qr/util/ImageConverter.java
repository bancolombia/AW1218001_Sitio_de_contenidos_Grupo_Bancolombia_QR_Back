package co.com.bancolombia.form.qr.util;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;


@Component
public class ImageConverter {
	
    public byte[] svg2Png(String imageQr) throws TranscoderException, IOException {
        
    	FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream();
        TranscoderOutput outputPngImage = new TranscoderOutput(outputStream);
        
        PNGTranscoder converter = new PNGTranscoder();
        
        converter.transcode(new TranscoderInput(new InputStreamReader(new ByteArrayInputStream(Base64.getDecoder().decode(imageQr)))), outputPngImage);
        
        outputStream.flush();
        outputStream.close();
        
        return outputStream.toByteArray();
    }

}
