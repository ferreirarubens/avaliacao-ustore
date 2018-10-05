package com.ferreirarubens.repo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ferreirarubens.model.Metadata;

/**
 * @author Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
public interface IMetadataRepository extends JpaRepository<Metadata, Serializable> {

	Metadata save(Metadata metadata);

	Metadata findByBucketAndName(String bucket, String name);

	@Query("select s from Metadata s join s.meta m where s.bucket = :bucket and :name in (KEY(m)) and :value in (VALUE(m))")
	List<Metadata> findByMetaKey(@Param("bucket") String bucke, @Param("name") String name, @Param("value") String value);
	
	List<Metadata> findByBucketAndMeta(String bucket, Map<String, String> map);
}
