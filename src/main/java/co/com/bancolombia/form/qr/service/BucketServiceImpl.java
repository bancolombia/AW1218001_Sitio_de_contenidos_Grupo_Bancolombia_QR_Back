package co.com.bancolombia.form.qr.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;

import co.com.bancolombia.form.qr.Constant;
import co.com.bancolombia.form.qr.config.BucketSettings;
import co.com.bancolombia.form.qr.dto.EncodedPdf;


@Service
public class BucketServiceImpl implements IBucketService {

	private static Logger logger = LoggerFactory.getLogger(BucketServiceImpl.class);

	@Autowired
	private AmazonS3 s3Client;

	@Autowired
	private BucketSettings bucketSettings;

	@Override
	public EncodedPdf getTemplate(String key) throws IOException {
		EncodedPdf response = new EncodedPdf();
		if (bucketSettings != null && bucketSettings.getBucketName() != null && key != null) {
			logger.info(Constant.APIQR_S3_LOG_KEY_VALUE, bucketSettings.getBucketName(), key);
			S3Object object = s3Client.getObject(new GetObjectRequest(bucketSettings.getBucketName(), key));
			response.setTemplate(IOUtils.toByteArray(object.getObjectContent()));
			return response;
		} else {
			throw new IOException("The configurations values are empty.");
		}
		 
	}

}
