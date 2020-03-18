package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Reference;

public interface ReferenceRepository {

	public List<Reference> findAll();
	
	public Optional<Reference> findById(Long id);
	
	public Reference save(Reference reference);
	
	public void delete(Reference reference);
	
	public void update(Reference reference);
	
}
