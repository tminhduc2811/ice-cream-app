package com.atcud.icecreamapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcud.icecreamapp.entities.Reference;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {

}
