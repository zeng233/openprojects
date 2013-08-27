package com.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * createtime：2012-5-7 上午11:28:32
 * 
 * @author zenghua
 * 
 */

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "s_id")
	private Set<Phone> phones = new HashSet<Phone>();

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

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

}
