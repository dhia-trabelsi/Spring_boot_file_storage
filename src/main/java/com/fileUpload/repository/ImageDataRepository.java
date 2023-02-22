package com.fileUpload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileUpload.entity.imageData;

public interface ImageDataRepository extends JpaRepository<imageData, Integer> {
	
	Optional<imageData> findByName(String fileName);

}
