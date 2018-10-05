package com.ferreirarubens.repo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ferreirarubens.model.Metadata;

public interface IMetadataRepository extends JpaRepository<Metadata, Serializable> {

	Metadata save(Metadata metadata);

	Metadata findByBucketAndName(String bucket, String name);

	@Query("select s from Metadata s join s.meta m where ?1 in (KEY(m))")
	List<Metadata> findByMetaKey(String meta);
	
	List<Metadata> findByBucketAndMeta(String bucket, Map<String, String> map);
}
