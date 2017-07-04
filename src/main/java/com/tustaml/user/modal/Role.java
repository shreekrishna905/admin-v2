package com.tustaml.user.modal;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="roles")
public class Role {

	@Id
	@SequenceGenerator(name="roles_id_seq", sequenceName="roles_id_seq",allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="roles_id_seq")
	private Long id;
	
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	private Collection<User> users;

	public Role(){
		
	}

	public Role(String name){
		this.name = "ROLE_" + name;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
}
