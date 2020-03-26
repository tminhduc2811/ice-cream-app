package com.atcud.icecreamapp.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.repositories.FeedbackRepository;
import com.atcud.icecreamapp.services.FeedbackService;

@Component
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public List<FeedbackDTO> getAllFeedback() {
        List<Feedback> feedback = feedbackRepository.findAll();
        List<FeedbackDTO> result = new ArrayList<>();
        for(Feedback f: feedback) {
            result.add(DTOBuilder.feedbackToDTO(f));
        }
        return result;
    }

    @Override
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public void delete(Feedback feedback) {
        feedbackRepository.delete(feedback);
    }

    @Override
    public void update(Feedback feedback) {
        feedbackRepository.update(feedback);
    }

}
