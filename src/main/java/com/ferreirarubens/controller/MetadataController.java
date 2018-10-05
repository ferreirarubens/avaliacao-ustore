package com.ferreirarubens.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ferreirarubens.exceptions.StorageException;
import com.ferreirarubens.model.Metadata;
import com.ferreirarubens.services.IMetadataService;

/**
 * @author rubens.ferreira
 *
 */
@RestController
@RequestMapping("/metadata")
public class MetadataController {

	@Autowired
	private IMetadataService metadataService;

	@GetMapping("/{bucket}/{filename}")
	public ResponseEntity<Metadata> getByName(@PathVariable String bucket, @PathVariable String filename) {
		return ResponseEntity.ok().body(metadataService.findByBucketAndName(bucket, filename));
	}

	@PutMapping("/{bucket}/{filename}")
	public ResponseEntity<Metadata> updateMetadata(@PathVariable String bucket, @PathVariable String filename,
			@RequestHeader HttpHeaders headers) {
		Metadata metadata = metadataService.findByBucketAndName(bucket, filename);
		if (Objects.isNull(metadata)) {
			throw new StorageException("File not exists");
		}

		headers.entrySet().stream().filter(h -> h.getKey().startsWith("meta."))
				.collect(Collectors.toMap(e -> e.getKey().toString().replace("meta.", ""), e -> e.getValue()))
				.entrySet().forEach(entry -> {
					metadata.getMeta().put(entry.getKey(), entry.getValue().get(0));
				});
		return ResponseEntity.ok().body(metadata);
	}

	@PostMapping("/{bucket}/meta/{meta}")
	@ResponseBody
	public ResponseEntity<List<String>> serveFile(@PathVariable String bucket, @PathVariable String meta,
			@RequestParam("value") String value) {

		return ResponseEntity.ok().body(metadataService.findByBucketAndKeyValueMap(bucket, meta, value).stream()
				/*
				 * .filter(m -> m.getMeta().get(meta).equals(value))
				 */.map(m -> m.getBucket() + "/" + m.getName()).collect(Collectors.toList()));
	}

}
