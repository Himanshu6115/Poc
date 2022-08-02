package com.jaxRs.PocProject.Repo;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.jaxRs.PocProject.Domain.UserDomain;
import com.jaxRs.PocProject.Exception.UserAlreadyExist;


public class UserDatabase {
	

	public void createEmpl(UserDomain userDomain) {
		try{
			 Configuration con = new Configuration().configure().addAnnotatedClass(UserDomain.class);    
             ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
             SessionFactory sf = con.buildSessionFactory(reg);
             System.out.println(sf);
             Session session = sf.openSession();
             Transaction trans = session.beginTransaction();
             session.save(userDomain);
             trans.commit();
		} catch (Exception e) {
			throw new UserAlreadyExist("Already Present");
		}
	}
	
//public UserDomain getEmployeeData(String username) {
//		
//		try {
//			 UserDomain employeeDomain;
//			 Configuration con = new Configuration().configure();
//	         ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
//	         SessionFactory sf = con.buildSessionFactory(reg);
//	         System.out.println(sf);
//	         Session session = sf.openSession();
//	          employeeDomain = (UserDomain)session.get(UserDomain.class, id);
//	          System.out.println("user is "+ employeeDomain.getUserName());
//	        //  System.out.println(employeeDomain.getEmplName());
//	          return employeeDomain;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public UserDomain validate(String userName, String password){
		Transaction trans=null;
		try {
			 Configuration con = new Configuration().configure().addAnnotatedClass(UserDomain.class);    
             ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
             SessionFactory sf = con.buildSessionFactory(reg);
             System.out.println(sf);
             Session session = sf.openSession();
             trans  = session.beginTransaction();
             UserDomain userDomain = (UserDomain) session.createQuery("FROM UserDomain user WHERE user.userName=:userName")
            		 .setParameter("userName", userName).uniqueResult();
             if (userDomain!=null && userDomain.getPassword().equals(password)) {
				return userDomain;
			}
         
//             else if (userDomain!=null && userDomain.getPassword().equals(password) && userDomain.getRole().equals("Producer")) {
// 				return true;
// 			}
             trans.commit();
		} catch (Exception e) {
			if (trans!=null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	public UserDomain validateAdmin(UserDomain userDomain1) {
		Transaction trans=null;
		try {
			 Configuration con = new Configuration().configure().addAnnotatedClass(UserDomain.class);    
             ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
             SessionFactory sf = con.buildSessionFactory(reg);
             System.out.println(sf);
             Session session = sf.openSession();
             System.out.println("Name isssc "+userDomain1.getUserName() );
             trans  = session.beginTransaction();
             UserDomain userDomain = (UserDomain) session.createQuery("FROM UserDomain user WHERE user.userName=:userName")
            		 .setParameter("userName", userDomain1.getUserName()).uniqueResult();
             
             if (userDomain!=null && userDomain.getPassword().equals(userDomain1.getPassword())) {
				return userDomain;
			}
         
//             else if (userDomain!=null && userDomain.getPassword().equals(password) && userDomain.getRole().equals("Producer")) {
// 				return true;
// 			}
             trans.commit();
		} catch (Exception e) {
			if (trans!=null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

}
