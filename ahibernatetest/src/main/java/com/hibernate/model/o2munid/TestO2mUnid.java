package com.hibernate.model.o2munid;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.util.HibernateUtil;


/**
 * description: 测试一对多的单向关系,设置实体时不能将id设置为string，设置ID生成策略
 *
 * createtime: 2012-11-8 下午03:51:32
 *
 * @author zenghua
 * @version 1.0
 */

public class TestO2mUnid {
	public static void main(String[] args) throws Exception {
		TestO2mUnid t = new TestO2mUnid();
		t.save();
	}
	
	public void save() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
		     tx = session.beginTransaction();
		     UnidOne one = new  UnidOne();
		     one.setName("aaaaa");
//		     session.save(one);
		     
		     UnidMany many = new UnidMany();
		     many.setName("many");
		     many.setUone(one);
		     session.save(many);
		     tx.commit();
		 } catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e; //throw必须用于由throws抛出异常的方法
		 }
		 finally {
			 session.close();
		 }
	}
	
	public void query() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
		     tx = session.beginTransaction();
		     Query query = session.createQuery("from UnidOne");
		     tx.commit();
		 } catch (Exception e) {
		     if (tx!=null) tx.rollback();
		 }
		 finally {
			 session.close();
		 }
	}
}
