package com.atcud.icecreamapp.repositories.feedback;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedbackRepository {

    Page<Feedback> findPage(Pageable pageable);

    List<Feedback> findAll();

    Optional<Feedback> findById(Long id);

    Feedback save(Feedback feedback);

    void delete(Feedback feedback);

    void update(Feedback feedback);

}
