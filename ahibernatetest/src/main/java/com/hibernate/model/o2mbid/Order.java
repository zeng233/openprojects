package com.hibernate.model.o2mbid;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * createtime：2012-5-7 下午02:43:21
 * 
 * @author zenghua
 * 
 */

@Entity
//@Cache(region="a", usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)//配置对象缓存
@Table(name="bid_many_order")
public class Order {
	@Id
	@GeneratedValue
	private int orderId;
	@Column(name="_name")
	private String name;
	@ManyToOne(fetch=FetchType.LAZY)
	//insertable,updatable两个属性设置为false参见hibernate in action中Optional one-to-many association with a join table上方
	//有说明：Setting insert and update to false has the desired effect. As we discussed earlier,
	//these two attributes used together make a property effectively read-only表示状态为只读
	//如果不用joincolumn注解，默认的外键名为对象属性customer+另一端的主键（customerId）
	
	@BatchSize(size=10)//用于N+1问题
	@JoinColumn(name="customer_id")//insertable不能设置为false，不然Order这张表里面外键为空
	private Customer customer;
	
//	@Temporal(TemporalType.TIME)//设计时间精度的，看JDBC的时间类型有3种
	private Date createTime;
	
	//配置乐观锁
//	@Version
//	@Column(name="OPTLOCK")//乐观锁
//	private int versionNum;
	
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
