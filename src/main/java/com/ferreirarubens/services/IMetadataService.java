package com.ferreirarubens.services;

import java.util.List;

import com.ferreirarubens.model.Metadata;

/**
 * @author Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
public interface IMetadataService {
	Metadata findByBucketAndName(String bucket, String name);
	
	Metadata save(Metadata metadata);
	
	List<Metadata> findByBucketAndKeyValueMap(String bucket, String key, String value);
}
