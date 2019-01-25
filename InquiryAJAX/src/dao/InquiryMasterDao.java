package dao;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import vo.InquiryVo;

public class InquiryMasterDao {

	static AnnotationConfiguration configuration;
	static SessionFactory factory;
	Session session;
	org.hibernate.Transaction transaction;
	List<InquiryVo> list;
	
	public void getConnection()
	{
		configuration=new AnnotationConfiguration();
		configuration.configure("hibernate.cfg.xml");
		factory=configuration.buildSessionFactory();
		session=factory.openSession();
	}
	
	public void addInquiry(InquiryVo inquiryVo)
	{
		getConnection();
		transaction=session.beginTransaction();
		session.save(inquiryVo);
		transaction.commit();
		session.close();
	}
	
	public List<InquiryVo> getAllInquiries()
	{
		getConnection();
		transaction=session.beginTransaction();
		list=session.createQuery("from InquiryVo").list();
		return list;
	}
}
