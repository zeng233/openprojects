package com.hibernate.model.inheritance.joinsub;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * description: TODO
 *
 * createtime: 2012-11-21 下午03:42:28
 *
 * @author zenghua
 * @version 1.0
 */

@Entity
@DiscriminatorValue("BoatBig")
public class BoatBig extends Boat {
	
	@Column(name="big_wing")
	private String wing;

//	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	@JoinColumn(name="boat_id",insertable=false, updatable=false)
//	private Boat boat;
	
	public String getWing() {
		return wing;
	}

	public void setWing(String wing) {
		this.wing = wing;
	}

//	public Boat getBoat() {
//		return boat;
//	}
//
//	public void setBoat(Boat boat) {
//		this.boat = boat;
//	}
}
