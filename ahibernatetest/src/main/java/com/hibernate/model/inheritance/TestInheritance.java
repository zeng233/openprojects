package com.hibernate.model.inheritance;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.inheritance.joinsub.BoatBig;
import com.hibernate.util.HibernateUtil;

/**
 * description: 
 *
 * createtime: 2012-11-22 下午01:33:32
 *
 * @author zenghua
 * @version 1.0
 */

public class TestInheritance {
	public static void main(String[] args) {
		TestInheritance t = new TestInheritance();
		t.save();
	}
	
	public void save() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			// TODO 文档13章节 事务和并发
			session.setFlushMode(FlushMode.COMMIT);
			
			BillingDetail bd = new BankAccount();
			bd.setOwner("BankAccount");
			session.save(bd);
			
			
//			Boat b = new Boat();
			BoatBig bb = new BoatBig();
			bb.setName("dd");
			bb.setWing("mywing");
//			bb.setBoat(b);（如果配置了关联关系表示自身关联）
			session.save(bb);//因为是继承关系，只需保存一方就可以级联保存了
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	
	}
}
