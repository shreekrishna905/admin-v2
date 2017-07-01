package com.tustaml.user.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tustaml.user.modal.User;

@Service
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional(readOnly=true)
	public User findByUserName(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from user where username = :username");
		query.setParameter("username", username);
		return (User) query.uniqueResult();
	}

}
