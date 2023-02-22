package com.fileUpload.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fileUpload.entity.imageData;
import com.fileUpload.repository.ImageDataRepository;
import com.fileUpload.util.ImageUtils;

@Service
public class ImageDataService {

	@Autowired
	private ImageDataRepository dataRepository;
	
	public String uploadImage(MultipartFile file) throws IOException {
		
		imageData image = dataRepository.save(imageData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.image(ImageUtils.compressImage(file.getBytes()))
				.build());
        if (image != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;	
	}
	
	public byte[] downloadFile(String fileName) {
		Optional<imageData> imageDb = dataRepository.findByName(fileName);
		byte[] files = ImageUtils.decompressImage(imageDb.get().getImage());
		return files;
		
	}
	
	
}
