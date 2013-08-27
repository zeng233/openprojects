import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.m2m.Role;
import com.hibernate.model.m2m.User;
import com.hibernate.util.HibernateUtil;

/**
 * createtime：2012-5-9 下午02:13:32
 * 
 * @author zenghua
 * 
 */

public class TestM2M {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
//			User u1 = new User();
//			u1.setId(9);
//			u1.setUsername("u1");
//			User u2 = new User();
//			u1.setId(10);
//			u2.setUsername("u2");
//			User u3 = new User();
//			u3.setUsername("u3");
			
			List<User> users = new ArrayList<User>();
			for (int i = 0; i < 10000; i++) {
				User u = new User();
				u.setUsername("u--------" + i);
				users.add(u);
			}
//			users.add(u1);
//			users.add(u2);
			
			Role r1 = new Role();
			r1.setRolename("r1");
			r1.setId(11);
			r1.setUsers(users);
			Role r2 = new Role();
			r1.setId(12);
			r2.setRolename("r2");
			r2.setUsers(users);
			
			List<Role> roles = new ArrayList<Role>();
			roles.add(r1);
			roles.add(r2);
			
			for (int i = 0; i < users.size(); i++) {
				users.get(i).setRoles(roles);
			}
			
			
			
			//-----------------------------操作对象-----------------------------------
			
			//由role维持关联关系User进行级联保存
			long before = System.currentTimeMillis();
//			System.out.println("插入之前的时间："+before);
//			for (User u : users) {
//				session.save(u);
//			}
//			System.out.println("插入用时：" + (System.currentTimeMillis()-before));
			
			//批量插入
//			for ( int i=0; i<users.size(); i++ ) {
//			    session.save(users.get(i));
//			    if ( i % 200 == 0 ) { //20, same as the JDBC batch size
//			        //flush a batch of inserts and release memory:
////			        session.flush();
//			        session.clear();
//			    }
//			}
//			System.out.println("批量插入用时：：" + (System.currentTimeMillis()-before));
			
//			for (Role r : roles) {
//				session.save(r);
//			}
			
			
//			User u1 = (User) session.load(User.class, 5);
//			session.delete(u1);
			
//			Role r1 = (Role) session.load(Role.class, 9);
//			session.delete(r1);
			
			
			//---------------------------查询-----------------------------
//			Query query = session.createQuery("from User");
//			List<User> list = query.list();
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println("username: " + list.get(i).getUsername() + "----rolename: " + list.get(i).getRoles().get(0).getRolename());
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
