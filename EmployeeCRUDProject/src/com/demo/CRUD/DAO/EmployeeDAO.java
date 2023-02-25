package com.demo.CRUD.DAO;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.demo.CRUD.DTO.EmployeeDTO;



public class EmployeeDAO 
{ 
	public void save(EmployeeDTO dto) 
	{
		Configuration cfg=new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(EmployeeDTO.class);
		SessionFactory fact=cfg.buildSessionFactory();
		Session session=fact.openSession();
		Transaction tx=session.beginTransaction();
		try
		{
		session.save(dto);
		tx.commit();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
	
	public void selectAll(long empno) 
	{
		Configuration cfg=new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(EmployeeDTO.class);
		SessionFactory fact=cfg.buildSessionFactory();
		Session session=fact.openSession();
		Transaction tx=session.beginTransaction();
		try
		{
		EmployeeDTO fromdb =session.get(EmployeeDTO.class, empno);
		System.out.println(fromdb);
		//tx.commit();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
	
	public void update(long empno, String ename) 
	{
		Configuration cfg=new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(EmployeeDTO.class);
		SessionFactory fact=cfg.buildSessionFactory();
		Session session=fact.openSession();
		Transaction tx=session.beginTransaction();
		try
		{
		EmployeeDTO fromdb =session.get(EmployeeDTO.class, empno);
		System.out.println(fromdb);
		if(fromdb!=null)
		{
			fromdb.setEname(ename);
			session.update(fromdb);
			tx.commit();
		}
		
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
	
	public void delete(long empno) 
	{
		Configuration cfg=new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(EmployeeDTO.class);
		SessionFactory fact=cfg.buildSessionFactory();
		Session session=fact.openSession();
		Transaction tx=session.beginTransaction();
		try
		{
		EmployeeDTO fromdb =session.get(EmployeeDTO.class, empno);
		
			session.delete(fromdb);
			tx.commit();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
	
	public void deleteByDeptno()
	{
		Configuration cfg=new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(EmployeeDTO.class);
		SessionFactory fact=cfg.buildSessionFactory();
		Session session=fact.openSession();
		Transaction tx=session.beginTransaction();
		try
		{
			Query qry =session.createQuery("delete from EmployeeDTO where deptno=:i ");
			qry.setParameter("i", 30);
			int status=qry.executeUpdate();
			
				System.out.println(status);
				tx.commit();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
	
	public void updateSal()
	{
		Configuration cfg=new Configuration();
		cfg.configure();
		cfg.addAnnotatedClass(EmployeeDTO.class);
		SessionFactory fact=cfg.buildSessionFactory();
		Session session=fact.openSession();
		Transaction tx=session.beginTransaction();
		try
		{
			Query qry =session.createQuery("update from EmployeeDTO set sal=:n where sal=:i ");
			qry.setParameter("n", 1100);
			qry.setParameter("i", 8000);
			int status=qry.executeUpdate();
			
				System.out.println(status);
				tx.commit();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			session.close();
		}
	}
	 public void EmployeeNameStartsA() 
	 {
		 	List<EmployeeDTO> l1=null;
		 	Configuration cfg=new Configuration();
			cfg.configure();
			cfg.addAnnotatedClass(EmployeeDTO.class);
			SessionFactory fact=cfg.buildSessionFactory();
			Session session=fact.openSession();
			Transaction tx=session.beginTransaction();
			try
			{
			Query qry =session.createQuery(" from EmployeeDTO d where d.ename like 'A%%%'"); 
			l1 =qry.list();
			System.out.println(l1);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			finally {
				session.close();
			}
	 }
	   
	 public void EmployeeSalBetween() 
	 {
		 	List<EmployeeDTO> l1=null;
		 	Configuration cfg=new Configuration();
			cfg.configure();
			cfg.addAnnotatedClass(EmployeeDTO.class);
			SessionFactory fact=cfg.buildSessionFactory();
			Session session=fact.openSession();
			Transaction tx=session.beginTransaction();
			try
			{
			Query qry =session.createQuery(" from EmployeeDTO d where d.sal between 5000 and 7000"); 
			l1 =qry.list();
			System.out.println("The Employees whose salary is between 5000 & 7000 are :- "+l1);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			finally {
				session.close();
			} 
		
	 }
	 
	 public void secMinimAndSecMaximSal() 
	 {
		 	Configuration cfg=new Configuration();
			cfg.configure();
			cfg.addAnnotatedClass(EmployeeDTO.class);
			SessionFactory fact=cfg.buildSessionFactory();
			Session session=fact.openSession();
			Transaction tx=session.beginTransaction();
			try
			{
				Query qry =session.createQuery(" select min(sal) from EmployeeDTO where sal not in (select min(sal) from EmployeeDTO)");
				Object fromdb= qry.uniqueResult();
				System.out.println("Second Minimum salary is "+fromdb);
				
				Query qry1 =session.createQuery(" select max(sal) from EmployeeDTO where sal not in (select max(sal) from EmployeeDTO)");
				Object fromdb1= qry1.uniqueResult();
				System.out.println("Second Maximum salary is "+fromdb1);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			finally {
				session.close();
			} 
	 }
	 
	 public List<EmployeeDTO> listSameSalAndUpdate() 
	 {
		    List<EmployeeDTO> l1=null;
		 	Configuration cfg=new Configuration();
			cfg.configure();
			cfg.addAnnotatedClass(EmployeeDTO.class);
			SessionFactory fact=cfg.buildSessionFactory();
			Session session=fact.openSession();
			Transaction tx=session.beginTransaction();
			try
			{
			Query qry =session.createQuery("select distinct d.empno,d.ename,d.sal,d.deptno from EmployeeDTO d, EmployeeDTO d1 where d.sal=d1.sal and d.empno!=d1.empno order by d.sal"); 
			l1 =qry.list();
//			System.out.println(l1);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			finally {
				session.close();
			}
			return l1; 
	}
	 
	 public void listSameSalAndUpdateSalto5000() 
		{
		 	List<EmployeeDTO> l1=null;
		 	Configuration cfg=new Configuration();
			cfg.configure();
			cfg.addAnnotatedClass(EmployeeDTO.class);
			SessionFactory fact=cfg.buildSessionFactory();
			Session session=fact.openSession();
			Transaction tx=session.beginTransaction();
			try
			{
				Query qry =session.createQuery("select distinct d.empno,d.ename,d.sal from EmployeeDTO d, EmployeeDTO d1 where d.sal=d1.sal and d.empno!=d1.empno order by d.sal");
//				Query qry =session.createQuery("select distinct d.empno,d.ename,d.sal from EmployeeDTO d where d.sal in (select d.sal from EmployeeDTO d group by d.sal having count(1)>1)");
				l1 =qry.list();
				Iterator itr=l1.iterator();
				while(itr.hasNext())
				{
					Object[] obj=(Object[]) itr.next();
					System.out.println("empno - "+String.valueOf(obj[0]));
					System.out.println("ename - "+String.valueOf(obj[1]));
					System.out.println("sal - "+String.valueOf(obj[2]));
					System.out.println();
				}		
				Query qry1 =session.createQuery("update from EmployeeDTO set sal=:n where sal=:i ");
				qry1.setParameter("n", 5000);
				qry1.setParameter("i", 1250);
				int status=qry1.executeUpdate();
				System.out.println(status);
				tx.commit();
				if(status>=1)
				{
					System.out.println("Employees salary Updated");
				}
				else {
					System.out.println("Updation failed");
				}		
			}
			catch (Exception e) {
				System.out.println(e);
			}
			finally {
				session.close();
			}
			
		}
	 
}