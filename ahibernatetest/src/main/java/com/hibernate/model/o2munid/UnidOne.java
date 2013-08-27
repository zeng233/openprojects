package com.hibernate.model.o2munid;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description: TODO
 *
 * createtime: 2012-11-8 下午03:59:28
 *
 * @author zenghua
 * @version 1.0
 */

@Entity
@Table(name="u_one")
public class UnidOne implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5554053126513533237L;
	
	@Id
	@Column(name="uo_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="u_name")
	private String name;
	
	
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
	
}
