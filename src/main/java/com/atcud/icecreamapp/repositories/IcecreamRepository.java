package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Icecream;

public interface IcecreamRepository {

    public List<Icecream> findAll();

    public Optional<Icecream> findById(Long id);

    Icecream save(Icecream icecream);

    public void delete(Icecream icecream);

    public void update(Icecream icecream);

    public boolean isExisted(String icecreamName);
}
