package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import vo.SourceVo;

public class SourceMasterDao {

	static AnnotationConfiguration configuration;
	static SessionFactory factory;
	Session session;
	Transaction transaction;
	
	public void getConnection()
	{
		configuration=new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		factory=configuration.buildSessionFactory();
		session=factory.openSession();
	}
	
	public List<SourceVo> getSourceList()
	{
		getConnection();
		transaction=session.beginTransaction();
		List<SourceVo> list=session.createQuery("from SourceVo").list();
		return list;
	}
}
