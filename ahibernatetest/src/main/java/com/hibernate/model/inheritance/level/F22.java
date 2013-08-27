package com.hibernate.model.inheritance.level;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * description: TODO
 *
 * createtime: 2012-11-21 下午04:06:59
 *
 * @author zenghua
 * @version 1.0
 */
@Entity
@DiscriminatorValue("F22")
public class F22 extends Plane {
	
	@Column(name="f22_bomb")
	private String bomb;

	public String getBomb() {
		return bomb;
	}

	public void setBomb(String bomb) {
		this.bomb = bomb;
	}
}
