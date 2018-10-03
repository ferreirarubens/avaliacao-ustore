/**
 * 
 */
package com.ferreirarubens.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author rubens.ferreira
 *
 */
public interface IStorageService {
	
	void init();

	void save(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();
}
