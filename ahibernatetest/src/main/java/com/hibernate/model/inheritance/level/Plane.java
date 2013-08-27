package com.hibernate.model.inheritance.level;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * description: 层次结构的继承，使用同一张表（单表继承）
 *
 * createtime: 2012-11-21 下午04:04:09
 *
 * @author zenghua
 * @version 1.0
 */

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//如果没有该注解，SINGLE_TABLE为默认类型
@DiscriminatorColumn(name="planeType", discriminatorType=DiscriminatorType.STRING)//用于区别父类和其他子类的列名
@DiscriminatorValue("Plane")
public class Plane {
	@Id
	@GeneratedValue
	@Column(name="plane_id")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
