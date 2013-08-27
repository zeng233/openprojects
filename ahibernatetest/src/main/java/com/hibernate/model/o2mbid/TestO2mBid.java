package com.hibernate.model.o2mbid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.transform.Transformers;

import com.hibernate.util.HibernateUtil;


/**
 * 测试一对多双向关联，一个客户对应多张订单
 * 
 * createtime：2012-5-7 下午02:47:12
 *
 * @author zenghua
 *
 */

public class TestO2mBid {
	public static void main(String[] args) throws Exception {
		TestO2mBid t = new TestO2mBid();
//		t.batchSaveOrUpdate();
//		t.save();
//		t.delete();
//		t.getOrLoad();
		
//		t.sqlQuery();
		t.hqlQuery();
	}
	/**
	 * 批量保存
	 * @throws Exception
	 */
	public void batchSaveOrUpdate() throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		tx = session.getTransaction();
		tx.setTimeout(1000);//设置事务超时
		
		tx = session.beginTransaction();
		long curr = System.currentTimeMillis();
		for (int i=0; i<10; i++) {
			Customer customer = new Customer("姓名" + i);
			session.save(customer);
			if (i%20 == 0) {
				session.flush();
				session.clear();
			}
		}
		long end = System.currentTimeMillis() - curr;
		System.out.println(end);
		
		session.setFlushMode(FlushMode.COMMIT);
		tx.commit();
		session.close();
	}
	/**
	 * 级联保存
	 */
	public void save() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Customer c = new Customer();
			c.setName("c");
			
//			Customer c = (Customer) session.get(Customer.class, 1);
			
			Order o1 = new Order();
			o1.setName("o3");
			o1.setCustomer(c);
			Order o2 = new Order();
			o2.setName("o4");
			o2.setCustomer(c);
			
			Set<Order> orders = new HashSet<Order>();
			orders.add(o1);
			orders.add(o2);
			c.setOrders(orders);
			// TODO 文档13章节 事务和并发
//			session.setFlushMode(FlushMode.COMMIT);
			session.save(c);
			
			
			//级联更新,先要从数据库取出获得持久化对象，然后进行修改，再提交事务数据库完成更新操作
//			Customer cus = (Customer) session.get(Customer.class, 2);
//			cus.setName("hello");
//			
//			Order order = (Order) session.get(Order.class, 1);
//			order.setName("11111");
//			session.update(order);
//			session.update(cus);
			
			//------------级联删除-------------------------------
//			Customer cus = (Customer) session.get(Customer.class, 1);
//			session.delete(cus);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void delete() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			//单独删除多的方
			/*Order order = (Order) session.load(Order.class, 1);
			session.delete(order);*/
			
			//获得单方，进行级联删除
			Customer c = (Customer) session.get(Customer.class, 1);
			session.delete(c);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void getOrLoad() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
		
		Customer cLoad = (Customer) session.load(Customer.class, 2);
		cLoad.setName("3");//隐式的更新Customer，因为在一个PO在一个session中，只要发生更新，hibernate会自动检测，如果有改变就会自动更行
		System.out.println(cLoad);
		System.out.println(cLoad.getName());
		System.out.println(HibernateProxyHelper.getClassWithoutInitializingProxy(cLoad));
		
//		Customer cGet = (Customer) session.get(Customer.class, 12);
//		System.out.println(cGet.getName());
		tx.commit();
		session.close();
	}
	
	/**
	 * 原生sql实体查询，参考3.6文档18.1.5章节
	 */
	public void sqlQuery() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
//			Query query = session.createQuery("");
			
//			Query sqlQuery = session.createSQLQuery("select name from bid_mangy_order")
//				.addEntity(O2mDto.class);//addEntity方法中的参数必须是hibernate持续化的，也就是PO对象
			
			Query sqlQuery = session.createSQLQuery("select orderid,_name as name from bid_many_order")// TODO 原生SQL查询中的属性必须和DTO的属性是一致的（可以忽略大小写）
				.setResultTransformer(Transformers.aliasToBean(O2mDto.class));//setResultTransformer方法中的参数是不受hibernate管制（可以非持久化类）的，可以任意的对象
			List<O2mDto> list = sqlQuery.list();
			for (int i=0; i<list.size(); i++) {
				System.out.println(list.get(i).getOrderid() + " : " + list.get(i).getName());
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 查询缓存
	 */
	public void hqlQuery() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Order").setCacheable(true).setCacheRegion("mycache");//这里是查询缓存，并非对象缓存，所以和配置文件配置的不一样，参考hibernate in action 15.4.2章节
			List<Order> orders = query.list();
			
			query = session.createQuery("from Customer where customerId=1");
			Customer c = (Customer) query.uniqueResult();
			Order o = new Order();
			o.setCustomer(c);
			o.setName("saveOrUpdate");
			Set<Order> sets = new HashSet<Order>();
			sets.add(o);
			c.setOrders(sets);
			
			session.saveOrUpdate(c);
			
//			Set<Order> set = c.getOrders();
			
			transaction.commit();
			
//			for(Order o : set) {
//				System.out.println(o.getName());
//			}
			
			for (Order order : orders) {
				System.out.println(order.getOrderId());
			}
			
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/**
	 * Criteria标准查询
	 */
	public void cQuery() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
