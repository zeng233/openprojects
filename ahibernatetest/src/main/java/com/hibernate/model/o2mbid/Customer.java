package com.hibernate.model.o2mbid;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

/**
 * createtime：2012-5-7 下午02:42:29
 * 
 * @author zenghua
 * 
 */

@Entity
//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)//配置对象缓存
//@Proxy(lazy=false)//在load时用，
@Table(name="bid_one_customer")
public class Customer {
	@Id
	@GeneratedValue
	private int customerId;
	
//	@Basic(fetch=FetchType.LAZY)//默认为EAGER
	private String name;
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="customer", fetch=FetchType.LAZY)
	@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@LazyCollection(LazyCollectionOption.EXTRA)//查询的时候用，集合不用初始化13.1.3
	private Set<Order> orders = new HashSet<Order>();
	
	//无参数的构造函数
	//在get，load方法获取对象时，没有无参构造函数就抛异常
	public Customer() {
		
	}
	
	public Customer(String name) {
//		customerId = id;
		this.name = name;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}
