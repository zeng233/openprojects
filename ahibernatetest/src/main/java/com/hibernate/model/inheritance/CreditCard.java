package com.hibernate.model.inheritance;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * description: 信用卡
 *
 * createtime: 2012-11-21 下午02:35:37
 *
 * @author zenghua
 * @version 1.0
 */

@Entity
@AttributeOverride(name="owner" /* 父类属性名称 */,column=@Column(name="cc_owner", length=20, nullable=false))//重写父类的属性名称
/*@AssociationOverride(name="address",joincolumns=@JoinColumn(name="ADDR_ID"))*/ //重命名关联列列名
public class CreditCard extends BillingDetail {
	@Id
	@GeneratedValue
	@Column(name="credit_card_id")
	private int id;
	/**
	 * 卡号
	 */
	private String num;
	
	/**
	 * 开户日期
	 */
	private Date expTime;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}
}
