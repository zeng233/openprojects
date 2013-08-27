package com.hibernate.model.o2munid;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * description: TODO
 *
 * createtime: 2012-11-8 下午03:59:50
 *
 * @author zenghua
 * @version 1.0
 */

@Entity
@Table(name="u_many")
public class UnidMany implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6296916259886459221L;
	@Id
	@Column(name = "um_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name = "u_name")
	private String name;
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)//延时加载，只有取出对象时候才去查
	@JoinColumn(name="u_one")//默认外键名称为uone+另一个实体的主键
	private UnidOne uone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UnidOne getUone() {
		return uone;
	}

	public void setUone(UnidOne uone) {
		this.uone = uone;
	}
	
	
}
