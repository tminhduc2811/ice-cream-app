package com.atcud.icecreamapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcud.icecreamapp.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	
}
