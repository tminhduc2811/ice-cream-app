package com.atcud.icecreamapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Reference;
import com.atcud.icecreamapp.repositories.ReferenceRepository;
import com.atcud.icecreamapp.services.ReferenceService;

@Component
public class ReferenceServiceImpl implements ReferenceService {

	@Autowired
	private ReferenceRepository referenceRepository;
	
	@Override
	public List<Reference> getAllReferences() {
		return referenceRepository.findAll();
	}

	@Override
	public Optional<Reference> getReferenceById(Long id) {
		return referenceRepository.findById(id);
	}

	@Override
	public Reference save(Reference reference) {
		return referenceRepository.save(reference);
	}

	@Override
	public void delete(Reference reference) {
		referenceRepository.delete(reference);
	}

	@Override
	public void update(Reference reference) {
		referenceRepository.update(reference);
	}

}
