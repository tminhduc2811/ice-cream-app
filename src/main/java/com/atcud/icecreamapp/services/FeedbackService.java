package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.entities.FeedbackDTO;
import com.atcud.icecreamapp.entities.Feedback;

public interface FeedbackService {

    public List<FeedbackDTO> getAllFeedback();

    public Optional<Feedback> getFeedbackById(Long id);

    public Feedback save(Feedback feedback);

    public void delete(Feedback feedback);

    public void update(Feedback feedback);

}
