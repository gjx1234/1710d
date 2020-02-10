package com.gengjiaxin.practice.domain;

public class Plan {

	private Integer id;
	
	private String name;
	
	private Double amount;
	
	private String manager;
	
	private String content;
	
	private Department department;
	
	private Integer dept_id;
	

	public Plan(Integer id, String name, Double amount, String manager, String content, Department department,
			Integer dept_id) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.manager = manager;
		this.content = content;
		this.department = department;
		this.dept_id = dept_id;
	}

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Plan() {
		super();
	}
	
	
}
