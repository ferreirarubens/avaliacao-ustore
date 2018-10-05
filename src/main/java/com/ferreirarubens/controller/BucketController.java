/**
 * 
 */
package com.ferreirarubens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferreirarubens.services.IStorageService;

/**
 * @author Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
@RestController
@RequestMapping("/bucket")
public class BucketController {
	
	@Autowired
	private IStorageService storageService;
	
	@PostMapping("/{name}")
	public ResponseEntity<String> createBucket(@PathVariable String name) {
		storageService.createBucket(name);
		return new ResponseEntity<String>("Bucket created " + name, HttpStatus.OK);
	}
}
