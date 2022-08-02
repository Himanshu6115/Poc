package com.jaxRs.PocProject.Repo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.jaxRs.PocProject.Domain.ItemDomain;
import com.jaxRs.PocProject.Exception.ItemAlreadyExist;
import com.jaxRs.PocProject.Exception.UserAlreadyExist;

public class ItemDatabase {
	
	public void createEmpl(ItemDomain itemDomain) {
		try{
			 Configuration con = new Configuration().configure().addAnnotatedClass(ItemDomain.class);    
             ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
             SessionFactory sf = con.buildSessionFactory(reg);
             System.out.println(sf);
             Session session = sf.openSession();
             Transaction trans = session.beginTransaction();
             session.save(itemDomain);
             trans.commit();
		} catch (Exception e) {
			throw new UserAlreadyExist("Already Present");
		}
	}

	public void updateItems(String itemId, ItemDomain item) {
		try {
		 Configuration con = new Configuration().configure().addAnnotatedClass(ItemDomain.class);    
         ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
         SessionFactory sf = con.buildSessionFactory(reg);
         System.out.println(sf);
         Session session = sf.openSession();
         Transaction trans = session.beginTransaction();         
         ItemDomain itemDomain = (ItemDomain) session.get(ItemDomain.class, itemId);
         System.out.println("Item Domian is "+itemDomain.getItemId());
		 itemDomain.setItemId(item.getItemId());
		 itemDomain.setName(item.getName());
		 itemDomain.setDescription(item.getDescription());
		 itemDomain.setPrice(item.getPrice());
		 session.update(itemDomain);
		 trans.commit();
		// return itemDomain;
		
		}
		catch (Exception e) {
			throw new ItemAlreadyExist("Already Present");
		}
	}

	public ItemDomain getItemDetailsById(String itemId) {
		try {
			 Configuration con = new Configuration().configure().addAnnotatedClass(ItemDomain.class);    
	         ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
	         SessionFactory sf = con.buildSessionFactory(reg);
	         System.out.println(sf);
	         Session session = sf.openSession();
	         ItemDomain itemDomain = (ItemDomain)session.get(ItemDomain.class, itemId);
	         return itemDomain;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ItemDomain deleteItems(String itemId) {
		
		try {
			 ItemDomain itemDomain;
			 Configuration con = new Configuration().configure();
	         ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
	         SessionFactory sf = con.buildSessionFactory(reg);
	         System.out.println(sf);
	         Session session = sf.openSession();
	         Transaction trans = session.beginTransaction();
	         itemDomain = (ItemDomain)session.get(ItemDomain.class, itemId);
	          session.delete(itemDomain);
	          session.getTransaction().commit();
	        //  System.out.println(employeeDomain.getEmplName());
	          return itemDomain;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
