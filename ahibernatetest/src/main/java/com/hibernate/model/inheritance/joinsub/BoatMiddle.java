package com.hibernate.model.inheritance.joinsub;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * description: TODO
 *
 * createtime: 2012-11-22 下午01:59:49
 *
 * @author zenghua
 * @version 1.0
 */

@Entity
@DiscriminatorValue("BoatMiddle")
public class BoatMiddle extends Boat {
	
	private String middle;

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}
}
