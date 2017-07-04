package com.tustaml.user.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tustaml.user.modal.User;

@Service("userDAO")
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	@Transactional(readOnly=true)
	public User findByUserName(String username) {
		User user = (User) entityManager.createQuery("select u from User u where u.username= :username")
											.setParameter("username", username)
											.getSingleResult();
		return user;
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<User> findAll(){
		List<User> users = entityManager
	                .createQuery("SELECT u FROM User u ORDER BY u.firstName ASC")
	                .getResultList();
	        return users;
	}
	
	@Override
	public void saveOrUpdate(User user){
		if(user.getId()==null){
			entityManager.persist(user);	
		} else {
			entityManager.merge(user);
		}
	}

	@Override
	public boolean isUserExist(User user) {
		return findByUserName(user.getUsername()) !=null;
	}


}
