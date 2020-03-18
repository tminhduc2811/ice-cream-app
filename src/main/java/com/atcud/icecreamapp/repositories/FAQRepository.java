package com.atcud.icecreamapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcud.icecreamapp.entities.FAQ;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
	
}
