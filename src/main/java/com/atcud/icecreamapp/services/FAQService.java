package com.atcud.icecreamapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atcud.icecreamapp.entities.FAQ;
import com.atcud.icecreamapp.repositories.IFAQRepository;

@Component
public class FAQService implements IFAQService {

	@Autowired
	private IFAQRepository FAQRepository;
	
	@Override
	public List<FAQ> getAllFAQ() {
		List<FAQ> FAQList = FAQRepository.getAllFAQ();
		return FAQList;
	}

}
