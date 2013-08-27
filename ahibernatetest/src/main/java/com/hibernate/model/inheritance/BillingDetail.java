package com.hibernate.model.inheritance;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 * description: 账单发票明细，具体表映射，此为隐式多态
 *
 * createtime: 2012-11-21 下午02:34:08
 *
 * @author zenghua
 * @version 1.0
 */

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class BillingDetail {
	/**
	 * 持有者
	 */
	@Column(name="owner", nullable=false)
	private String owner;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
