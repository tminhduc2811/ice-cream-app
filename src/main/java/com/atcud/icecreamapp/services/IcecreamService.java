package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.RecipeDTO;
import com.atcud.icecreamapp.entities.Icecream;
public interface IcecreamService {

    public List<Icecream> getAllIcecreams();

    public List<RecipeDTO> getAllRecipeByIcecreamId(Long id);

    public Optional<Icecream> getIcecreamById(Long id);

    public Icecream createIcecream(Icecream icecream);

    public void delete(Long id);

    public void update(Icecream icecream);

}
