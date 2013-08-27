package com.hibernate.model.own;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.hibernate.model.o2obid.Person;

/**
 * description: 参考hibernate2.2章节,自身表关联设计
 *
 * createtime: 2012-11-20 下午01:51:23
 *
 * @author zenghua
 * @version 1.0
 */

@Entity
@Table(name="_message")
public class Message {
	@Id 
	@GeneratedValue
	@Column(name = "_id")
	private Long id;
	
	@Column(length=50)//以前在四方 的时候，如果字符超过了50个怎么办，Java用的Unicode，一个汉字占2个字符，3个Unicode码
	private String title;
	
	@Column(name = "message_text", length=300)
	private String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "next_message_id")
	private Message nextMessage;

	@Transient
	private Person person;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Message getNextMessage() {
		return nextMessage;
	}

	public void setNextMessage(Message nextMessage) {
		this.nextMessage = nextMessage;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
