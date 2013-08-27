package com.hibernate.model.inheritance.joinsub;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * description: TODO
 *
 * createtime: 2012-11-21 下午03:42:40
 *
 * @author zenghua
 * @version 1.0
 */

@Entity
@PrimaryKeyJoinColumn(name="boat_fk")//如果没有指定外键名称，默认就是父类的主键名称
@DiscriminatorValue("BoatSmall")
public class BoatSmall extends Boat {
	
	@Column(name="small_foot")
	private String foot;

	public String getFoot() {
		return foot;
	}

	public void setFoot(String foot) {
		this.foot = foot;
	}
}
