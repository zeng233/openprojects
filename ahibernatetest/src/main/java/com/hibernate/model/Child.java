package com.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * createtime：2012-5-8 下午02:40:08
 * 
 * @author zenghua
 * 
 */
@Entity
@Table(name="_child")
public class Child {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="c_name")
	private String name;
	@ManyToOne
	@JoinTable(name = "parent_child", joinColumns = @JoinColumn(name = "cId",referencedColumnName="id"), 
			inverseJoinColumns = @JoinColumn(name = "pId", referencedColumnName="p_id"))
	private Parent parent;
	
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

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}
}
