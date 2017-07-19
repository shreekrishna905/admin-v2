package com.tustaml.relationship.dao;

import java.util.List;

import com.tustaml.relationship.modal.RelationShip;

public interface RelationShipDAO {

	public void saveOrUpdate(RelationShip relationship);
	
	public List<RelationShip> findAll(Long id);
	
	
}
