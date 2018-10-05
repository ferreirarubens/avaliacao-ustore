package com.ferreirarubens.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ferreirarubens.model.Metadata;
import com.ferreirarubens.repo.IMetadataRepository;

/**
 * @author Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
@Service
@Transactional
public class MetadataServiceImpl implements IMetadataService {

	@Autowired
	private IMetadataRepository metadataRepository;

	@Override
	public Metadata findByBucketAndName(String bucket, String name) {
		return metadataRepository.findByBucketAndName(bucket, name);
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public List<Metadata> findByBucketAndKeyValueMap(String bucket, String key, String value) {
		return metadataRepository.findByMetaKey(bucket, key, value);
	}
	
	@Override
	public Metadata save(Metadata metadata) {
		return metadataRepository.save(metadata);
	}

}
