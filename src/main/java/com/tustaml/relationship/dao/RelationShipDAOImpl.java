
package com.tustaml.relationship.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tustaml.relationship.modal.RelationShip;

@Service("relationShipDAO")
@Repository
@Transactional
public class RelationShipDAOImpl implements RelationShipDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void saveOrUpdate(RelationShip relationship) {
		if(relationship.getId()==null){
			entityManager.persist(relationship);	
		} else {
			entityManager.merge(relationship);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<RelationShip> findAll(Long id) {
		 Query query = entityManager
                .createQuery("SELECT r FROM RelationShip r where r.userOne.id = :userOne OR r.userTwo.id = :userTwo AND r.status = :status");
		 query.setParameter("userOne", id);
		 query.setParameter("userTwo",id);
		 query.setParameter("status", true);
		 List<RelationShip> listResult = query.getResultList();
		 return listResult;
	}

}
