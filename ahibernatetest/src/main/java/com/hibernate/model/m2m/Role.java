package com.hibernate.model.m2m;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * description：角色表
 * 
 * createtime：2012-5-9 下午02:11:26
 * 
 * @author zenghua
 * 
 */
@Entity
@Table(name = "_role")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role {
	@Id
	@GeneratedValue
	@Column(name="r_id")
	private int id;
	private String rolename;
	@ManyToMany
	@JoinTable(name="user_role", joinColumns=@JoinColumn(name="rid", referencedColumnName="r_id"), 
			inverseJoinColumns=@JoinColumn(name="uid", referencedColumnName="u_id"))
			
	
	
	
//	@ManyToMany(cascade=CascadeType.ALL, mappedBy="roles", fetch=FetchType.LAZY)
	private List<User> users = new ArrayList<User>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
