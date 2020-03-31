package com.atcud.icecreamapp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.DTO.DTOBuilder;
import com.atcud.icecreamapp.DTO.entities.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.Feedback;
import com.atcud.icecreamapp.repositories.feedback.FeedbackRepository;
import com.atcud.icecreamapp.services.FeedbackService;

@Component
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Override
    public Page<FeedbackDTO> findPage(Pageable pageable) {
        Page<Feedback> entityPage = feedbackRepository.findPage(pageable);
        return DTOBuilder.mapPage(entityPage, FeedbackDTO.class);
    }

    @Override
    public List<FeedbackDTO> getAllFeedback() {
        List<Feedback> feedback = feedbackRepository.findAll();
        return DTOBuilder.mapList(feedback, FeedbackDTO.class);
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
