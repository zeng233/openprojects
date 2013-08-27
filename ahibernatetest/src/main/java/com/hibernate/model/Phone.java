package com.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * createtime：2012-5-7 上午11:31:51
 * 
 * @author zenghua
 * 
 */

@Entity
@Table(name = "phone")
public class Phone {
	@Id
	@GeneratedValue
	private int phoneId;
	private String phoneNum;
	
	public int getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
