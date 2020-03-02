package com.gengjiaxin.redis.domain;

import java.io.Serializable;

public class User implements Serializable{

	private Integer id;
	
	private String name;
	
	private String gender;
	
	private String telephone;
	
	private String email;
	
	private String birthday;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public User(Integer id, String name, String gender, String telephone, String email, String birthday) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.birthday = birthday;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", telephone=" + telephone + ", email="
				+ email + ", birthday=" + birthday + "]";
	}
	
	
}
