package com.atcud.icecreamapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcud.icecreamapp.entities.Reference;
import com.atcud.icecreamapp.services.ReferenceService;

@RestController
@RequestMapping("references")
public class ReferenceController {
	
	@Autowired
	private ReferenceService service;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Reference>> getAllReferences() {
		List<Reference> references = service.getAllReferences();
		if (references.isEmpty()) {
			return new ResponseEntity<List<Reference>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Reference>>(references, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<Reference> createReference(@RequestBody Reference reference) {
		return new ResponseEntity<Reference>(service.save(reference), HttpStatus.CREATED);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces="application/json" )
	public ResponseEntity<Reference> deleteRecipe(@PathVariable Long id){
		Optional<Reference> reference = service.getReferenceById(id);
		if (!reference.isPresent()) {
			return new ResponseEntity<Reference>(HttpStatus.NOT_FOUND);
		}
		service.delete(reference.get());
		return new ResponseEntity<Reference>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces="application/json" )
	public ResponseEntity<Reference> update(@RequestBody Reference newReference, @PathVariable Long id) {
		Optional<Reference> currentReference = service.getReferenceById(id);
		if (!currentReference.isPresent()) {
			return new ResponseEntity<Reference>(HttpStatus.NOT_FOUND);
		}
		service.update(newReference);
		return new ResponseEntity<Reference>(newReference, HttpStatus.OK);
	}
}
