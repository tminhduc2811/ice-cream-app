package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Reference;

public interface ReferenceService {
	
	public List<Reference> getAllReferences();
	
	public Optional<Reference> getReferenceById(Long id);
	
	public Reference save(Reference reference);
	
	public void delete(Reference reference);
	
	public void update(Reference reference);
	
}
