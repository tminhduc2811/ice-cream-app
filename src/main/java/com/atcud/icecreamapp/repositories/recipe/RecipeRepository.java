package com.atcud.icecreamapp.repositories.recipe;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeRepository {

    Page<Recipe> findPage(Pageable pageable);

    List<Recipe> findAll();

    Optional<Recipe> findById(Long id);

    Recipe save(Recipe recipe);

    void delete(Recipe recipe);

    void update(Recipe recipe);

}

