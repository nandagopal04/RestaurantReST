package com.seaside.entity;

import jakarta.persistence.Entity;

@Entity
public class Chef {
	int id;
	String name;
	public Chef() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Chef(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
		return "Chef [id=" + id + ", name=" + name + "]";
	}
}
