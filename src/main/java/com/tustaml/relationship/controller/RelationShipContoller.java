package com.tustaml.relationship.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.tustaml.relationship.dao.RelationShipDAO;
import com.tustaml.relationship.modal.RelationShip;

@RestController
@RequestMapping("/api")
public class RelationShipContoller {

	@Autowired
	private RelationShipDAO relationShipDAO;
	
	@RequestMapping(value = "/relationship/list", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<RelationShip>> listAllRelationShip(@RequestParam("id") Long id) {
        List<RelationShip> relationship = relationShipDAO.findAll(id);
        if (relationship.isEmpty()) {
            return new ResponseEntity<List<RelationShip>>(HttpStatus.NO_CONTENT);
        			}
        return new ResponseEntity<List<RelationShip>>(relationship, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/relationship", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody RelationShip relationShip, UriComponentsBuilder ucBuilder){
		relationShipDAO.saveOrUpdate(relationShip);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/relationship/{id}").buildAndExpand(relationShip.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}


}
