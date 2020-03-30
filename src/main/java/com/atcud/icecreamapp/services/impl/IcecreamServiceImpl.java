package com.atcud.icecreamapp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.RecipeDTO;
import com.atcud.icecreamapp.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Icecream createIcecream(Icecream icecream) {
        if (icecreamRepository.isExisted(icecream.getName())) {
            throw new CustomException("Icecream already existed", HttpStatus.CONFLICT);
        }
        return icecreamRepository.save(icecream);
    }

    @Override
    public void delete(Long id) {
        Optional<Icecream> icecream = icecreamRepository.findById(id);
        if (!icecream.isPresent()) {
            throw new CustomException("Icecream not found", HttpStatus.NOT_FOUND);
        }
        icecreamRepository.delete(icecream.get());
    }

    @Override
    public void update(Icecream icecream) {
        Optional<Icecream> entity = icecreamRepository.findById(icecream.getId());
        if (!entity.isPresent()) {
            throw new CustomException("Icecream not found", HttpStatus.NOT_FOUND);
        }
        icecreamRepository.update(icecream);
    }

    @Override
    public List<RecipeDTO> getAllRecipeByIcecreamId(Long id) {
        Optional<Icecream> icecream = icecreamRepository.findById(id);
        if (!icecream.isPresent()) {
            throw new CustomException("Icecream not found", HttpStatus.NOT_FOUND);
        }
        List<Recipe> recipes = icecream.get().getRecipes();
        List<RecipeDTO> recipeDTO = new ArrayList<>();
        for(Recipe recipe: recipes) {
            recipeDTO.add(DTOBuilder.recipeToDTO(recipe));
        }
        return recipeDTO;
    }

}
