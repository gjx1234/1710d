package com.gengjiaxin.redis.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable{

	private Integer id;
	
	private String name;
	
	private BigDecimal price;
	
	private Double recent;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Double getRecent() {
		return recent;
	}

	public void setRecent(Double recent) {
		this.recent = recent;
	}

	public Goods(Integer id, String name, BigDecimal price, Double recent) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.recent = recent;
	}

	public Goods() {
		super();
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", recent=" + recent + "]";
	}
	
	
}
