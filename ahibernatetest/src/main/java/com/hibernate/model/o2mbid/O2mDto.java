package com.hibernate.model.o2mbid;


/**
 * description: TODO
 *
 * createtime: 2012-11-9 下午02:30:21
 *
 * @author zenghua
 * @version 1.0
 */

public class O2mDto {
	private int orderid;
	private String another;
	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getAnother() {
		return another;
	}

	public void setAnother(String another) {
		this.another = another;
	}
	
}
