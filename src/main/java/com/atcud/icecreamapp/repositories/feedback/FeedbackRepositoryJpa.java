package com.atcud.icecreamapp.repositories.feedback;

import com.atcud.icecreamapp.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepositoryJpa extends JpaRepository<Feedback, Long> {
}
