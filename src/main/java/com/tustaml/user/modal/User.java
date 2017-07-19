package com.tustaml.user.modal;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tustaml.relationship.modal.RelationShip;


@Entity
@Table(name="tbl_users")
public class User {

	@Id
	@SequenceGenerator(name="users_id_seq", sequenceName="users_id_seq",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="users_id_seq")
	private Long id;
	
	@JsonIgnore
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	private boolean enabled;
	
	@ManyToMany
    @JoinTable( 
        name = "tbl_users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
	private Collection<Role> roles;
	
	@OneToMany(mappedBy="userOne")
	@JsonIgnore
	private Collection<RelationShip> relationShipOne;
	
	@OneToMany(mappedBy="userTwo")
	@JsonIgnore
	private Collection<RelationShip> relationShipTwo;
	
	@OneToMany(mappedBy="userAction")
	@JsonIgnore
	private Collection<RelationShip> relationShipAction;
	
	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	//Helper Class To Add Role
	public void addRole(Role role){
		role.getUsers().add(this);
		this.roles.add(role);
	}

	public Collection<RelationShip> getRelationShipOne() {
		return relationShipOne;
	}

	public void setRelationShipOne(Collection<RelationShip> relationShipOne) {
		this.relationShipOne = relationShipOne;
	}

	public Collection<RelationShip> getRelationShipTwo() {
		return relationShipTwo;
	}

	public void setRelationShipTwo(Collection<RelationShip> relationShipTwo) {
		this.relationShipTwo = relationShipTwo;
	}

	public Collection<RelationShip> getRelationShipAction() {
		return relationShipAction;
	}

	public void setRelationShipAction(Collection<RelationShip> relationShipAction) {
		this.relationShipAction = relationShipAction;
	}
	
}
