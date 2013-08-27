

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.m2m.User;
import com.hibernate.util.HibernateUtil;

/**
 * createtime：2012-5-14 上午10:43:36
 * 
 * @author zenghua
 * 
 */

public class TestQuery {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("from User");
			query.setCacheable(true);
			List<User> users = query.list();
			
			for (int i = 0; i < users.size(); i++) {
				System.out.println(users.get(i).getUsername());
			}
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
