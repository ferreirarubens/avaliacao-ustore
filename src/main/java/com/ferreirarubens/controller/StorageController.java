package com.ferreirarubens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ferreirarubens.exceptions.StorageException;
import com.ferreirarubens.model.Metadata;
import com.ferreirarubens.services.IMetadataService;
import com.ferreirarubens.services.IStorageService;

/**
 * @author rubens.ferreira
 *
 */
@RestController
public class StorageController {

	@Autowired
	private IStorageService storageService;
	
	@Autowired
	private IMetadataService metadataService;

	@PostMapping("/bucket/{name}")
	public ResponseEntity<String> createBucket(@PathVariable String name) {
		storageService.createBucket(name);
		return new ResponseEntity<String>("Bucket created " + name, HttpStatus.OK);
	}

	@PostMapping("/{bucket}")
	public ResponseEntity<Metadata> fileUploadBucket(@RequestParam("file") MultipartFile file,
			@PathVariable String bucket) {
		return new ResponseEntity<Metadata>(storageService.save(bucket, file), HttpStatus.OK);
	}

	@PostMapping("/{bucket}/{filename}")
	public ResponseEntity<Metadata> fileUploadBucketFilename(@RequestParam("file") MultipartFile file,
			@PathVariable String bucket, @PathVariable String filename) {
		return new ResponseEntity<>(storageService.save(bucket, file, filename), HttpStatus.OK);
	}

	@GetMapping("/{bucket}/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String bucket, @PathVariable String filename) {

		Resource file = storageService.getResource(bucket, filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.header("metadata", metadataService.findByBucketAndName(bucket, filename).toString())
				.body(file);
	}
	
	@PutMapping("/{bucket}/update/{filename}")
	public ResponseEntity<Metadata> fileUpdateBucket(@RequestParam("file") MultipartFile file,
			@PathVariable String bucket, @PathVariable String filename) {
		if(filename.isEmpty()) {
			throw new StorageException("File name is required");
		}
		return new ResponseEntity<Metadata>(storageService.save(bucket, file, filename), HttpStatus.OK);
	}

}
