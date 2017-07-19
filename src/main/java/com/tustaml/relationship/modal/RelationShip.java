package com.tustaml.relationship.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tustaml.user.modal.User;

@Entity
@Table(name="tbl_relationship")
public class RelationShip {

	@Id
	@SequenceGenerator(name="relationship_id_seq", sequenceName="relationship_id_seq",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="relationship_id_seq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_one_id", nullable=false, unique=true)
	private User userOne;
	
	@ManyToOne
	@JoinColumn(name="user_two_id", nullable=false, unique=true)
	private User userTwo;
	
	@ManyToOne
	@JoinColumn(name="action_user_id", nullable=false)
	private User userAction;
	
	private Boolean status;

	public User getUserOne() {
		return userOne;
	}

	public void setUserOne(User userOne) {
		this.userOne = userOne;
	}

	public User getUserTwo() {
		return userTwo;
	}

	public void setUserTwo(User userTwo) {
		this.userTwo = userTwo;
	}

	public User getUserAction() {
		return userAction;
	}

	public void setUserAction(User userAction) {
		this.userAction = userAction;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
