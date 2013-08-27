package com.hibernate.test;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.hibernate.model.m2m.User;
import com.hibernate.util.HibernateUtil;

/**
 * createtime：2012-5-10 下午02:18:06
 *
 * @author zenghua
 *
 */

public class Test {
	static Session session = null;
	static Transaction transaction = null;

	/**
	 * 在整个类执行前初始化
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("this is before class");
		session = HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * 类最后执行
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("this is after class");
	}

	/**
	 * 调用之前都会执行
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("this is before");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("this is after");
		session.close();
	}
	
	//------------------------------------测试方法---------------------------------------
	@org.junit.Test
	public void testQueryTime() {
		transaction = session.beginTransaction();
		Query query = session.createQuery("from User");
		List<User> list = query.list();
		
	}
}
