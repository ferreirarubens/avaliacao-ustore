/**
 * 
 */
package com.ferreirarubens.services;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ferreirarubens.model.Metadata;

/**
 * @author rubens.ferreira
 *
 */
public interface IStorageService {
	
	void start();
	
	void createBucket(String bucketName);

	Metadata save(String bucketName, MultipartFile file);
	
	Metadata save(String bucketName, MultipartFile file, String filename);

	Path load(String bucket, String filename);

	Resource getResource(String bucket, String filename);

	void deleteAll();
}
