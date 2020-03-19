package com.atcud.icecreamapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Icecream;
import com.atcud.icecreamapp.entities.Recipe;
import com.atcud.icecreamapp.repositories.IcecreamRepository;
import com.atcud.icecreamapp.services.IcecreamService;

@Component
public class IcecreamServiceImpl implements IcecreamService {

	@Autowired
	IcecreamRepository icecreamRepository;
	
	@Override
	public List<Icecream> getAllIcecreams() {
		return icecreamRepository.findAll();
	}

	@Override
	public Optional<Icecream> getIcecreamById(Long id) {
		return icecreamRepository.findById(id);
	}

	@Override
	public Icecream save(Icecream icecream) {
		return icecreamRepository.save(icecream);
	}

	@Override
	public void delete(Icecream icecream) {
		icecreamRepository.delete(icecream);
	}

	@Override
	public void update(Icecream icecream) {
		icecreamRepository.update(icecream);
	}

	@Override
	public List<Recipe> getAllRecipeByIcecreamId(Long id) {
		Icecream icecream = icecreamRepository.findById(id).get();
		return icecream.getRecipes();
	}

}
