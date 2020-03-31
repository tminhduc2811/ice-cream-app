package com.atcud.icecreamapp.repositories;

import java.util.List;
import java.util.Optional;

import com.atcud.icecreamapp.entities.FAQ;

public interface FAQRepository {

    public List<FAQ> findAll();

    Optional<FAQ> findById(Long id);

    public FAQ save(FAQ faq);

    public void delete(FAQ faq);

    public void update(FAQ faq);

}
