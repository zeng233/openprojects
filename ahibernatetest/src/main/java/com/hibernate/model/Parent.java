package com.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * createtime：2012-5-8 下午02:38:57
 * 
 * @author zenghua
 * 
 */

@Entity
@Table(name = "_parent")
public class Parent {
	@Id
	@GeneratedValue
	@Column(name = "p_id")
	private int id;
	@Column(name = "p_name")
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch=FetchType.LAZY)
	private Set<Child> childs = new HashSet<Child>();

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

	public Set<Child> getChilds() {
		return childs;
	}

	public void setChilds(Set<Child> childs) {
		this.childs = childs;
	}
}
