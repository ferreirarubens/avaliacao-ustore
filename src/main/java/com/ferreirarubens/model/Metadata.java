package com.ferreirarubens.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author rubens.ferreira
 *
 */
@Entity
@Table(name = "metadata", schema = "ustore", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "bucket" })})
public class Metadata {

	@Id
	private String name;
	
	@Column
	private String bucket;

	@ElementCollection
	@MapKeyColumn(name="name")
	private Map<String, String> meta = new HashMap<>();

	public Metadata() {
		// TODO Auto-generated constructor stub
	}
	
	public Metadata(String bucket, String name, Map<String, String> meta) {
		super();
		this.bucket = bucket;
		this.name = name;
		this.meta = meta;
	}
	
	public Metadata(String bucket, String name) {
		super();
		this.bucket = bucket;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	@Override
	public String toString() {
		return getClass().getName() + " {\n\tname: " + name + "\n\tbucket: " + bucket + "\n\tmeta: " + meta + "\n}";
	}

}
