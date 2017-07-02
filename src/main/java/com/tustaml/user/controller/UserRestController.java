package com.tustaml.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tustaml.user.dao.UserDAO;
import com.tustaml.user.modal.User;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserDAO userDAO;;
	
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userDAO.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        			}
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
	
}
