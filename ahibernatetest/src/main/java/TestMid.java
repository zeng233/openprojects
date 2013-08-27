import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Child;
import com.hibernate.model.Parent;
import com.hibernate.util.HibernateUtil;

/**
 * createtime：2012-5-8 下午02:42:18
 * 
 * @author zenghua
 * 
 */

public class TestMid {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Parent p = new Parent();
			p.setName("p");
			
			Child c1 = new Child();
			c1.setName("c1");
			c1.setParent(p);
			Child c2 = new Child();
			c2.setName("c2");
			c2.setParent(p);
			
			Set<Child> childs = new HashSet<Child>();
			childs.add(c1);
			childs.add(c2);
			p.setChilds(childs);
			
			session.save(p);
			
//			Query query = session.createQuery("from Parent");
//			List<Parent> parents = query.list();
//			for (Parent p : parents) {
//				System.out.println(p.getId());
//			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
