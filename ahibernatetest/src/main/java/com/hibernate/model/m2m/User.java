package com.hibernate.model.m2m;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * createtime：2012-5-9 下午02:11:16
 * 
 * @author zenghua
 * 
 */
@Entity
@Table(name = "_user")
public class User {
	@Id
	@GeneratedValue
	@Column(name="u_id")
	private int id;
	@Basic(fetch=FetchType.LAZY)
	private String username;
//	主表为role，从表为user，由user维持关联关系
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="users", fetch=FetchType.LAZY)
	
	
	
	
//	@ManyToMany
//	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="uid", referencedColumnName="u_id"), 
//			inverseJoinColumns=@JoinColumn(name="rid", referencedColumnName="r_id"))
	private List<Role> roles = new ArrayList<Role>();
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
