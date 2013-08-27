package com.hibernate.model.inheritance.joinsub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * description: 父类,子类链接共享主键
 *
 * createtime: 2012-11-21 下午03:39:58
 *
 * @author zenghua
 * @version 1.0
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Boat {
	@Id
	@GeneratedValue
	@Column(name = "boat_id")
	private int id;
	@Column(name = "boat_name")
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
