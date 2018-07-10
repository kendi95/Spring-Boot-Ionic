package com.kohatsu.cursomc.servicies;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.kohatsu.cursomc.servicies.exceptions.FileException;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	public URI uploadFile(MultipartFile multipartFile){

		try {
			
			String fileName = multipartFile.getOriginalFilename();
			InputStream is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();

			return uploadFile(is, fileName, contentType);
			
		}catch(IOException ex) {
			
			throw new FileException("Erro de IO: "+ex.getMessage());
			
		}
		

	}

	public URI uploadFile(InputStream is, String fileName, String contentType){
		
		try {
			
			ObjectMetadata metaData = new ObjectMetadata();
			metaData.setContentType(contentType);
			
			LOG.info("Upload inicializado...");
			s3Client.putObject(bucketName, fileName, is, metaData);
			LOG.info("Upload finalizado.");
			
			return s3Client.getUrl(bucketName, fileName).toURI();
			
		}catch(URISyntaxException ex) {
			
			throw new FileException("Erro ao cnonverter URL para URI");
			
		}
		
		
	}

}
