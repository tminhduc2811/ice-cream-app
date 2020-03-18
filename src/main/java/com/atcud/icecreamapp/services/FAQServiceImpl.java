package com.atcud.icecreamapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.FAQ;
import com.atcud.icecreamapp.repositories.FAQRepository;

@Component
public class FAQServiceImpl implements FAQService {

	@Autowired
	private FAQRepository faqRepository;
	
	@Override
	public List<FAQ> getAllFAQ() {
		return faqRepository.findAll();
	}

	@Override
	public Optional<FAQ> getFAQById(Long id) {
		return faqRepository.findById(id);
	}

	@Override
	public FAQ save(FAQ faq) {
		return faqRepository.save(faq);
	}

	@Override
	public void delete(FAQ faq) {
		faqRepository.delete(faq);
	}

}
