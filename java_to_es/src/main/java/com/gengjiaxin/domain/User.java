package com.gengjiaxin.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@SuppressWarnings("serial")
@Document(indexName = "test_user", type = "user")
public class User implements Serializable {

	@Id
	private Integer id;

	@Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart",type = FieldType.text)
	private String name;
	
	@Field(index = true, store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart",type = FieldType.text)
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + "]";
	}


}
