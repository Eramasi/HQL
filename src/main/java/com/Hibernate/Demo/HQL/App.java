package com.Hibernate.Demo.HQL;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        
        Configuration config=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class);
        
        ServiceRegistry registry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        
        SessionFactory sf=config.buildSessionFactory(registry);
        
        Session session=sf.openSession();
        
        session.beginTransaction();
        
		/*
		 * Random r=new Random();
		 * 
		 * 
		 * for(int i=1;i<=50;i++) { Student s=new Student();
		 * 
		 * s.setSid(i);
		 * 
		 * s.setSname("Name" + i);
		 * 
		 * s.setSmarks(r.nextInt(100));
		 * 
		 * session.save(s);
		 * 
		 * }
		 */     
        
        
		/*
		 * Query
		 * q1=session.createQuery("select sid,sname,smarks from Student where sid=35");
		 * 
		 * Object[] student=(Object[])q1.uniqueResult();
		 * 
		 * 
		 * for(Object o:student) {
		 * 
		 * System.out.println(o);
		 * 
		 * }
		 */
		/*
		 * List<Student> students=q1.list();
		 * 
		 * for(Student s1:students) { System.out.println(s1); }
		 */
        
        
       //using SQL Query
        
		/*
		 * SQLQuery
		 * query=session.createSQLQuery("select * from student where smarks>60");
		 * 
		 * query.addEntity(Student.class);
		 * 
		 * List<Student> students=query.list();
		 * 
		 * 
		 * for(Student o: students)
		 * 
		 * { System.out.println(o); }
		 */
        
        
        SQLQuery query=session.createSQLQuery("select sname,smarks from student where smarks>60");
        
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        
        List students=query.list();
        
        
        for(Object o:students)
        {
        	Map m=(Map)o;
        	
        	System.out.println(m.get("sname") + "  :  " + m.get("smarks"));
        	
        	
        }
        	
        session.getTransaction().commit();
        
        
        
        
        
    }
}
