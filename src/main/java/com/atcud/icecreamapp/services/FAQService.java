package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.FAQ;

public interface FAQService {

    public List<FAQ> getAllFAQ();

    public Optional<FAQ> getFAQById(Long id);

    public FAQ save(FAQ faq);

    public void delete(FAQ faq);

    public void update(FAQ faq);

}
