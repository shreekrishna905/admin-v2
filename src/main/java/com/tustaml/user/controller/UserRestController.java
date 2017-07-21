package com.tustaml.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tustaml.user.dao.UserDAO;
import com.tustaml.user.modal.User;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserDAO userDAO;;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userDAO.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        			}
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
			if (userDAO.isUserExist(user.getEmail())) {
		            System.out.println("A User with name " + user.getEmail() + " already exist");
		            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		    }
			// Encode password and set to user password
			String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encodedPassword);		
	        userDAO.saveOrUpdate(user);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/user/check", method = RequestMethod.GET)
	public ResponseEntity<String> checkUser(@RequestParam("email") String email, UriComponentsBuilder ucBuilder){
			if (userDAO.isUserExist(email)) {
		        	return new ResponseEntity<String>("{\"status\":true}", HttpStatus.OK);
		    }
			return new ResponseEntity<String>("{\"status\":false}", HttpStatus.OK);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<User> getUser(@RequestParam("email") String email){
		 User user = userDAO.findByEmail(email);
	        if (user == null) {
	            System.out.println("User with username " + email + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	
}
