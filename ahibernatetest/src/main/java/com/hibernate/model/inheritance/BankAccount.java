package com.hibernate.model.inheritance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * description: 银行账户
 *
 * createtime: 2012-11-21 下午02:38:56
 *
 * @author zenghua
 * @version 1.0
 */

@Entity
public class BankAccount extends BillingDetail {
	@Id
	@GeneratedValue
	@Column(name="bank_account_id")
	private int id;
	private String account;
	private String bankName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
}
