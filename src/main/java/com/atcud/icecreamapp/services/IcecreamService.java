package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Icecream;
import com.atcud.icecreamapp.entities.Recipe;

public interface IcecreamService {
	
	public List<Icecream> getAllIcecreams();
	
	public List<Recipe> getAllRecipeByIcecreamId(Long id);
	
	public Optional<Icecream> getIcecreamById(Long id);
	
	public Icecream save(Icecream icecream);
	
	public void delete(Icecream icecream);
	
	public void update(Icecream icecream);
	
}
