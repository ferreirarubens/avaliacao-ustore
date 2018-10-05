package com.ferreirarubens.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ferreirarubens.exceptions.StorageException;
import com.ferreirarubens.model.Metadata;
import com.ferreirarubens.repo.IMetadataRepository;

@Service
public class StorageServiceImpl implements IStorageService {

	@Value("${storage.root}")
	private String rootLocation;

	@Autowired
	private IMetadataRepository metadataRepository;

	@Override
	public void createBucket(String bucketName) {
		if (bucketName.isEmpty()) {
			throw new StorageException("Failed to create bucket " + bucketName);
		}

		File dir = Paths.get(this.rootLocation, bucketName).toFile();

		if (!dir.exists()) {
			boolean result = false;
			try {
				dir.mkdir();
				result = true;
			} catch (SecurityException se) {

			}
			if (result) {
				System.out.println("Directory created");
			}
		} else {
			throw new StorageException("Bucket already exists: " + bucketName);
		}
	}

	@Override
	public Metadata save(String bucketName, MultipartFile file) {
		return this.save(bucketName, file, "");
	}

	@Override
	public Metadata save(String bucketName, MultipartFile file, String filename) {
		if (filename.isEmpty()) {
			filename = StringUtils.cleanPath(file.getOriginalFilename());
		}
		Metadata metadata =  metadataRepository.findByBucketAndName(bucketName, filename);;
		if (Objects.isNull(metadata)) {
			metadata = new Metadata(bucketName, filename);
			metadata.getMeta().put("name", filename);
		}
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				throw new StorageException("Cannot store file with relative path " + filename);
			}
			try (InputStream inputStream = file.getInputStream()) {
				metadata.getMeta().put("size", String.valueOf(file.getBytes().length));
				metadata.getMeta().put("upload_date", Date.from(Instant.now()).toString());
				metadata.getMeta().put("last_modify", Date.from(Instant.now()).toString());

				if (new File(rootLocation + "/" + bucketName).exists()) {
					Files.copy(inputStream, Paths.get(rootLocation, bucketName).resolve(filename),
							StandardCopyOption.REPLACE_EXISTING);
				} else {
					throw new StorageException("Bucket not exists: " + bucketName);
				}
				return metadataRepository.save(metadata);
			}
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	@Override
	public void start() {
		try {
			Files.createDirectories(Paths.get(this.rootLocation));
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public Path load(String bucket, String filename) {
		return Paths.get(rootLocation, bucket).resolve(filename);
	}

	@Override
	public Resource getResource(String bucket, String filename) {
		try {
			Path file = load(bucket, filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageException("Error to read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageException("Error read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(this.rootLocation).toFile());
	}
}
