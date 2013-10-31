import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Phone;
import com.hibernate.model.Student;
import com.hibernate.util.HibernateUtil;

/**
 * createtime：2012-5-7 上午11:33:14
 *
 * @author zenghua
 *
 */

public class Test {
	public static void main(String[] args) { 

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
//			Phone p1 = new Phone();
//			p1.setPhoneNum("p1");
//			Phone p2 = new Phone();
//			p2.setPhoneNum("p2");
//			Set<Phone> phones = new HashSet<Phone>();
//			phones.add(p1);
//			phones.add(p2);
//
//			Student s = new Student();
//			s.setName("s");
//			s.setPhones(phones);
//			session.save(s);
			
//			Student s = new Student();
//			s.setId(1);
			
			//查看API中delete方法的解释，只能删除持久化状态的Object
//			Student ss = (Student) session.get(Student.class, 1);
//			session.delete(ss);
			
//			Student s = new Student();
//			Student ss = (Student) session.get(Student.class, 2);
//			ss.setId(222);
//			ss.setName("bb");
//			transaction.commit();
//			transaction = session.beginTransaction();
//			session.update(ss);
			
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	
	}
}
