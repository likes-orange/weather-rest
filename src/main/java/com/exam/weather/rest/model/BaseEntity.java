package com.exam.weather.rest.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -9040595613460432058L;

	public BaseEntity() {
		this.id = UUID.randomUUID().toString();
	}

	@Id
	@Column(name = "ID", unique = true)
	protected String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
