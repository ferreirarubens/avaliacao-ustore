package com.ferreirarubens.services;

import java.util.List;

import com.ferreirarubens.model.Metadata;

/**
 * @author rubens.ferreira
 *
 */
public interface IMetadataService {
	Metadata findByBucketAndName(String bucket, String name);
	
	List<Metadata> findByBucketAndKeyValueMap(String bucket, String key, String value);
}
